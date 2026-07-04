package UITests.Master;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import entities.Master;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.masterProfile.MasterPromotionPage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

@ExtendWith(SerenityJUnit5Extension.class)
@AddCategory(promotionAndClickPrice = true)
@AddMasters(masters = 2)
public class MasterPromoteWithRecommendedPriceTest extends TestBase {

    private Master master;

    @BeforeEach
    public void setup() throws TimeoutException, URISyntaxException, InterruptedException {
        master = getMaster(0);
        admin.addMoneyToMaster(10000, master, false);

        user.addMasterProfileImage(getMaster(1), true);
        user.addMasterProfileImage(master, false);
    }

    @Test
    public void promoteWithRecommendedPrice() throws TimeoutException, IOException {
        user.atMasterProfilePage.openProfilePage();
        user.atMasterPromotionPage.openPromotionTab();
        user.atMasterPromotionPage.promoteCategory(
                master.getCategory().getName(),
                MasterPromotionPage.PromotionType.RecommendedPrice);
        user.atMasterProjectsPage.logsOut();

        admin.approvePromotion(master.getCategory());

        user.atHomePage.openHomePage();
        user.atCatalogPage.openMastersCatalog();
        user.atCatalogPage.selectFilterCategoryById(master.getCategory().getSystemId());
        user.atCatalogPage.verifyMasterCategoryPromoted(master);
    }
}
