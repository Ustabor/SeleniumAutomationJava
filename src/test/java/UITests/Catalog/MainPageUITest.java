package UITests.Catalog;

import UITests.TestBase;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.Config;

import java.util.concurrent.TimeoutException;

@WithTag("smoke")
@ExtendWith(SerenityJUnit5Extension.class)
public class MainPageUITest extends TestBase {

    @Test
    public void mainPageUiTest() throws TimeoutException {
        if (Config.isFixinglist()) user.atHomePage.openLocationPopupAndVerifyCountries();

        user.atHomePage.verifySubdomainDropDown();
        user.atHomePage.verifyHeaderLanguagesListIsVisible();

        user.atHomePage.verifyLoginForm();
        user.atHomePage.verifyRegistrationForm();
        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.verifyPage();

        user.atHomePage.verifyRandomFaqItem();

        setBrowserMobileWindowSize();
        user.atHomePage.openMobileViewMainMenu();

        if (!Config.isUstabor() && !Config.isBildrlist()) {
            user.atHomePage.verifyMobileViewCountriesMenu();
        }

        user.atHomePage.openPlaceOrderPage();
        user.atHomePage.verifyMobileViewCustomerRegistrationForm();
    }
}
