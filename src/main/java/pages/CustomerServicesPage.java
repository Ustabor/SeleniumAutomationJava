package pages;

import io.cucumber.java.zh_cn.假如;
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

    public void selectCategory(String name) {
        var xpath = String.format("//div[@class='service-categories']/a[text()='%s']", name);
        getDriver().findElement(By.xpath(xpath)).click();
    }

    public void verifyServiceName(String name) {
        serviceItem.shouldContainText(name);
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
        orderFormPrice.shouldContainText(price);
    }

    public void submitOrder() {
        submitServiceOrder.click();
    }

    public void enterAddress() {
        address.sendKeys("Test Address");
    }

    public void verifyPriceConfirmation(String price) {
        orderConfirmationInfo.shouldContainText(price);
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
        serviceDetails.shouldContainText(name);
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
}
