package utils;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class Admin {

    private static final Logger logger = LoggerFactory.getLogger(Admin.class);

    private Executor executor;
    private static Admin instance;

    private Admin() {
        try {
            var redirect = new LaxRedirectStrategy();
            var ssl = new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build();
            var config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
            executor = Executor.newInstance(
                    HttpClientBuilder
                            .create()
                            .setRedirectStrategy(redirect)
                            .setSSLContext(ssl)
                            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                            .setDefaultRequestConfig(config)
                            .build());
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
        }
        login();
    }

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }

        return instance;
    }

    public void deleteMaster(String id) {
        var url = Config.getAdminUrl() + String.format("master/%s/delete", id);

        try {
            var result = executor.execute(Request.Get(url))
                    .returnResponse()
                    .getStatusLine()
                    .getStatusCode();

            if (result != 200) {
                logger.info("Delete master request failed");
                throw new HttpResponseException(result, "Delete master request failed");
            }
            logger.info("Master profile deleted. Id: {}", id);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(String id) {
        var url = Config.getAdminUrl() + String.format("reference/category/%s/delete", id);

        try {
            var result = executor.execute(Request.Get(url))
                    .returnResponse()
                    .getStatusLine()
                    .getStatusCode();

            if (result != 200) {
                logger.info("Delete category request failed");
                throw new HttpResponseException(result, "Delete category request failed");
            }
            logger.info("Category deleted. Id: {}", id);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(String customerId) {
        if (customerId.isEmpty())
        {
            return;
        }

        var url = Config.getAdminUrl() + String.format("customer/%s/delete", customerId);

        try {
            var result = executor.execute(Request.Get(url))
                    .returnResponse()
                    .getStatusLine()
                    .getStatusCode();

            if (result != 200) {
                logger.info("Delete customer request failed");
                throw new HttpResponseException(result, "Delete customer request failed");
            }
            logger.info("Customer deleted. Id: {}", customerId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSmsUrl(String phoneNumber) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            logger.info("Getting service url for phone number: {}", phoneNumber);
            var smsLog = getSmsLogPage();
            var code = new NewXmlParser(smsLog).getUrl(phoneNumber);
            if (code.isEmpty()) {
                logger.info("Url not found, retry");
                Thread.sleep(1000);
            } else {
                logger.info("Url: {}", code);
                return code;
            }
        }

        throw new NullPointerException("No SMS code found after 10 attempts");
    }

    public String getSmsCode(String phoneNumber) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            logger.info("Getting SMS code for phone number: {}", phoneNumber);
            var smsLog = getSmsLogPage();
            var code = new NewXmlParser(smsLog).getSmsCode(phoneNumber);
            if (code.isEmpty()) {
                logger.info("Code not found, retry");
                Thread.sleep(1000);
            } else {
                logger.info("SMS code: {}", code);
                return code;
            }
        }

        throw new NullPointerException("No SMS code found after 10 attempts");
    }

    public String getSmsPassword(String phoneNumber) throws InterruptedException {
        var stringKey = "SmsRegistration";
        if (Config.isFixinglist()) {
            stringKey += "_fixinglist";
        }
        if (Config.isUstabor()) {
            stringKey += "_ustabor";
        }

        for (int i = 0; i < 10; i++) {
            logger.info("Getting SMS password for phone number: {}", phoneNumber);
            var smsLog = getSmsLogPage();
            var password = new NewXmlParser(smsLog).getSmsPassword(phoneNumber, XmlParser.getTextByKey(stringKey));
            if (password.isEmpty()) {
                logger.info("Password not found, retry");
                Thread.sleep(1000);
            } else {
                logger.info("SMS password: {}", password);
                return password;
            }
        }

        throw new NullPointerException("No SMS password found after 10 attempts");
    }

    public String getCustomerId(String phoneNumber) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            logger.info("Getting customer id for phone number: {}", phoneNumber);
            var page = getCustomersPage();
            var code = new NewXmlParser(page).getId(phoneNumber);
            if (code.equals("")) {
                logger.info("Url not found, retry");
                Thread.sleep(1000);
            } else {
                logger.info("Customer id: {}", code);
                return code;
            }
        }

        throw new NullPointerException("No SMS code found after 10 attempts");
    }

    public String getSmsByText(String phoneNumber, String sms) {
        var smsLog = getSmsLogPage();
        var smsText = new NewXmlParser(smsLog).getSmsText(phoneNumber, sms);
        logger.info("Get SMS by piece of text for phone number: {}", phoneNumber);

        return smsText;
    }

    private void login() {
        var url = Config.getAdminUrl() + "login";

        try {
            var result = executor.execute(Request.Post(url)
                    .bodyForm(Form.form()
                            .add("data[login]", Config.getUsers().getAdmin().getEmail())
                            .add("data[password]", Config.getUsers().getAdmin().getPassword())
                            .build()))
                    .returnResponse()
                    .getStatusLine()
                    .getStatusCode();

            if (result != 200) {
                logger.info("Admin login failed");
                throw new HttpResponseException(result, "Login failed");
            }
            logger.info("Admin login successfully");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getSmsLogPage() {
        var url = Config.getAdminUrl() + "logs/sms";

        try {
            return executor.execute(Request.Get(url)).returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getCustomersPage() {
        var url = Config.getAdminUrl() + "customer";

        try {
            return executor.execute(Request.Get(url)).returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
