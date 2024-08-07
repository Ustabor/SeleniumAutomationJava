package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.util.List;

public class AddRequestPage extends BaseAdminPage {

    @FindBy(xpath = "//input[@name='data[name][ru]']")
    private WebElementFacade serviceName;

    @FindBy(xpath = "//select[@id='form_data_category_id']")
    private WebElementFacade serviceCategory;

    @FindBy(xpath = "//input[@name='data[question][ru]']")
    private WebElementFacade serviceHeader;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade submit;

    @FindBy(xpath = "//li[@data-tab='tab-prices']")
    private WebElementFacade pricesTab;

    @FindBy(xpath = "//input[contains(@name, 'prices')]")
    private List<WebElementFacade> priceInput;


    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "request/service/create");
    }

    public void enterName(String name) {
        serviceName.sendKeys(name);
    }

    public void selectCategory(String categoryName) {
        serviceCategory.selectByVisibleText(categoryName);
    }

    public void enterHeader(String header) {
        serviceHeader.sendKeys(header);
    }

    public void saveService() {
        submit.click();
    }

    public void setPrices() {
        pricesTab.click();
        for (WebElementFacade input: priceInput) {
            input.sendKeys("100");
        }
    }
}
