package UITests.Customer.Requests;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@WithTag("smoke")
@ExtendWith(SerenityJUnit5Extension.class)
@AddCategory(addRequest = true, promotionAndClickPrice = true)
@AddMasters(masters = 1)
public class CustomerRequestClickContactWithZeroBalanceTest extends TestBase {

    @Test
    public void verifyWithdrawForCustomerContacts() throws TimeoutException, InterruptedException, IOException {
        var result1 = createRequest(true, true);
        var result2 = createRequest(false, true);

        user.atHomePage.openHomePage();
        user.atHomePage.logsOut();
        user.atHomePage.login(getMaster(), true);
        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.openRequestWithId(result1.requestOuterId);
        user.atMasterRequestPage.clickConnectClientButton();
        user.atMasterRequestPage.closeConnectCustomerPopup();

        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.openRequestWithId(result2.requestOuterId);
        user.atMasterRequestPage.clickConnectClientButton();
        user.atMasterRequestPage.verifyErrorMessage(getText("InsufficientFunds"));
    }
}
