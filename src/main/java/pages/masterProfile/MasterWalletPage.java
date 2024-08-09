package pages.masterProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class MasterWalletPage extends MasterProfileBasePage {

    @FindBy(xpath = "//div[@class='wallet-tabs']")
    private WebElementFacade walletTabs;
    @FindBy(xpath = "//input[@id='form_data_sum']")
    private WebElementFacade amountInput;
    @FindBy(xpath = "//select[@id='form_data_payment']")
    private WebElementFacade paymentSystemSelect;
    @FindBy(xpath = "//form//button")
    private WebElementFacade submitFormButton;
    @FindBy(xpath = "//div[@class='wallet-tabs']/a[last()]")
    private WebElementFacade cardsSection;
    @FindBy(xpath = "//div[@id='btn-add-card']")
    private WebElementFacade addCardButton;
    @FindBy(xpath = "//form//input[@id='form_data_name']")
    private WebElementFacade cardNameInput;
    @FindBy(xpath = "//form//input[@id='form_data_number']")
    private WebElementFacade cardNumberInput;
    @FindBy(xpath = "//form//input[@id='form_data_expire']")
    private WebElementFacade cardExpiryDateInput;
    @FindBy(xpath = "//div[./div[@class='icon uzcard']]")
    private WebElementFacade uzcardCard;
    @FindBy(xpath = "//div[./div[@class='icon humo']]")
    private WebElementFacade humoCard;
    @FindBy(xpath = "//label[@for='form_data_is_main']")
    private WebElementFacade mainCheckbox;

    public void walletTabsShouldBeVisible() {
        walletTabs.shouldBeVisible();
    }

    public void enterAmount(String amount) {
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    public void selectClickSystem() {
        paymentSystemSelect.click();
        paymentSystemSelect.selectByIndex(1);
    }

    public void selectPaycomSystem() {
        paymentSystemSelect.click();
        paymentSystemSelect.selectByIndex(2);
    }

    public void clickSubmitFormButton() {
        submitFormButton.click();
    }

    public void verifyClickSystemPage() {
        assertTrue(getDriver().getCurrentUrl().contains("my.click.uz"));
    }

    public void verifyPaycomSystemPage() {
        var result = getDriver().getCurrentUrl().contains("payme") || getDriver().getCurrentUrl().contains("paycom");
        assertTrue(result);
    }

    public void clickCardsSection() {
        cardsSection.click();
    }

    public void clickAddCard() {
        addCardButton.click();
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

    public void verifyUzcardAdded() {
        uzcardCard.shouldBeVisible();
    }

    public void verifyHumoCardAdded() {
        humoCard.shouldBeVisible();
    }

    public void verifyUzcardIsMainCard() {
        var elements = uzcardCard.findElements(By.xpath("//div[@class='is_main']"));
        assertThat(elements.size()).isGreaterThan(0);
    }

    public void verifyHumoIsMainCard() {
        var elements = humoCard.findElements(By.xpath("//div[@class='is_main']"));
        assertThat(elements.size()).isGreaterThan(0);
    }

    public void selectCardAsMain() {
        mainCheckbox.click();
    }

    public void clickHumoOptions() {
        humoCard.findElement(By.xpath("//div[@class='more']"));
    }

    public void clickHumoDelete() {
        humoCard.findElement(By.xpath("//div[@class='more']//a[@class='btn-delete']"));
    }
}
