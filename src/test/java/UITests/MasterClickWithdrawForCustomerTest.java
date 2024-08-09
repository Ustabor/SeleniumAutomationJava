package UITests;

import annotations.AddCategory;
import annotations.AddMasters;
import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.masterProfile.MasterPromotionPage;
import utils.Admin;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

//Заказчик - Списывание средств за клик

@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true)
@AddMasters(masters = 1, useAdminSite = true)
public class MasterClickWithdrawForCustomerTest extends TestBase {

    private User customer;
    @Before
    public void clickWithdrawForCustomer() throws InterruptedException, TimeoutException {
        customer = DataGenerator.getCustomer();
        watcher.users.add(customer);

        user.atHomePage.openHomePage();
        user.atHomePage.registerAsCustomer(customer);

        var smsCode = Admin.getInstance().getSmsCode(customer.getPhoneNumber());
        user.atHomePage.enterAuthCodeAndSubmit(smsCode, customer.getPhoneNumber());

        var password = Admin.getInstance().getSmsPassword(customer.getPhoneNumber());
        customer.setPassword(password);

        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        customer.setProfileId(user.atCustomerProfilePersonalInfoPage.getCustomerProfileId());
        user.atCustomerProfilePersonalInfoPage.logsOut();
    }

    @Test
    public void withdrawMoneyOnClick() throws Exception {
        var master = watcher.getMaster();
        admin.addMoneyToMaster(10000, master, false);

        user.atHomePage.openHomePage();
        user.atHomePage.loginIfNeeded(master);

        user.atMasterProfilePage.openProfilePage();
        user.atMasterPromotionPage.openPromotionTab();
        user.atMasterPromotionPage.promoteCategory(
                master.getCategory().getName(),
                MasterPromotionPage.PromotionType.MinimalPrice);
        user.atMasterProjectsPage.logsOut();

        admin.atAdminHomePage.loginAsAdmin();
        admin.atPromotionPage.approvePromotion(master.getCategory());

        user.atHomePage.openHomePage();
        user.atHomePage.login(customer, true);
        setCountryLanguageAndLocation();

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(master.getCategory().getName());
        user.atCatalogPage.openMasterContactsAndVerify(master.getFirstName());
        user.atCatalogPage.openMasterContactsAndVerify(master.getFirstName());
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.verifyMyMastersListContains(watcher.getMaster().getFirstName());

        admin.atMastersPage.openMasterPage(master.getProfileId());
        admin.atMastersPage.verifyOnlyOneTransactionExist("-100");
    }
}
