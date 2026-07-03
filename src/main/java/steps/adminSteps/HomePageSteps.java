package steps.adminSteps;

import net.serenitybdd.core.steps.UIInteractions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CatalogPage;
import pages.admin.AdminLoginPage;
import utils.Config;

public class HomePageSteps extends UIInteractions {

    private AdminLoginPage homePage;

    private static final Logger logger = LoggerFactory.getLogger(HomePageSteps.class);

    public void loginAsAdmin() {
        homePage.openPage();
        homePage.enterLogin(Config.getUsers().getAdmin().getEmail());
        logger.info(Config.getUsers().getAdmin().getEmail());
        logger.info(Config.getUsers().getAdmin().getPassword());
        homePage.enterPassword(Config.getUsers().getAdmin().getPassword());
        homePage.clickLoginBtn();
        homePage.waitForLogin();
    }
}
