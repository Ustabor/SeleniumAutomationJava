package UITests.Customer.Requests;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@AddCategory(addRequest = true)
@AddMasters(masters = 1)
public class CustomerRequestAssignForFreeTest extends TestBase {

    @Before
    public void setup() throws TimeoutException, InterruptedException {
        admin.addMoneyToMaster(10000, watcher.getMaster(), false);
    }

    @Test
    public void verifyRequestAssignToMasterForFree() throws TimeoutException, InterruptedException, IOException {
        var result = createRequest(true, true);

        user.atHomePage.openHomePage();
        user.atHomePage.login(watcher.getMaster(), true);
        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.verifyRequestId(result.requestId);
        user.atMasterProfileRequestsPage.verifyBalance(10000);
    }
}
