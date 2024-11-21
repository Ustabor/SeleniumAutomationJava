package UITests.Customer.Requests;

import UITests.TestBase;
import annotations.AddCategory;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

//Гость - создание заявки

@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true)
public class CustomerRequestTest extends TestBase {

    @Test
    public void verifyUserCanCreateCustomerRequest() throws TimeoutException, InterruptedException {
        var result = createRequest(false);
        user.atCustomerProfileRequestsPage.newRequestShouldBeVisible();

        user.atCustomerProfilePersonalInfoPage.openPersonalInfo();
        user.atCustomerProfilePersonalInfoPage.verifyProfileData(result.guest);

    }
}
