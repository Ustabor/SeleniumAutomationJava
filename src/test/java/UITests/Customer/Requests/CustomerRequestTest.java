package UITests.Customer.Requests;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//Гость - создание заявки

@ExtendWith(SerenityJUnit5Extension.class)
@AddCategory(addRequest = true)
@AddMasters(masters = 1)
@Tag("MyTag")
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
