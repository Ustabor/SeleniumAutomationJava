package UITests.Customer.Requests;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@WithTag("smoke")
@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true, addRequest = true)
@AddMasters(masters = 2)
public class CustomerRequestAssignForMoneyTest extends TestBase {

    @Before
    public void setUp() throws TimeoutException, InterruptedException, IOException {
        super.setUp();
        admin.addMoneyToMaster(10000, watcher.getMaster(0), false);
        admin.addMoneyToMaster(10000, watcher.getMaster(1), false);
    }

    @Test
    public void verifyRequestAssignToMasterForAmount() throws TimeoutException, InterruptedException, IOException {
        var result = createRequest(true, false);

        user.atHomePage.openHomePage();
        user.atHomePage.login(watcher.getMaster(0), true);
        user.atHomePage.waitForLoaderDisappears();

        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.verifyBalance(9000);
        user.atMasterProfileRequestsPage.openRequest();

        user.atMasterRequestPage.clickConnectClientButton();
        user.atMasterRequestPage.verifyCustomerInfo(result.guest);
        user.atMasterRequestPage.closeConnectCustomerPopup();

        user.atHomePage.logsOut();
        user.atHomePage.login(result.guest, true);
        user.atCustomerProfileRequestsPage.openRequestsPage();
        user.atCustomerRequestPage.openRequest();
        user.atCustomerRequestPage.verifyMastersCount(2);
    }
}
