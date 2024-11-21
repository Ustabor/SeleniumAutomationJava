package UITests.Customer.Requests;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

@WithTag("smoke")
@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true, promotionAndClickPrice = true)
@AddMasters(masters = 1)
public class CustomerRequestClientContactWithdrawTest extends TestBase {

    @Before
    public void setup() throws TimeoutException, InterruptedException {
        admin.addMoneyToMaster(500, watcher.getMaster(), false);
    }

    @Test
    public void verifyWithdrawForCustomerContacts() throws TimeoutException, InterruptedException {
        var result1 = createRequest(true);
        var result2 = createRequest(false);

        user.atHomePage.openHomePage();
        user.atHomePage.logsOut();
        user.atHomePage.login(watcher.getMaster(), true);
        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.openRequestWithId(result1.requestId);
        user.atMasterRequestPage.clickConnectClientButton();
        user.atMasterRequestPage.closeConnectCustomerPopup();

        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.openRequestWithId(result2.requestId);
        user.atMasterRequestPage.clickConnectClientButton();
        user.atMasterRequestPage.verifyErrorMessage(getText("InsufficientFunds"));

        admin.atMastersPage.openMasterPage(watcher.getMaster().getProfileId());
        admin.atMastersPage.verifyBalance(-500);
    }
}
