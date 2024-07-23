package pages.customerProfile;

import static org.assertj.core.api.Assertions.*;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class CustomerProfilePersonalInfoPage extends CustomerProfileBasePage {

    @FindBy(xpath = "//input[@id='form_user_presentation']")
    private WebElementFacade userNameInput;
    @FindBy(xpath = "//input[@id='form_user_phone']")
    private WebElementFacade phoneNumberInput;
    @FindBy(xpath = "//a[@id='button-delete']")
    private WebElementFacade deleteProfileButton;
    @FindBy(xpath = "//div[.//div[contains(text(), 'Удалить профиль')]]//button[@class='btn-submit']")
    private WebElementFacade submitButton;
    @FindBy(xpath = "//div[@id='customer-masters']//div[@class='item']")
    private List<WebElementFacade> myMastersList;
    @FindBy(xpath = "//a[@id='button-password']")
    private WebElementFacade passwordButton;
    @FindBy(xpath = "//input[@name='password[password]']")
    private WebElementFacade passwordInput;
    @FindBy(xpath = "//input[@name='password[password_confirm]']")
    private WebElementFacade repeatPasswordInput;
    @FindBy(xpath = "//div[@class='window']//button")
    private WebElementFacade submitResetButton;
    @FindBy(xpath = "//div[@class='window-shadow']//div[@class='btn-close']")
    private WebElementFacade closeResetFormBtn;

    public void deleteBtnShouldBeVisible() {
        deleteProfileButton.shouldBeVisible();
    }

    public void verifyMyMastersListContains(String projectName) {
        boolean anyMatch = myMastersList.stream()
                .map(WebElementFacade::getText)
                .filter(Objects::nonNull)
                .anyMatch(item -> item.contains(projectName));

        assertThat(anyMatch).isTrue();
    }

    public void deleteProfile() {
        deleteProfileButton.click();
    }

    public void submitProfileDeleting() {
        submitButton.click();
    }

    public void verifyUserName(String phoneNumber) {
        assertThat(userNameInput.getValue()).contains(phoneNumber);
    }

    public void verifyPhoneNumber(String phoneNumber) {
        assertThat(phoneNumberInput.getValue().replaceAll("\\s+", "")).contains(phoneNumber);
    }

    public void clickChangePassword() {
        passwordButton.click();
    }

    public void typeNewPassword(String newPassword) {
        passwordInput.sendKeys(newPassword);
    }

    public void repeatNewPassword(String newPassword) {
        repeatPasswordInput.sendKeys(newPassword);
    }

    public void clickSaveNewPassword() {
        submitResetButton.click();
    }

    public void clickCloseChangePasswordForm() {
        closeResetFormBtn.click();
    }
}
