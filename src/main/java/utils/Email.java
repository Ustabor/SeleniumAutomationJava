package utils;

import com.refactorable.guerrillamail.api.client.GuerrillaMailClient;
import com.refactorable.guerrillamail.api.client.factory.GuerrillaMailClientFactory;
import com.refactorable.guerrillamail.api.client.model.request.AddressRequest;
import com.refactorable.guerrillamail.api.client.model.response.AddressResponse;
import com.refactorable.guerrillamail.api.client.model.response.EmailResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import static com.refactorable.guerrillamail.api.client.model.request.EmailRequest.*;
import static com.refactorable.guerrillamail.api.client.model.request.EmailsRequest.*;

public class Email {

    private static GuerrillaMailClient guerrillaMailClient;
    private static AddressResponse addressResponse;

    public Email() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://api.guerrillamail.com");
        guerrillaMailClient = GuerrillaMailClientFactory.defaultClient(webTarget);
        addressResponse = guerrillaMailClient.address( AddressRequest.initialize() );
    }

    public enum EmailType {
        ConfirmRegister,
        ForgotPassword
    }

    public String getEmailAddress() {
        return addressResponse.getAddress();
    }

    public String getAuthCode() throws TimeoutException {
        var key = String.format("%s_%s_%s",
                "CustomerRegEmailSubject",
                Config.getCountryCode(),
                Config.getEnv());

        var email = getEmailBySubject(XmlParser.getTextByKey(key));

        var pattern = Pattern.compile(String.format("(?<=%s).*?(?=</)", XmlParser.getTextByKey("AuthCode")));
        var matcher = pattern.matcher(email.getBody());
        if (matcher.find()) {
            return matcher.group(0);
        }
        throw new NullPointerException("Getting email auth code failed");
    }

    public String getUrl(EmailType emailType) throws TimeoutException {
        String emailSubject = "";
        switch (emailType) {
            case ConfirmRegister:
                emailSubject = XmlParser.getTextByKey("CustomerRegEmailSubject_new_test");
                break;
            case ForgotPassword:
                emailSubject = XmlParser.getTextByKey("ForgotPassEmailSubject");
                break;
        }
        var email = getEmailBySubject(emailSubject);

        var pattern = Pattern.compile(String.format("(?<=<a href=\").*?(?=\">%s</a>)", XmlParser.getTextByKey("ForgotPassword")));
        var matcher = pattern.matcher(email.getBody());
        if (matcher.find()) {
            return matcher.group(0);
        }
        throw new NullPointerException("Getting forgot password link is failed");
    }

    private EmailResponse getEmailBySubject(String subject) throws TimeoutException {
        final EmailResponse[] response = new EmailResponse[1];
        WaitHelper.pollingWait(5 * 60000, 15000, () -> {

            try {
                var emails = guerrillaMailClient.emails(
                        emails( addressResponse.getSessionId(), 0L, 0))
                        .getEmails();

                if (emails.isEmpty()) {
                    return false;
                }

                var email = emails.stream()
                        .filter(e -> e.getSubject().contains(subject))
                        .findFirst();
                email.ifPresent(emailResponse -> response[0] = emailResponse);

                return email.isPresent();
            } catch (Exception exception) {
                return false;
            }
        });

        var email = guerrillaMailClient.email(
                fetch(addressResponse.getSessionId(), response[0].getId())
        );

        guerrillaMailClient.delete(
                delete(addressResponse.getSessionId(), email.getId())
        );

        return email;
    }
}
