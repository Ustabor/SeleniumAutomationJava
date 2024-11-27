package UITests.Customer.Requests;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

//Гость - создание заявки

@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true)
@AddMasters(masters = 1)
public class CustomerRequestTest extends TestBase {

    @Test
    public void verifyUserCanCreateCustomerRequest() throws TimeoutException, InterruptedException {
        var result = createRequest(false, true);

        user.atCustomerProfileRequestsPage.openRequestsPage();
        user.atCustomerProfileRequestsPage.newRequestShouldBeVisible();

        user.atCustomerProfilePersonalInfoPage.openPersonalInfo();
        user.atCustomerProfilePersonalInfoPage.verifyProfileData(result.guest);
    }
}
