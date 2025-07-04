package pages;

import entities.Master;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends SearchBlock {

    private static final String countryCityXpath = "//div[@class='window window-region']//li[text()='%s']";

    //region Common elements
    @FindBy(xpath = "//div[contains(@class,'customer')]")
    private WebElementFacade iAmCustomerBtn;

    @FindBy(xpath = "//form//div[@class='master']")
    private WebElementFacade iAmMasterBtn;

    @FindBy(xpath = "//div[contains(@class,'item site-www')]/div[@class='name']")
    private WebElementFacade builderTab;

    @FindBy(xpath = "//div[@id='btn-region']")
    private WebElementFacade locationBtn;

    @FindBy(xpath = "//div[@class='popup region-popup']//button[@class='btn btn-default']")
    private WebElementFacade changeLocation;
    //endregion

    //region Login form
    @FindBy(xpath = "//input[@id='form_login_login']")
    private WebElementFacade signInFormLoginInput;

    @FindBy(xpath = "//input[@id='form_login_password']")
    private WebElementFacade signInFormPasswordInput;

    @FindBy(xpath = "//form[@id='form-login']//button[@type='submit']")
    private WebElementFacade signInFormLoginBtn;

    @FindBy(xpath = "//a[@class='link-register']")
    private WebElementFacade signInFormRegisterLink;

    @FindBy(xpath = "//a[@href='#window-recovery']")
    private WebElementFacade forgotPasswordLink;

    @FindBy(xpath = "//form[@id='form-login']//div[@class='form-errors']")
    private WebElementFacade loginError;
    //endregion

    //region Customer registration form
    @FindBy(xpath = "//input[@id='form_registration_user_presentation']")
    private WebElementFacade regFormUserNameInput;

    @FindBy(xpath = "//input[@id='form_registration_user_phone']")
    private WebElementFacade regFormUserPhoneNumberInput;

    @FindBy(xpath = "//input[@type='email']")
    private WebElementFacade regFormEmailInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElementFacade regFormPasswordInput;

    @FindBy(xpath = "//input[@type='password' and contains(@name, 'password_confirm')]")
    private WebElementFacade regFormConfirmPasswordInput;

    @FindBy(xpath = "//form[@id='form-registration-customer']//button[@type='submit']")
    private WebElementFacade regFormSubmitBtn;

    @FindBy(xpath = "//input[@id='form_confirmation_code']")
    private WebElementFacade regFormConfirmationCodeInput;

    @FindBy(xpath = "//form[@id='form-confirmation']//button[@type='submit']")
    private WebElementFacade regFormSubmitCodeBtn;

    @FindBy(xpath = "//div[contains(@class, 'field-rules')]//a")
    private WebElementFacade regFormRulesLink;
    //endregion

    //region Master registration form
    private static final String registrationFormXpath = "//form[@id='form-registration-master']";
    @FindBy(xpath = registrationFormXpath)
    private WebElementFacade masterRegistrationForm;

    @FindBy(xpath = registrationFormXpath)
    private List<WebElementFacade> masterRegistrationForm1;

    @FindBy(xpath = "//input[@id='form_registration_master_name']")
    private WebElementFacade regMasterNameInput;
    @FindBy(xpath = "//input[@id='form_registration_master_email']")
    private WebElementFacade regMasterEmailInput;
    @FindBy(xpath = "//input[@id='form_registration_master_surname']")
    private WebElementFacade regMasterSurnameInput;

    @FindBy(xpath = "//input[@id='form_registration_master_phone']")
    private WebElementFacade regMasterPhoneNumber;

    @FindBy(xpath = "//textarea[@id='form_registration_master_about']")
    private WebElementFacade regMasterAboutMeInput;

    @FindBy(xpath = "//select[@id='form_registration_master_site_id']/../div")
    private WebElementFacade regMasterDomainSelector;

    @FindBy(xpath = "//div[@class='ui-selectbox-popup']//li[1]")
    private WebElementFacade regMasterDomainSelectorFirst;

    @FindBy(xpath = "//div[@class='categories-list']//a")
    private List<WebElementFacade> regMasterCategoriesList;

    @FindBy(xpath = "//div[@class='categories-list']//a")
    private WebElementFacade regMasterFirstCategory;

    @FindBy(xpath = "//select[@id='form_registration_master_experience']/../div")
    private WebElementFacade regMasterExperienceSelector;

    @FindBy(xpath = "//div[@class='ui-selectbox-popup']//li[2]")
    private WebElementFacade regMasterExperienceSelectorFirst;

    @FindBy(xpath = "//div[contains(@class, 'field-city_id')]")
    private WebElementFacade regMasterCitySelector;

    @FindBy(xpath = "//div[@class='ui-selectbox-popup']//li[2]")
    private WebElementFacade regMasterCitySelectorFirst;

    @FindBy(xpath = "//form[@method='post']//button[@type='submit']")
    private WebElementFacade regMasterSubmitBtn;
    //endregion

    //region Mobile view elements
    @FindBy(xpath = "//div[@class='sitemap']//div[contains(@class, 'btn-region')]")
    private WebElementFacade mobileMenuCountryBtn;

    @FindBy(xpath = "//div[@class='window window-region']")
    private WebElementFacade mobileCountryPopup;

    @FindBy(xpath = "//div[@class='header-inner']//nav[contains(@class, 'language')]")
    private WebElementFacade mobileMenuLangBtn;

    @FindBy(xpath = "//div[@class='sitemap']//nav[contains(@class, 'language')]//a")
    private List<WebElementFacade> mobileMenuLangsList;

    @FindBy(xpath = "//div[@class='sitemap']//nav[contains(@class, 'sites')]")
    private WebElementFacade mobileMenuSitesBtn;

    @FindBy(xpath = "//div[@class='sitemap']//nav[contains(@class, 'sites')]//a")
    private List<WebElementFacade> mobileMenuSitesList;

    @FindBy(xpath = "//div[@class='sitemap']//div[@id='sitemap-phone-button']")
    private WebElementFacade mobileMenuPhoneBtn;

    @FindBy(xpath = "//div[@class='window-body']")
    private WebElementFacade mobileMenuPhonePopup;

    @FindBy(xpath = "//div[@class='btn-contact']/a[@class='btn-login']")
    private WebElementFacade mobileMenuCustomerRegistrationBtn;

    @FindBy(xpath = "//a[@class='link-register']")
    private WebElementFacade mobileRegisterLink;

    @FindBy(xpath = "//div[@class='sitemap']//a[contains(@class, 'btn-master')]")
    private WebElementFacade mobileMenuMasterRegistrationBtn;
    //endregion

    @FindBy(xpath = "//div[contains(@class, 'categories-tabs')]//a[@id='categories-catalog-btn']")
    private WebElementFacade viewFullCatalogBtn;

    @FindBy(xpath = "//div[@class='window feedback-window']//a[@class='button btn-submit']")
    private WebElementFacade leaveFeedbackBtn;

    @FindBy(xpath = "//div[@class='site-categories-popup']//div[contains(@class,'items')]/a")
    private List<WebElementFacade> categoriesList;

    @FindBy(xpath = "//input[@id='form_data_login']")
    private WebElementFacade forgotPasswordEmailInput;

    @FindBy(xpath = "//form[@id='form-password']//button[@type='submit']")
    private WebElementFacade restorePasswordBtn;

    @FindBy(xpath = "//div[@class='row row-faq']//div[@class='item']")
    private List<WebElementFacade> faqItemsList;

    @FindBy(xpath = "//div[@class='row row-faq']//div[@class='item']")
    private WebElementFacade faqItem;

    @FindBy(xpath = "//div[@class='row row-faq']//div[@class='item expanded']//div[@class='text']")
    private WebElementFacade faqOpenedItemText;

    @FindBy(xpath = "//div[@class='ad-banner-popup']")
    private WebElementFacade adBanner;

    @FindBy(xpath = "//div[@class='ad-banner-popup']/div")
    private WebElementFacade adBannerCloseBtn;

    public void clickRegistrationLink() {
        signInFormRegisterLink.click();
    }

    public void openRegistrationForm() {
        openLoginForm();
        clickRegistrationLink();
    }

    //region Customer registration form
    public void regFormEnterUserName(String userName) {
        regFormUserNameInput.sendKeys(userName);
    }

    public void regFormEnterUserPhone(String phone) {
        regFormUserPhoneNumberInput.click();
        regFormUserPhoneNumberInput.sendKeys(phone);
    }

    public void regFormEnterPassword(String password) {
        regFormPasswordInput.sendKeys(password);
        regFormConfirmPasswordInput.sendKeys(password);
    }

    public void regFormClickSubmit() {
        focusElementJS(regFormSubmitBtn);
        regFormSubmitBtn.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void regFormEnterAuthCode(String code) {
        regFormConfirmationCodeInput.sendKeys(code);
    }

    public void regFormClickSubmitAuthCode() {
        regFormSubmitCodeBtn.click();
    }
    //endregion

    //region Master registration form methods
    public void regMasterFormEnterEmail(String email) {
        regMasterEmailInput.sendKeys(email);
    }
    public void regMasterFormEnterFirstName(String name) {
        regMasterNameInput.sendKeys(name);
    }

    public void regMasterFormEnterLastName(String surname) {
        regMasterSurnameInput.sendKeys(surname);
    }

    public void regMasterFormEnterAboutMe(String aboutMe) {
        regMasterAboutMeInput.sendKeys(aboutMe);
    }

    public void regMasterFormSelectBuildSubDomain() {
        regMasterDomainSelector.click();
        regMasterDomainSelectorFirst.click();
    }

    public void regMasterFormSelectRandomCategory(Master master) {
        regMasterFirstCategory.waitUntilVisible();
        WebElementFacade category = regMasterCategoriesList.get(new Random().nextInt(regMasterCategoriesList.size()));
        focusElementJS(category);
        category.click();

        master.getCategory().setName(category.getText());
        master.getCategory().setSystemId(category.getAttribute("data-id"));
    }

    public void regMasterFormSelectCategory(Master master) {
        withTimeoutOf(Duration.ofSeconds(25)).waitFor(regMasterFirstCategory).isClickable();
        WebElementFacade category = regMasterCategoriesList
                .stream()
                .filter(x -> x.getText().trim().equals(master.getCategory().getName()))
                .findFirst()
                .orElse(null);

        if (category == null) {
            throw new NullPointerException(String.format("No category found: %s", master.getCategory().getName()));
        }

        focusElementJS(category);
        category.click();
    }

    public void regMasterFormEnterPhoneNumber(String phoneNumber) {
        regMasterPhoneNumber.sendKeys(phoneNumber);
    }

    public void regMasterFormSelectExperience() {
        regMasterExperienceSelector.click();
        regMasterExperienceSelectorFirst.click();
    }

    public String regMasterFormSelectCity() throws InterruptedException {
        Thread.sleep(5000);
        regMasterCitySelector.click();
        Thread.sleep(3000);
        regMasterCitySelectorFirst.click();
        return regMasterCitySelector.getText();
    }

    public void regMasterClickSubmit() {
        focusElementJS(regMasterSubmitBtn);
        regMasterSubmitBtn.click();
    }
    //endregion

    //region SignIn form methods
    public void signInFormEnterLogin(String login) {
        clearInputJS(signInFormLoginInput);
        signInFormLoginInput.sendKeys(login);
    }

    public void signInFormEnterPassword(String password) {
        clearInputJS(signInFormPasswordInput);
        signInFormPasswordInput.sendKeys(password);
    }

    public void signInFormClickLoginBtn() {
        signInFormLoginBtn.click();
    }
    //endregion

    //region Forgot password form methods
    public void openForgotPasswordForm() {
        forgotPasswordLink.click();
    }

    public void forgotPasswordEnterPhone(String email) {
        forgotPasswordEmailInput.sendKeys(email);
    }

    public void forgotPasswordClickRestoreBtn() {
        restorePasswordBtn.click();
    }
    //endregion

    //region Validation methods

    public void iAmMasterBtnShouldBeVisible() {
        iAmMasterBtn.shouldBeVisible();
    }

    public void iAmCustomerBtnShouldBeVisible() {
        iAmCustomerBtn.shouldBeVisible();
    }

    public void registerLinkShouldBeVisible() {
        signInFormRegisterLink.shouldBeVisible();
    }

    public void loginInputShouldBeVisible() {
        signInFormLoginInput.shouldBeVisible();
    }

    public void passwordInputShouldBeVisible() {
        signInFormPasswordInput.shouldBeVisible();
    }

    public void loginBtnShouldBeVisible() {
        signInFormLoginBtn.shouldBeVisible();
    }

    public void forgotPasswordLinkShouldBeVisible() {
        forgotPasswordLink.shouldBeVisible();
    }

    public void registerFormShouldBeVisible() {
        regMasterNameInput.shouldBeVisible();
    }

    public void verifyFaqItemTextIsVisible() {
        faqOpenedItemText.shouldBeVisible();
    }

    public void verifyMobileViewCountriesMenu() {
        mobileCountryPopup.shouldBeVisible();
    }

    public void verifyMobileViewLanguagesMenu() {
        mobileMenuLangsList.forEach(WebElementState::shouldBeVisible);
    }

    public void verifyMobileViewPhoneFormText(String text) {
        mobileMenuPhonePopup.shouldContainText(text);
    }

    public void waitUntilFeedbackPopupIsVisible() {
        leaveFeedbackBtn.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
    }

    public void loginErrorWithTextShouldBeVisible(String text) {
        assertThat(loginError.getText()).isEqualTo(text);
    }
    //endregion

    //region Mobile view methods
    public void openMobileViewCountryMenu() {
        mobileMenuCountryBtn.click();
    }

    public void openMobileViewLangMenu() {
        mobileMenuLangBtn.click();
    }

    public void openMobileViewPhoneForm() {
        mobileMenuPhoneBtn.click();
    }

    public void openMobileViewCustomerRegistrationForm() {
        mobileMenuCustomerRegistrationBtn.click();
        mobileRegisterLink.click();
    }
    //endregion


    public void clickCustomerBtn() {
        iAmCustomerBtn.click();
    }

    public void clickMasterBtnRegister() {
        iAmMasterBtn.click();
        withTimeoutOf(Duration.ofSeconds(25)).waitFor(regFormEmailInput).isPresent();
    }

    public boolean isLoggedIn() {
        return isLogoutBtnVisible();
    }

    public void clickLeaveFeedback() {
        leaveFeedbackBtn.click();
    }

    public void openRandomFaqItem() {
        faqItem.shouldBeVisible();
        faqItemsList
                .get(new Random().nextInt(faqItemsList.size()))
                .findElement(By.xpath(".//i"))
                .click();
    }

    public void regFormSubmitBtnShouldBeVisible() {
        regFormSubmitBtn.shouldBeVisible();
    }

    public void regFormUserNameInputShouldBeVisible() {
        regFormUserNameInput.shouldBeVisible();
    }

    public void regFormPhoneNumberInputShouldBeVisible() {
        regFormUserPhoneNumberInput.shouldBeVisible();
    }

    public void regFormConditionsLinkShouldBeVisible() {
        regFormRulesLink.shouldBeVisible();
    }

    public void clickBuilderTab() {
        builderTab.click();
    }

    public void openCategory(String category) {
        var foundCategory = categoriesList
                .stream()
                .filter(e -> e.getText().trim().equals(category))
                .findAny()
                .orElse(null);

        if (foundCategory == null) {
            throw new NullPointerException(String.format("No element with text '%s' found", category));
        }

        focusElementJS(foundCategory);
        foundCategory.click();
    }

    public void setLanguage(String lang) {
        if (getCurrentLang().toLowerCase().equals(lang)) {
            return;
        }

        openHeaderLangDropDown();
        selectLanguage(lang);
    }

    public String getMasterPhoneCountryCode() {
        return regMasterPhoneNumber.getAttribute("placeholder").replaceAll("[^\\d]", "");
    }

    public String getCustomerPhoneCountryCode() {
        return regFormUserPhoneNumberInput.getAttribute("placeholder").replaceAll("[^\\d]", "");
    }

    public void openLocationPopup() {
        locationBtn.click();
    }

    public void selectLocation(String country, String city) {
        changeLocation.click();
        getDriver()
                .findElement(By.xpath(String.format(countryCityXpath, country)))
                .click();
        getDriver()
                .findElement(By.xpath(String.format(countryCityXpath, city)))
                .click();
    }

    public void selectCity(String city) {
        changeLocation.click();
        getDriver()
                .findElement(By.xpath(String.format(countryCityXpath, city)))
                .click();
    }

    public void verifyUserLoggedOut() {
        assertThat(isLoggedIn()).isFalse();
    }
}
