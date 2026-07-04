package UITests.Master;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import entities.Master;
import entities.User;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.masterProfile.MasterPromotionPage;
import utils.DataGenerator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

//Заказчик - Списывание средств за клик

@WithTag("smoke")
@ExtendWith(SerenityJUnit5Extension.class)
@AddCategory(promotionAndClickPrice = true)
@AddMasters(masters = 1)
public class MasterClickWithdrawForCustomerTest extends TestBase {

    private final User customer = DataGenerator.getCustomer();
    private Master master;

    @BeforeEach
    public void setup() throws InterruptedException, TimeoutException, URISyntaxException, IOException {
        master = getMaster();
        admin.addMoneyToMaster(10000, master, false);
        user.addMasterProfileImage(master, true);

        users.add(customer);
        user.atHomePage.openHomePage();
        user.registerAsCustomer(customer);
        user.atCustomerProfilePersonalInfoPage.logsOut();
    }

    @Test
    public void withdrawMoneyOnClick() throws Exception {
        user.atHomePage.openHomePage();
        user.atHomePage.loginIfNeeded(master);

        user.atMasterProfilePage.openProfilePage();
        user.atMasterPromotionPage.openPromotionTab();
        user.atMasterPromotionPage.promoteCategory(
                master.getCategory().getName(),
                MasterPromotionPage.PromotionType.MinimalPrice);
        user.atMasterProjectsPage.logsOut();

        admin.approvePromotion(master.getCategory());

        user.atHomePage.openHomePage();
        user.atHomePage.login(customer, true);

        user.atHomePage.openHomePage();
        user.atCatalogPage.openMastersCatalog();
        user.atCatalogPage.selectFilterCategoryById(master.getCategory().getSystemId());
        user.atCatalogPage.openMasterContactsAndVerify(master.getFirstName());

        user.atCatalogPage.hideFeedbackPopUpIfNeeded();

        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.verifyMyMastersListContains(getMaster().getFirstName());

        admin.atMastersPage.openMasterPage(master.getProfileId());
        admin.atMastersPage.verifyOnlyOneTransactionExist("-100");
    }
}
