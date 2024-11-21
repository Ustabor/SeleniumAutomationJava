package UITests.Catalog;

import UITests.TestBase;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

import java.util.concurrent.TimeoutException;

@WithTag("smoke")
@RunWith(SerenityRunner.class)
public class MainPageUITest extends TestBase {

    @Test
    public void mainPageUiTest() throws TimeoutException {
        if (Config.isFixinglist()) user.atHomePage.openLocationPopupAndVerifyCountries();

        user.atHomePage.verifySubdomainDropDown();
        user.atHomePage.verifyHeaderLanguagesListIsVisible();

        if (!Config.isUstabor() && !Config.isBildrlist() && !Config.isFixinglist()) {
            user.atHomePage.verifyPhonePopUpText(getText("PhoneHintPopupText_" + Config.getEnv()));
        }

        user.atHomePage.verifyLoginForm();
        user.atHomePage.verifyRegistrationForm();
        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.verifyPage();

        user.atHomePage.verifyRandomFaqItem();

        setBrowserMobileWindowSize();
        user.atHomePage.openMobileViewMainMenu();

        if (!Config.isFixListKg() || !Config.isNewTest()) {
            user.atHomePage.verifyMobileViewLanguageMenu();
        }

        if (!Config.isUstabor() && !Config.isBildrlist()) {
            user.atHomePage.verifyMobileViewCountriesMenu();
        }

        user.atHomePage.openPlaceOrderPage();
        user.atHomePage.verifyMobileViewCustomerRegistrationForm();
    }
}
