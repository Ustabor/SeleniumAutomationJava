package pages.admin;

import entities.Category;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

public class AddServicePage extends BaseAdminPage {

    @FindBy(xpath = "//select[@id='form_data_category_id']")
    private WebElementFacade categories;
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
        serviceNameInputRu.sendKeys(category.getService().getName());
        serviceNameInputEn.sendKeys(category.getService().getName());
        serviceNameInputUz.sendKeys(category.getService().getName());
        servicePriceInput.sendKeys(String.valueOf(category.getService().getPrice()));
        serviceDescriptionRu.sendKeys(category.getService().getDescription());
        serviceDescriptionEn.sendKeys(category.getService().getDescription());
        serviceDescriptionUz.sendKeys(category.getService().getDescription());
        isCountableCheckbox.click();
        submit.click();
    }
}
