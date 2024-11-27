package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.util.List;

public class RequestsPage extends BaseAdminPage {

    @FindBy(xpath = "//a[@id='btn-master-find']")
    private WebElementFacade assignMasterBtn;
    @FindBy(xpath = "//form//input[@class='ui-autocomplete-input']")
    private WebElementFacade masterNameInput;
    @FindBy(xpath = "//li[@class='ui-menu-item']")
    private WebElementFacade masterSuggestion;
    @FindBy(xpath = "//div[@class='exists-block']//div[@class='menu-btn']")
    private WebElementFacade actionsBtn;
    @FindBy(xpath = "//a[@data-action='add']")
    private WebElementFacade freeAdd;
    @FindBy(xpath = "//a[@data-action='add-pay']")
    private WebElementFacade paymentAdd;
    @FindBy(xpath = "//a[@data-action='remove']")
    private WebElementFacade remove;
    @FindBy(xpath = "//button[@class='button-submit']")
    private WebElementFacade submitMasterAssignBtn;
    @FindBy(xpath = "//ul[contains(@class, 'ui-autocomplete')]/li")
    private WebElementFacade autocompleteSuggestion;
    @FindBy(xpath = "//select[@id='form_data_type']")
    private WebElementFacade assignToDropDown;
    @FindBy(xpath = "//input[@id='form_data_payment']")
    private WebElementFacade withdrawContactPrice;
    @FindBy(xpath = "//div[@class='btn-menu']")
    private WebElementFacade menuBtn;
    @FindBy(xpath = "//a[contains(@href, 'delete')]")
    private WebElementFacade deleteBtn;
    @FindBy(xpath = "//textarea[@id='form_data_note']")
    private WebElementFacade deleteReason;
    @FindBy(xpath = "//button[contains(@class, 'btn-submit')]")
    private WebElementFacade submitBtn;
    @FindBy(xpath = "//select[@id='request_category_id']")
    private WebElementFacade categorySelector;
    @FindBy(xpath = "//select[@id='request_city_id']")
    private WebElementFacade citySelector;
    @FindBy(xpath = "//input[@id='request_username']")
    private WebElementFacade nameInput;
    @FindBy(xpath = "//input[@id='request_address']")
    private WebElementFacade addressInput;
    @FindBy(xpath = "//div[@class='btn-close']")
    private WebElementFacade closeButton;
    @FindBy(xpath = "//div[@id='masters-grid']//tr[@data-id]")
    private List<WebElementFacade> assignedMasters;

    public void openPage(String requestId) {
        getDriver().get(Config.getAdminUrl() + String.format("request/%s",requestId));
    }

    public void openEditPage(String requestId) {
        getDriver().get(Config.getAdminUrl() + String.format("request/%s/edit",requestId));
    }

    public void verifyCustomerLogin(String firstName) {
    }

    public void verifyCategory(String name) {
    }

    public void verifyWhatToDoQuestion(String question) {
    }

    public void verifyCustomerName(String firstName) {
    }

    public void verifyAddress(String city) {
    }

    public void verifyPhoneNumber(String phoneNumber) {
    }

    public void openAssignMasterForm() {
        assignMasterBtn.click();
    }

    public void findMaster(String masterId) {
        masterNameInput.sendKeys(masterId);
        masterSuggestion.click();
    }

    public void submitMasterAssign() {
        submitMasterAssignBtn.click();
    }

    public void setAssignToAllMasters() {
        assignToDropDown.selectByValue("category");
    }

    public void setWithdrawContactPrice() {
        withdrawContactPrice.click();
    }

    public void deleteRequest() {
        deleteBtn.click();
        deleteReason.sendKeys("test");
        submitBtn.click();
    }

    public void assignForFree() {
        actionsBtn.click();
        freeAdd.click();
    }

    public void clickMenu() {
        menuBtn.click();
    }

    public void assignForPayment() {
        actionsBtn.click();
        paymentAdd.click();
    }

    public void resetAssign() {
        actionsBtn.click();
        remove.click();
        waitForLoaderDisappears(3000);
    }

    public void selectCategory(String categoryId) {
        categorySelector.selectByValue(categoryId);
    }

    public void selectCity() {
        citySelector.selectByIndex(1);
    }

    public void enterName() {
        nameInput.sendKeys("Test User");
    }

    public void enterAddress() {
        addressInput.sendKeys("Test Address");
    }

    public void clickSubmit() {
        submitBtn.click();
    }

    public void closeAssignMasterForm() {
        closeButton.click();
    }

    public boolean isMasterAssigned() {
        return !assignedMasters.isEmpty();
    }
}
