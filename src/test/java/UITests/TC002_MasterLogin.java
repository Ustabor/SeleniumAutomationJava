package UITests;

import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

@RunWith(SerenityRunner.class)
public class TC002_MasterLogin extends TestBase {

    @Test
    public void verifyMasterAccountLogin() throws InterruptedException {
        var master = DataGenerator.getMaster();
        watcher.users.add(master);

        user.register(master, true);

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
