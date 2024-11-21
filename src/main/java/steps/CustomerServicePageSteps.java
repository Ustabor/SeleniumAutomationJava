package steps;

import entities.Service;
import entities.User;
import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.CustomerServicesPage;

public class CustomerServicePageSteps extends ScenarioSteps {

    private CustomerServicesPage customerServicesPage;

    @Step
    public void openPage() {
        customerServicesPage.openServices();
    }

    @Step
    public void selectCategory(String name) {
        customerServicesPage.selectCategory(name);
    }

    @Step
    public void verifyServiceIsVisible(Service service) {
        customerServicesPage.verifyServiceName(service.getName());
        customerServicesPage.verifyServicePrice(String.valueOf(service.getPrice()));
    }

    @Step
    public void clickOrderService() {
        customerServicesPage.clickOrderService();
    }

    @Step
    public void increaseServicesCount() {
        customerServicesPage.increaseServicesCount();
    }

    @Step
    public void verifyPrice(String price) {
        customerServicesPage.verifyOrderFormServicePrice(price);
    }

    @Step
    public void submitServiceOrder() {
        customerServicesPage.submitOrder();
    }

    @Step
    public void verifyConfirmationInfo(User customer, String price) {
        customerServicesPage.verifyPriceConfirmation(price);
        customerServicesPage.verifyCustomerPhoneNumber(customer.getPhoneNumber());
    }

    @Step
    public void enterPaymentCardInfo(String name, String number, String expiryDate) {
        customerServicesPage.enterCardName(name);
        customerServicesPage.enderCardNumber(number);
        customerServicesPage.enterExpiryDate(expiryDate);
        customerServicesPage.submitOrder();
    }

    @Step
    public void clickServiceDetails() {
        customerServicesPage.clickDetails();
    }

    @Step
    public void verifyServiceDetails(Service service) {
        customerServicesPage.verifyServiceDetailsName(service.getName().split(" ")[0]);
        customerServicesPage.verifyServiceDetailsPrice(String.valueOf(service.getPrice()));
    }

    @Step
    public void clickServiceDetailsOrder() {
        customerServicesPage.clickServiceDetailsOrder();
    }

    @Step
    public void verifyOrderFormIsVisible() {
        customerServicesPage.verifyOrderConfirmationFormIsVisible();
    }

    @Step
    public void enterCustomerInfo(User customer) {
        customerServicesPage.enterCustomerFirstName(customer.getFirstName());
        customerServicesPage.enterCustomerPhoneNumber(customer.getPhoneNumber());
        customerServicesPage.enterCustomerAddress("Test Address");
    }

    @Step
    public void waitForCodeForm() {
        customerServicesPage.waitForSubmitCodeForm();
    }

    @Step
    public String getSmsCode(User customer) throws InterruptedException {
        return customerServicesPage.getSmsCode(customer);
    }

    @Step
    public void confirmPhoneNumber(String code, String number) throws InterruptedException {
        customerServicesPage.enterSmsCode(code);
        customerServicesPage.clickCodeConfirm();
    }

    @Step
    public void enterOrderConfirmationCode(String code) {
        customerServicesPage.enterOrderConfirmationCode(code);
        customerServicesPage.submitOrder();
    }

    @Step
    public void verifyOrderConfirmedIsVisible() {
        customerServicesPage.verifyOrderConfirmedIsVisible();
    }

    @Step
    public String getUrlFromSms(User customer) throws InterruptedException {
        return customerServicesPage.getUrlFromSms(customer);
    }

    @Step
    public String getServiceId() {
        return customerServicesPage.getServiceId();
    }

    @Step
    public void closeServiceDetails() {
        customerServicesPage.closeServiceDetails();
    }
}
