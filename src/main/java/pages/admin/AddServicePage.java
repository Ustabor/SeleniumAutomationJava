package pages.admin;

import entities.Category;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import utils.Config;

public class AddServicePage extends BaseAdminPage {

    @FindBy(xpath = "//div[@class='locale-menu']")
    private WebElementFacade flagButton;
    @FindBy(xpath = "//li[@data-tab='tab-text']")
    private WebElementFacade textTab;
    @FindBy(xpath = "//li[@data-tab='tab-main']")
    private WebElementFacade mainTab;
    @FindBy(xpath = "//select[@id='form_data_category_id']")
    private WebElementFacade categories;
    @FindBy(xpath = "//input[@id='form_data_urlname']")
    private WebElementFacade serviceUrlPath;
    @FindBy(xpath = "//input[@id='form_data_name_ru']")
    private WebElementFacade serviceNameInputRu;
    @FindBy(xpath = "//input[@id='form_data_name_en']")
    private WebElementFacade serviceNameInputEn;
    @FindBy(xpath = "//input[@id='form_data_name_uz']")
    private WebElementFacade serviceNameInputUz;
    @FindBy(xpath = "//input[@id='form_data_price']")
    private WebElementFacade servicePriceInput;
    @FindBy(xpath = "//textarea[@id='form_data_text_ru']")
    private WebElementFacade serviceDescriptionRu;
    @FindBy(xpath = "//textarea[@id='form_data_text_en']")
    private WebElementFacade serviceDescriptionEn;
    @FindBy(xpath = "//textarea[@id='form_data_text_uz']")
    private WebElementFacade serviceDescriptionUz;
    @FindBy(xpath = "//input[@id='form_data_is_countable']")
    private WebElementFacade isCountableCheckbox;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade submit;

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "ustabor-services/create");
    }

    public void createService(Category category) {
        categories.click();
        categories.selectByValue(category.getSystemId());
        serviceUrlPath.sendKeys(category.getService().getUrl());
        servicePriceInput.sendKeys(String.valueOf(category.getService().getPrice()));
        isCountableCheckbox.click();

        serviceNameInputRu.sendKeys(category.getService().getName());
        textTab.click();

        enterIframeText("ru", category.getService().getDescription());
        mainTab.click();

        switchLang("en");
        serviceNameInputEn.sendKeys(category.getService().getName());
        textTab.click();
        enterIframeText("en", category.getService().getDescription());
        mainTab.click();

        switchLang("uz");
        serviceNameInputUz.sendKeys(category.getService().getName());
        textTab.click();
        enterIframeText("uz", category.getService().getDescription());
        mainTab.click();

        submit.click();
    }

    private void enterIframeText(String lang, String text) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var xpath = String.format("//iframe[@id='form_data_text_%s_ifr']", lang);
        var iFrame = getDriver().findElement(By.xpath(xpath));
        getDriver().switchTo().frame(iFrame);

        getDriver().findElement(By.xpath("//body")).sendKeys(text);

        getDriver().switchTo().defaultContent();
    }

    private void switchLang(String lang) {
        String xpath = "//div[@data-code='%s']";

        flagButton.click();
        getDriver().findElement(By.xpath(String.format(xpath, lang))).click();
        flagButton.click();
    }
}
