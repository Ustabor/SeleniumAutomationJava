package steps;

import entities.Category;
import entities.User;
import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.PlaceOrderPage;
import utils.Admin;
import utils.XmlParser;

import java.io.IOException;

public class PlaceOrderPageSteps extends ScenarioSteps {

    private PlaceOrderPage placeOrderPage;

    @Step
    public void verifyPage() {
        placeOrderPage.descriptionShouldBeVisible();
        placeOrderPage.phoneInputShouldBeVisible();
        placeOrderPage.confirmButtonShouldBeVisible();
    }

    @Step
    public void placeOrder(User customer, Category category) throws InterruptedException, IOException {
        var request = XmlParser.getTextByKey("Service");
        var question = XmlParser.getTextByKey("Question_main");

        enterDescription();
        enterPhoneNumber(customer);
        clickPlaceOrder();

        waitForCodeForm();
        var smsCode = getSmsCode(customer);
        confirmPhoneNumber(smsCode, customer.getPhoneNumber());

        var customerId = Admin.getInstance().getCustomerId(customer.getPhoneNumber());
        customer.setProfileId(customerId);

        var password = Admin.getInstance().getSmsPassword(customer.getPhoneNumber());
        customer.setPassword(password);

        verifySuccessPageIsVisible();
        clickFillRequest();

        selectCategory(category);
        selectRequest(request);
        selectQuestion(question);
        selectStartDateImmediately();
        selectStartTime();
        fillContactInfo(customer, "Some request info");
        clickPlaceOrder();

        verifySuccessPageIsVisible();
    }

    @Step
    public void selectStartTime() {
        placeOrderPage.selectStartTimeMorning();
        placeOrderPage.waitForServiceLoader();
    }

    @Step
    public void selectCategory(Category category) {
        placeOrderPage.selectCategory(category.getSystemId());
        placeOrderPage.waitForServiceLoader();
    }

    @Step
    public void selectRequest(String request) {
        placeOrderPage.selectWhatToDo(request);
        placeOrderPage.waitForServiceLoader();
    }

    @Step
    public void selectQuestion(String question) {
        placeOrderPage.selectQuestion(question);
        placeOrderPage.waitForServiceLoader();
    }

    @Step
    public void selectStartDateImmediately() {
        placeOrderPage.selectStartDateImmediately();
        placeOrderPage.waitForServiceLoader();
    }

    @Step
    public void fillContactInfo(User guest, String info) {
        placeOrderPage.enterName(guest.getFirstName());
        placeOrderPage.enterPhoneNumber(guest.getPhoneNumber());
        placeOrderPage.enterAddress(guest.getCity());
        placeOrderPage.enterAdditionalInfo(info);
    }

    @Step
    public void confirmPhoneNumber(String code, String number) throws InterruptedException, IOException {
        placeOrderPage.enterSmsCode(code);
        placeOrderPage.clickCodeConfirm();

        if (placeOrderPage.isRefreshLinkVisible()) {
            retryEnterCode(number);
        }
    }

    @Step
    public void retryEnterCode(String phoneNumber) throws InterruptedException, IOException {
        placeOrderPage.resendCode();
        placeOrderPage.waitForLoaderDisappears();

        var smsCode = Admin.getInstance().getSmsCode(phoneNumber);
        placeOrderPage.enterSmsCode(smsCode);
        placeOrderPage.clickConfirmButton();
    }

    @Step
    public void openRequestsPage() {
        placeOrderPage.clickMyRequestsBtn();
    }

    @Step
    public void waitForCodeForm() {
        placeOrderPage.waitForSubmitCodeForm();
    }

    @Step
    public String getSmsCode(User customer) throws InterruptedException, IOException {
        return placeOrderPage.getSmsCode(customer);
    }

    @Step
    public void clickPlaceOrder() {
        placeOrderPage.clickConfirmButton();
    }

    @Step
    public void verifyMastersCountEquals(int i) {
        placeOrderPage.verifyMastersCount(i);
    }

    @Step
    public void verifyCategory(String text) {
        placeOrderPage.verifyCategory(text);
    }

    @Step
    public void clickCategoryTab() {
        placeOrderPage.clickCategoryTab();
    }

    @Step
    public void enterDescription() {
        placeOrderPage.enterDescription();
    }

    @Step
    public void enterPhoneNumber(User guest) {
        guest.setPhoneCode(placeOrderPage.getCountryCode());
        placeOrderPage.enterPhoneNumber(guest.getPhoneNumber());
    }

    @Step
    public void verifySuccessPageIsVisible() {
        placeOrderPage.waitForLoaderDisappears();
        placeOrderPage.verifySuccessPageIsVisible();
    }

    @Step
    public void clickFillRequest() {
        placeOrderPage.clickFillRequest();
    }

    public String getRequestId() {
        return placeOrderPage.getRequestId();
    }

    @Step
    public void verifyMastersCountMoreThan(int i) {
        placeOrderPage.verifyMastersCountMoreThan(i);
    }
}
