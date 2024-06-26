package UITests.Ignore;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

@Ignore
@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true)
@AddMasters()
public class TC005a_MasterFeedback extends TestBase {

    private User customer;

    @Before
    public void setUp() throws TimeoutException, InterruptedException {
        super.setUp();
        customer = DataGenerator.getCustomer();
        watcher.users.add(customer);

        user.atHomePage.openHomePage();
        user.atHomePage.registerAsCustomer(customer);

        var smsCode = Admin.getInstance().getSmsCode(customer.getPhoneNumber());
        user.atHomePage.enterAuthCodeAndSubmit(smsCode, customer.getPhoneNumber());

        var password = Admin.getInstance().getSmsPassword(customer.getPhoneNumber());
        customer.setPassword(password);
    }

    @Test
    public void verifyMasterFeedback() throws TimeoutException {
        user.atCustomerProfilePersonalInfoPage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(watcher.getMaster().getCategory().getName());

        user.atCatalogPage.openMasterContactsAndVerify(watcher.getMaster().getLastName());

        user.atCustomerProfilePersonalInfoPage.logsOut();
        user.atHomePage.login(customer, true);

        user.atHomePage.waitForFeedbackProposalAndOpen();
        user.atFeedbackPage.leftFeedback(5, "Testing Review");

        user.atHomePage.openHomePage();

        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.verifyMyMastersListContains(watcher.getMaster().getLastName());

        Admin.getInstance().runCron("1");
        admin.waitForCronTaskCompleted("1", 200);

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(watcher.getMaster().getCategory().getName());

        user.atCatalogPage.verifyMastersSortedByRate(watcher.getMaster(), 5);
    }
}
