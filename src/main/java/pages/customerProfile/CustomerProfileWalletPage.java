package pages.customerProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerProfileWalletPage extends CustomerProfileBasePage {
    @FindBy(xpath = "//a[@class='wallet']")
    private WebElementFacade walletTabs;
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
    @FindBy(xpath = "//form//button")
    private WebElementFacade submitFormButton;

    public void clickWalletTab() {
        walletTabs.click();
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

    public void clickSubmitFormButton() {
        submitFormButton.click();
    }
}
