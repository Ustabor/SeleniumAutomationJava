package UITests.Master;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import entities.Master;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.masterProfile.MasterPromotionPage;

import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

//Гость - Списывание средств за клик

@WithTag("smoke")
@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true)
@AddMasters(masters = 1)
public class MasterClickWithdrawForGuestTest extends TestBase {

    private Master master;

    @Before
    public void setup() throws TimeoutException, URISyntaxException, InterruptedException {
        master = watcher.getMaster();
        admin.addMoneyToMaster(10000, master, false);
        user.addMasterProfileImage(master, false);
    }

    @Test
    public void withdrawMoneyOnClick() throws Exception {
        user.atMasterProfilePage.openProfilePage();
        user.atMasterPromotionPage.openPromotionTab();
        user.atMasterPromotionPage.promoteCategory(
                master.getCategory().getName(),
                MasterPromotionPage.PromotionType.MinimalPrice);
        user.atMasterProjectsPage.logsOut();

        admin.atAdminHomePage.loginAsAdmin();
        admin.atPromotionPage.approvePromotion(master.getCategory());

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(master.getCategory().getName());
        user.atCatalogPage.openMasterContactsAndVerify(master.getFirstName());

        admin.atMastersPage.openMasterPage(master.getProfileId());
        admin.atMastersPage.verifyOnlyOneTransactionExist("-100");
    }
}
