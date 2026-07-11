package UITests.Customer.Requests;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@WithTag("smoke")
@ExtendWith(SerenityJUnit5Extension.class)
@AddCategory(promotionAndClickPrice = true, addRequest = true)
@AddMasters(masters = 1)
public class CustomerRequestAssignForMoneyTest extends TestBase {

    @BeforeEach
    public void setUp() throws TimeoutException, InterruptedException, IOException {
        super.setUp();
        admin.addMoneyToMaster(10000, getMaster(), false);
    }

    @Test
    public void verifyRequestAssignToMasterForAmount() throws TimeoutException, InterruptedException, IOException {
        var result = createRequest(true, true);

        user.atHomePage.openHomePage();
        user.atHomePage.login(getMaster(), true);
        user.atHomePage.waitForLoaderDisappears();

        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.verifyBalance(10000);
        user.atMasterProfileRequestsPage.openRequest();

        user.atMasterRequestPage.clickConnectClientButton();
        user.atMasterRequestPage.verifyCustomerInfo(result.guest);
        user.atMasterRequestPage.closeConnectCustomerPopup();

        user.atMasterRequestPage.verifyRequestStatusIsPaid();
        user.atMasterRequestPage.verifyCustomerConnectButtonHasNoPrice();
        user.atMasterRequestPage.verifyCustomerInfoInRequest(result.guest);

        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.verifyBalance(9000);

        user.atHomePage.logsOut();
        user.atHomePage.login(result.guest, true);
        user.atCustomerProfileRequestsPage.openRequestsPage();
        user.atCustomerRequestPage.openRequest();
        user.atCustomerRequestPage.verifyMastersCount(1);
    }
}
