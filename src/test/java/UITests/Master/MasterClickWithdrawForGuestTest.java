package UITests.Master;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import entities.Master;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.masterProfile.MasterPromotionPage;

import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

//Гость - Списывание средств за клик

@WithTag("smoke")
@ExtendWith(SerenityJUnit5Extension.class)
@AddCategory(promotionAndClickPrice = true)
@AddMasters(masters = 1)
public class MasterClickWithdrawForGuestTest extends TestBase {

    private Master master;

    @BeforeEach
    public void setup() throws TimeoutException, URISyntaxException, InterruptedException {
        master = getMaster();
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

        admin.approvePromotion(master.getCategory());

        user.atHomePage.openHomePage();
        user.atCatalogPage.openMastersCatalog();
        user.atCatalogPage.selectFilterCategoryById(master.getCategory().getSystemId());
        user.atCatalogPage.openMasterContactsAndVerify(master.getFirstName());

        admin.atMastersPage.openMasterPage(master.getProfileId());
        admin.atMastersPage.verifyOnlyOneTransactionExist("-100");
    }
}
