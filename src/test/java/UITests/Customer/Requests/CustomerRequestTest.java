package UITests.Customer.Requests;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//Гость - создание заявки

@RunWith(SerenityRunner.class)
@AddCategory(addRequest = true)
@AddMasters(masters = 1)
public class CustomerRequestTest extends TestBase {

    @Test
    public void verifyUserCanCreateCustomerRequest() throws TimeoutException, InterruptedException, IOException {
        var result = createRequest(false, true);

        user.atCustomerProfileRequestsPage.openRequestsPage();
        user.atCustomerProfileRequestsPage.newRequestShouldBeVisible();

        user.atCustomerProfilePersonalInfoPage.openPersonalInfo();
        user.atCustomerProfilePersonalInfoPage.verifyProfileData(result.guest);
    }
}
