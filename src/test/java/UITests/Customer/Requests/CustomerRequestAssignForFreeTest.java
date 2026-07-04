package UITests.Customer.Requests;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@ExtendWith(SerenityJUnit5Extension.class)
@AddCategory(addRequest = true, promotionAndClickPrice = true)
@AddMasters(masters = 1)
public class CustomerRequestAssignForFreeTest extends TestBase {

    @BeforeEach
    public void setup() throws TimeoutException, InterruptedException {
        admin.addMoneyToMaster(10000, getMaster(), false);
    }

    @Test
    public void verifyRequestAssignToMasterForFree() throws TimeoutException, InterruptedException, IOException {
        var result = createRequest(true, true);

        user.atHomePage.openHomePage();
        user.atHomePage.login(getMaster(), true);
        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.verifyRequestId(result.requestOuterId);
        user.atMasterProfileRequestsPage.verifyBalance(10000);
    }
}
