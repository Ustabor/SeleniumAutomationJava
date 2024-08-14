package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerServicesPage extends BasePage {
    @FindBy(xpath = "//div[@class='item' and not(@style)]")
    private WebElementFacade serviceItem;
    @FindBy(xpath = "//div[@class='item' and not(@style)]//a[@class='btn-order']")
    private WebElementFacade orderService;
    @FindBy(xpath = "//div[@class='item' and not(@style)]//a[@class='btn-more']")
    private WebElementFacade serviceDetailsButton;
    @FindBy(xpath = "//div[@class='service-item']")
    private WebElementFacade serviceDetails;
    @FindBy(xpath = "//div[@class='service-item']//a[@class='btn-order']")
    private WebElementFacade serviceDetailsOrderButton;
    @FindBy(xpath = "//div[@class='btn increase']")
    private WebElementFacade increaseCountButton;
    @FindBy(xpath = "//form//div[@class='price']")
    private WebElementFacade orderFormPrice;
    @FindBy(xpath = "//form//button[@class='btn-submit']")
    private WebElementFacade submitServiceOrder;
    @FindBy(xpath = "//form//input[@id='form_data_address']")
    private WebElementFacade address;
    @FindBy(xpath = "//div[@class='order-info']")
    private WebElementFacade orderConfirmationInfo;
    @FindBy(xpath = "//form//input[@id='form_data_name']")
    private WebElementFacade cardNameInput;
    @FindBy(xpath = "//form//input[@id='form_data_number']")
    private WebElementFacade cardNumberInput;
    @FindBy(xpath = "//form//input[@id='form_data_expire']")
    private WebElementFacade cardExpiryDateInput;
    @FindBy(xpath = "//form//input[@id='form_data_name']")
    private WebElementFacade userNameInput;
    @FindBy(xpath = "//form//input[@id='form_data_phone']")
    private WebElementFacade userPhoneInput;
    @FindBy(xpath = "//form//input[@id='form_data_address']")
    private WebElementFacade userAddressInput;
    @FindBy(xpath = "//input[@id='form_data_code']")
    private WebElementFacade orderConfirmationCode;
    @FindBy(xpath = "//div[@class='order-confirmed-content']")
    private WebElementFacade orderConfirmed;

    public void selectCategory(String name) {
        var xpath = String.format("//div[@class='service-categories']/a[text()='%s']", name);
        getDriver().findElement(By.xpath(xpath)).click();
    }

    public void verifyServiceName(String name) {
        var itemText = serviceItem.getText();
        assertThat(itemText.toLowerCase()).contains(name.split("\\s+")[0].toLowerCase());
    }

    public void verifyServicePrice(String price) {
        assertThat(serviceItem.getText().replaceAll(" ", "")).contains(price);
    }

    public void clickOrderService() {
        orderService.click();
    }

    public void increaseServicesCount() {
        increaseCountButton.click();
    }

    public void verifyOrderFormServicePrice(String price) {
        assertThat(orderFormPrice.getText().replaceAll( " ", "")).contains(price);
    }

    public void submitOrder() {
        submitServiceOrder.click();
    }

    public void enterAddress() {
        address.sendKeys("Test Address");
    }

    public void verifyPriceConfirmation(String price) {
        assertThat(orderConfirmationInfo.getText().replaceAll(" ", "")).contains(price);
    }

    public void verifyCustomerPhoneNumber(String customer) {
        orderConfirmationInfo.shouldContainText(customer);
    }

    public void enterCardName(String name) {
        cardNameInput.sendKeys(name);
    }

    public void enderCardNumber(String number) {
        cardNumberInput.sendKeys(number);
    }

    public void enterExpiryDate(String expiryDate) {
        cardExpiryDateInput.sendKeys(expiryDate);
    }

    public void clickDetails() {
        serviceDetailsButton.click();
    }

    public void verifyServiceDetailsName(String name) {
        var serviceName = serviceDetails.getText().toLowerCase();
        assertThat(serviceName).contains(name.toLowerCase());
    }

    public void verifyServiceDetailsDescription(String description) {
        serviceDetails.shouldContainText(description);
    }

    public void verifyServiceDetailsPrice(String price) {
        assertThat(serviceDetails.getText().replaceAll(" ", "")).contains(price);
    }

    public void verifyOrderConfirmationFormIsVisible() {
        cardNameInput.shouldBeVisible();
    }

    public void clickServiceDetailsOrder() {
        serviceDetailsOrderButton.click();
    }

    public void enterCustomerFirstName(String firstName) {
        userNameInput.sendKeys(firstName);
    }

    public void enterCustomerPhoneNumber(String phoneNumber) {
        userPhoneInput.sendKeys(phoneNumber);
    }

    public void enterCustomerAddress(String testAddress) {
        userAddressInput.sendKeys(testAddress);
    }

    public void enterOrderConfirmationCode(String code) {
        orderConfirmationCode.sendKeys(code);
    }

    public void verifyOrderConfirmedIsVisible() {
        orderConfirmed.shouldBeVisible();
    }
}
