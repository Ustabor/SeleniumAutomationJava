package pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Config;
import utils.WaitHelper;
import utils.XmlParser;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class BasePage extends PageObject {

    private final String loaderXpath = "//div[contains(@class, 'loading')]";

    @FindBy(xpath = loaderXpath)
    private WebElementFacade loader;

    @FindBy(xpath = "//input[@id='form_confirmation_code']")
    private WebElementFacade submitCodeInput;

    @FindBy(xpath = "//div[@class='refresh-wrap']/a")
    private WebElementFacade repeatSendCode;

    //region Header elements
    @FindBy(xpath = "//div[@class='header']//a[@class='logo']")
    private WebElementFacade logoBtn;

    @FindBy(xpath = "//nav[@class='user']/a[1]")
    private WebElementFacade profileBtn;

    @FindBy(xpath = "//a[contains(@href, 'logout')]")
    private WebElementFacade logoutBtn;

    @FindBy(xpath = "//a[@href='#form-login']")
    private WebElementFacade openLoginFormBtn;

    @FindBy(xpath = "//div[@class='header']//nav[@class='nav-menu sites']")
    private WebElementFacade sitesMenuBtn;

    @FindBy(xpath = "//div[@class='header']//nav[@class='nav-menu sites']/div[@class='menu']/a")
    private List<WebElementFacade> sitesList;

    @FindBy(xpath = "//div[@class='header']//nav[@class='nav-menu countries']/div[contains(@class, 'label')]")
    private WebElementFacade headerCountriesBtn;

    @FindBy(xpath = "//ul[@class='countries']/li")
    private List<WebElementFacade> countriesList;

    @FindBy(xpath = "//button[@class='button-submit']")
    private WebElementFacade popupOkBtn;

    @FindBy(xpath = "//div[@id='header-phone-button']")
    private WebElementFacade phoneBtn;

    @FindBy(xpath = "//div[@class='header-phone']//div[@class='hint']")
    private WebElementFacade phonePopup;

    @FindBy(xpath = "//div[@class='header-inner']//nav[@class='nav-menu language']/div[contains(@class, 'label')]")
    private WebElementFacade headerLangBtn;

    @FindBy(xpath = "//div[@class='header-menu']//nav[@class='nav-menu language']//a")
    private List<WebElementFacade> langsList;

    @FindBy(xpath = "//div[./input[@id='catalog-input']]/button")
    private WebElementFacade placeOrderBtn;
    //endregion

    //region Footer elements
    @FindBy(xpath = "//div[@class='footer']//nav[@class='nav-menu language']//div[contains(@class, 'label')]")
    private WebElementFacade footerLangBtn;

    @FindBy(xpath = "//div[@class='footer']//nav[@class='nav-menu language']//a")
    private List<WebElementFacade> footerLangsList;

    @FindBy(xpath = "//div[@class='footer']//nav[@class='nav-menu countries']//div[contains(@class, 'label')]")
    private WebElementFacade footerCountryBtn;

    @FindBy(xpath = "//div[@class='footer']//nav[@class='nav-menu countries']//a")
    private List<WebElementFacade> footerCountriesList;
    //endregion

    //region Mobile view elements
    @FindBy(xpath = "//div[@class='header']//div[@id='button-sitemap']")
    private WebElementFacade mobileViewMenuBtn;
    //endregion

    @FindBy(xpath = "//div[@class='btn-close']")
    private WebElementFacade closePopupBtn;

    @FindBy(xpath = "//nav[@class='footer']/a[contains(@href,'sitemap')]")
    private WebElementFacade siteMapLink;

    @FindBy(xpath = "//div[@class='refresh-wrap']/a")
    private WebElementFacade resendCode;

    public void clickResendCode() {
        resendCode.click();
    }

    public boolean isRefreshLinkVisible() {
        setTimeouts(2, ChronoUnit.SECONDS);
        var state = resendCode.isVisible();
        resetTimeouts();
        return state;
    }

    public void setTimeouts(int duration, TemporalUnit timeUnit) {
        setImplicitTimeout(duration, timeUnit);
        setWaitForTimeout(duration * 1000);
    }

    public void resetTimeouts() {
        resetImplicitTimeout();
        setWaitForTimeout(15000);
    }

    public void waitForLoaderDisappears() {
        waitForLoaderDisappears(120000);
    }

    public void waitForServiceLoader() {
        setImplicitTimeout(500, ChronoUnit.MILLIS);
        setWaitForTimeout(500);

        var xpath = "//div[@class='loading']";
        var retry = 5;

        for (int i = 0; i < retry; i++) {
            var elements = getDriver().findElements(By.xpath(xpath));
            if (elements.isEmpty()) {
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void waitForLoaderDisappears(int timeoutMilli) {
        Serenity.throwExceptionsImmediately();
        setTimeouts(2, ChronoUnit.SECONDS);
        try {
            WaitHelper.pollingWait(timeoutMilli, 500, () -> !loader.isVisible());
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        resetTimeouts();
    }

    public void longWaitForDocument() throws TimeoutException {
        WaitHelper.pollingWait(
                120000,
                500,
                () -> (Boolean) evaluateJavascript("return document.readyState === 'complete'"));
    }

    public void openPageWithConfigUrl() throws TimeoutException {
        getDriver().get(Config.getFullUrl());
        longWaitForDocument();
        scrollPageUpJS();
    }

    public void openHomePage() {
        logoutBtn.waitUntilClickable();
        logoBtn.click();
    }

    public void openCustomerProfilePage() {
        getDriver().get(Config.getFullUrl() + "customer");
    }

    public void openMasterProfilePage() {
        getDriver().get(Config.getFullUrl() + "master");
    }

    public void logsOut() {
        for (int i=0;i<3;i++) {
            logoutBtn.waitUntilClickable();
            logoutBtn.click();
            if (openLoginFormBtn.isVisible()) { return; }
        }
    }

    boolean isLogoutBtnVisible() {
        setTimeouts(1, ChronoUnit.SECONDS);
        boolean result = logoutBtn.isVisible();
        resetTimeouts();
        return result;
    }

    public void logoutBtnShouldBeVisible() {
        assertThat(isLogoutBtnVisible()).isTrue();
    }

    protected void focusElementJS(WebElementFacade element) {
        evaluateJavascript("arguments[0].focus();", element);
    }

    protected void scrollPageUpJS() {
        evaluateJavascript("window.scrollTo(0, 0)");
    }

    void clearInputJS(WebElementFacade input) {
        evaluateJavascript("arguments[0].value = ''", input);
    }

    public List<String> getSitesNames() {
        sitesMenuBtn.click();
        return sitesList.stream().map(WebElementFacade::getText).collect(Collectors.toList());
    }

    public void openSubdomainDropDown() {
        sitesMenuBtn.click();
    }

    public void openSiteWithName(String siteName) {
        openSubdomainDropDown();
        WebElementFacade site = sitesList
                .stream()
                .filter(s -> s.getText().contains(siteName))
                .findFirst()
                .orElse(null);

        if (site == null) {
            throw new NullPointerException();
        }

        site.click();
    }

    public void correctSiteShouldBeVisible(String siteName) {
        String expectedUrl = Config.getFullUrl();
        if (expectedUrl.endsWith("/ru/")) {
            expectedUrl = expectedUrl.replace("/ru/", "");
        }

        var homeDomainKeyword = "SiteDomainHome";
        if (Config.isFixinglist()) {
            homeDomainKeyword += "_fixinglist";
        }

        if (siteName.equals(Objects.requireNonNull(XmlParser.getTextByKey("SiteDomainBuild_Short2"))) ||
                siteName.equals(Objects.requireNonNull(XmlParser.getTextByKey("SiteDomainBuild_Full")))) {
            assertThat(getDriver().getCurrentUrl()).contains(expectedUrl);
        } else if (siteName.contains(Objects.requireNonNull(XmlParser.getTextByKey("SiteDomainAuto")))) {
            assertThat(getDriver().getCurrentUrl()).contains("//auto.");
        } else if (siteName.contains(Objects.requireNonNull(XmlParser.getTextByKey("SiteDomainTech")))) {
            assertThat(getDriver().getCurrentUrl()).contains("//tech.");
        } else if (siteName.contains(Objects.requireNonNull(XmlParser.getTextByKey(homeDomainKeyword)))) {
            assertThat(getDriver().getCurrentUrl()).contains("//home.");
        } else if (siteName.contains(Objects.requireNonNull(XmlParser.getTextByKey("SiteDomainMaterials")))) {
            assertThat(getDriver().getCurrentUrl()).contains("//materials.");
        } else {
            throw new IllegalArgumentException(String.format("No such site with name %s found", siteName));
        }
        logoBtn.shouldBeVisible();
    }

    public void openSiteMap() {
        siteMapLink.click();
    }

    public void openHeaderCountriesDropDown() {
        headerCountriesBtn.click();
    }

    public List<String> getCountries() {
        openHeaderCountriesDropDown();
        return countriesList
                .stream()
                .map(x -> x.getAttribute("class"))
                .map(x -> x.substring(0, x.lastIndexOf(" ")))
                .collect(Collectors.toList());
    }

    public void setCountryByCode(String countryCode) {
        if (!isCountrySelectorVisible() || headerCountriesBtn.getAttribute("class").contains(countryCode)) {
            return;
        }

        openHeaderCountriesDropDown();
        WebElementFacade item = countriesList
                .stream()
                .filter(s -> s.getAttribute("class").contains(countryCode))
                .findFirst()
                .orElse(null);

        if (item == null) {
            throw new NullPointerException();
        }

        item.click();
    }

    public void currentDomainNameShouldBe(String country) {
        assertThat(headerCountriesBtn.getAttribute("class")).contains(country);
    }

    public void clickProfileBtn() {
        profileBtn.click();
    }

    public void openLoginForm() {
        openLoginFormBtn.click();
    }

    public boolean isCountrySelectorVisible() {
        setTimeouts(1, ChronoUnit.SECONDS);
        boolean result =  headerCountriesBtn.isCurrentlyVisible();
        resetTimeouts();
        return result;
    }

    public void clickHeaderPhoneBtn() {
        phoneBtn.click();
    }

    public void verifyHeaderPhonePopupText(String text) {
        phonePopup.shouldBeVisible();
        phonePopup.shouldContainText(text);
    }

    public void verifyHeaderCountriesPopup() {
        countriesList.forEach(WebElementState::shouldBeVisible);
    }

    public void openHeaderLangDropDown() {
        headerLangBtn.click();
    }

    public String getCurrentLang() {
        return headerLangBtn.getText();
    }

    public void verifyLangPopupIsVisible() {
        langsList.forEach(WebElementState::shouldBeVisible);
    }

    public void selectLanguage(String lang) {
        var language = langsList.stream().filter(x -> x.getText().contains(lang)).findFirst().orElse(null);
        language.click();
    }

    public void verifySubdomainsListIsVisible() {
        sitesList.forEach(WebElementState::shouldBeVisible);
    }

    public void openPlaceOrderForm() {
        placeOrderBtn.click();
    }

    public void scrollPageToTop() {
        evaluateJavascript("window.scrollTo(0,0);");
    }
    public void scrollPageToBottom() {
        evaluateJavascript("window.scrollTo(0,document.body.scrollHeight);");
    }

    public void openMobileViewMainMenu() {
        mobileViewMenuBtn.click();
    }

    public void clickCloseBtn() {
        if (closePopupBtn.isVisible()) {
            closePopupBtn.click();
        }
    }

    public void waitForSubmitCodeForm(){
        withTimeoutOf(Duration.ofSeconds(25)).waitFor(submitCodeInput).isPresent();
    }

    public void resendCode() {
        repeatSendCode.click();
    }

    public void verifyHeaderSite(String site) {
        sitesMenuBtn.shouldContainText(site);
    }
}
