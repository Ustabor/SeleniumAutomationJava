package UITests.Master.Registration;

import UITests.TestBase;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.DataGenerator;

import java.io.IOException;

@ExtendWith(SerenityJUnit5Extension.class)
public class MasterLoginTest extends TestBase {

    @BeforeEach
    public void setup() throws InterruptedException, IOException {
        var master = DataGenerator.getMaster();
        users.add(master);

        user.register(master, true);
    }

    @Test
    public void verifyMasterAccountLogin() {
        user.atMasterProfilePage.openProjectsTab();
        user.atMasterProjectsPage.pageShouldBeVisible();

        user.atMasterProjectsPage.openWalletTab();
        user.atMasterWalletPage.pageShouldBeVisible();

        user.atMasterWalletPage.openNotificationTab();
        user.atMasterNotificationsPage.pageShouldBeVisible();

        user.atMasterNotificationsPage.openPromotionTab();
        user.atMasterPromotionPage.pageShouldBeVisible();

        user.atMasterPromotionPage.openFaqTab();
        user.atMasterFaqPage.pageShouldBeVisible();
    }
}
