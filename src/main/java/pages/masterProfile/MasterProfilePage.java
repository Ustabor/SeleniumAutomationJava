package pages.masterProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.components.FileToUpload;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MasterProfilePage extends MasterProfileBasePage {

    @FindBy(xpath = "//div[@class='presentation']")
    private WebElementFacade masterName;
    @FindBy(xpath = "//input[@id='form_user_presentation']")
    private WebElementFacade masterNameInput;
    @FindBy(xpath = "//input[@id='form_user_email']")
    private WebElementFacade masterEmailInput;
    @FindBy(xpath = "//input[@id='form_user_phone']")
    private WebElementFacade masterPhoneInput;
    @FindBy(xpath = "//select[@id='form_user_city_id']")
    private WebElementFacade masterCitySelect;
    @FindBy(xpath = "//select[@id='form_user_experience']")
    private WebElementFacade masterExperienceSelect;
    @FindBy(xpath = "//textarea")
    private WebElementFacade aboutMeInput;
    @FindBy(xpath = "//div[@class='p about']")
    private WebElementFacade aboutMeText;
    @FindBy(xpath = "//ul[@class='cities']")
    private WebElementFacade masterCity;
    @FindBy(xpath = "//ul[@class='categories']//li")
    private List<WebElementFacade> masterCategories;
    @FindBy(xpath = "//a[contains(@class, 'button-edit')]")
    private WebElementFacade profileSettingsBtn;
    @FindBy(xpath = "//div[@class=' user-profile']//div[contains(@class, 'rating-stars')]")
    private WebElementFacade masterRating;
    @FindBy(xpath = "//a[@href='#reviews']")
    private WebElementFacade masterReviews;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade saveButton;
    @FindBy(xpath = "//table[@class='services']//td[@class='main']/div")
    private WebElementFacade service;
    @FindBy(xpath = "//table[@class='services']//input[@type='text']")
    private WebElementFacade servicePrice;

    public void masterFullNameShouldContain(String firstName) {
        masterName.shouldContainText(firstName);
    }

    public void aboutMeTextShouldBeEqual(String aboutMe) {
        aboutMeText.shouldContainText(aboutMe);
    }

    public void verifyMasterCity(String city) {
        masterCity.shouldContainText(city);
    }

    public void openProfileSettings() {
        profileSettingsBtn.click();
    }

    public void masterRatingShouldBe(String rating) {
        assertThat(masterRating.getAttribute("class")).contains(rating);
    }

    public void masterFeedbackShouldContain(String feedback) {
        masterReviews.shouldContainText(feedback);
    }

    public void selectService() throws InterruptedException {
        service.click();
        servicePrice.sendKeys("100");
        Thread.sleep(1000); //Wait for backend response
    }

    public void enterName(String firstName) {
        masterNameInput.clear();
        masterNameInput.sendKeys(firstName);
    }

    public void enterEmail(String email) {
        masterEmailInput.sendKeys(email);
    }

    public void enterPhoneNumber(String phoneNumber) {
        masterPhoneInput.sendKeys(phoneNumber);
    }

    public void changeCity() {
        masterCitySelect.click();
        masterCitySelect.selectByIndex(4);
    }

    public void changeExperience() {
        masterExperienceSelect.click();
        masterCitySelect.selectByIndex(1);
    }

    public void enterAboutMeInfo(String aboutMe) {
        aboutMeInput.sendKeys(aboutMe);
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void masterAboutMeInfoShouldContain(String aboutMe) {
        aboutMeInput.shouldContainText(aboutMe);
    }

    public void masterNameInputShouldContain(String firstName) {
        assertThat(masterNameInput.getValue()).isEqualTo(firstName);
    }

    public void uploadProfileImage() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("$(\"[class='panel-body columns-wrap']\").append(\"<input type='file' id='form_user_image' tag='upload' name='user[image]'>\")");

        var fileInput = getDriver().findElement(By.xpath("//*[@tag='upload']"));
        var file = new File("files/house-icon.webp");
        var absolutePath = file.getAbsolutePath();

        fileInput.sendKeys(absolutePath);
        saveButton.click();
    }
}
