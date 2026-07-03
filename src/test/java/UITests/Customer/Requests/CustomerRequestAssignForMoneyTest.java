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
@AddMasters(masters = 2)
public class CustomerRequestAssignForMoneyTest extends TestBase {

    @BeforeEach
    public void setUp() throws TimeoutException, InterruptedException, IOException {
        super.setUp();
        admin.addMoneyToMaster(10000, getMaster(0), false);
        admin.addMoneyToMaster(10000, getMaster(1), false);
    }

    @Test
    public void verifyRequestAssignToMasterForAmount() throws TimeoutException, InterruptedException, IOException {
        var result = createRequest(true, false);

        user.atHomePage.openHomePage();
        user.atHomePage.login(getMaster(0), true);
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
