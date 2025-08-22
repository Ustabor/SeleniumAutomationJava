package utils;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Admin {
    private static final Logger logger = LoggerFactory.getLogger(Admin.class);
    private static Executor executor;
    private static Admin instance;
    private static boolean isAuthenticated = false;

    private Admin() {}

    public static Admin getInstance() throws IOException {
        if (instance == null) {
            executor = RequestsExecutor.create();
            instance = new Admin();
        }

        return instance;
    }

    public void deleteMaster(String id) throws IOException {
        loginIfNeeded();
        var url = Config.getAdminUrl() + String.format("master/%s/delete", id);

        var result = executor.execute(Request.Get(url))
                .returnResponse()
                .getStatusLine()
                .getStatusCode();

        if (result != 200) {
            logger.info("Delete master request failed");
            throw new HttpResponseException(result, "Delete master request failed");
        }
        logger.info("Master profile deleted. Id: {}", id);
    }

    public void deleteCategory(String id) throws IOException {
        loginIfNeeded();
        var url = Config.getAdminUrl() + String.format("reference/category/%s/delete", id);

        var result = executor.execute(Request.Get(url))
                .returnResponse()
                .getStatusLine()
                .getStatusCode();

        if (result != 200) {
            logger.info("Delete category request failed");
            throw new HttpResponseException(result, "Delete category request failed");
        }
        logger.info("Category deleted. Id: {}", id);
    }

    public void deleteCustomer(String customerId) throws IOException {
        loginIfNeeded();
        if (customerId.isEmpty()) return;

        var url = Config.getAdminUrl() + String.format("customer/%s/delete", customerId);

        var result = executor.execute(Request.Get(url))
                .returnResponse()
                .getStatusLine()
                .getStatusCode();

        if (result != 200) {
            logger.info("Delete customer request failed");
            throw new HttpResponseException(result, "Delete customer request failed");
        }
        logger.info("Customer deleted. Id: {}", customerId);
    }

    public String getSmsUrl(String phoneNumber) throws InterruptedException, IOException {
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

    public String getSmsCode(String phoneNumber) throws InterruptedException, IOException {
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

    public String getSmsPassword(String phoneNumber) throws InterruptedException, IOException {
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

    public String getCustomerId(String phoneNumber) throws InterruptedException, IOException {
        for (int i = 0; i < 10; i++) {
            logger.info("Getting customer id for phone number: {}", phoneNumber);
            var page = getCustomersPage();
            var code = new NewXmlParser(page).getId(phoneNumber);
            if (code.isEmpty()) {
                logger.info("Customer id not found, retry");
                Thread.sleep(1000);
            } else {
                logger.info("Customer id: {}", code);
                return code;
            }
        }

        throw new NullPointerException("No customer id found after 10 attempts");
    }

    public String getSmsByText(String phoneNumber, String sms) throws IOException {
        var smsLog = getSmsLogPage();
        var smsText = new NewXmlParser(smsLog).getSmsText(phoneNumber, sms);
        logger.info("Get SMS by piece of text for phone number: {}", phoneNumber);

        return smsText;
    }

    private void loginIfNeeded() throws IOException {
        if (isAuthenticated) return;

        var url = Config.getAdminUrl() + "login";

        var loginResult = executor.execute(Request.Post(url)
                        .bodyForm(Form.form()
                                .add("data[login]", Config.getUsers().getAdmin().getEmail())
                                .add("data[password]", Config.getUsers().getAdmin().getPassword())
                                .build()))
                .returnResponse()
                .getStatusLine()
                .getStatusCode();

        if (loginResult != 200) {
            logger.info("Admin login failed");
            throw new HttpResponseException(loginResult, "Login failed");
        }
        isAuthenticated = true;
        logger.info("Admin login successfully");
    }

    private String getSmsLogPage() throws IOException {
        loginIfNeeded();
        var url = Config.getAdminUrl() + "logs/sms";
        return executor
                .execute(Request.Get(url))
                .returnContent()
                .asString();
    }

    private String getCustomersPage() throws IOException {
        loginIfNeeded();
        var url = Config.getAdminUrl() + "customer";
        return executor
                .execute(Request.Get(url))
                .returnContent()
                .asString();
    }
}
