package UITests.Master.Registration;

import UITests.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.io.IOException;

@RunWith(SerenityRunner.class)
public class MasterLoginTest extends TestBase {

    @Before
    public void setup() throws InterruptedException, IOException {
        var master = DataGenerator.getMaster();
        watcher.users.add(master);

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
