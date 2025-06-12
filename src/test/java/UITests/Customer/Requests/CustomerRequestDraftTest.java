package UITests.Customer.Requests;

import UITests.TestBase;
import annotations.AddCategory;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

//Гость - создание черновика заявки

@RunWith(SerenityRunner.class)
@AddCategory(addRequest = true)
public class CustomerRequestDraftTest extends TestBase {

    @Test
    public void verifyUserCanCreateCustomerRequestDraft() throws TimeoutException, InterruptedException {
        var guest = DataGenerator.getGuestCustomer();

        watcher.users.add(guest);

        user.atHomePage.openPlaceOrderPage();

        user.atPlaceOrderPage.enterDescription();
        user.atPlaceOrderPage.enterPhoneNumber(guest);
        user.atPlaceOrderPage.clickPlaceOrder();

        user.atPlaceOrderPage.waitForCodeForm();
        var smsCode = user.atPlaceOrderPage.getSmsCode(guest);
        user.atPlaceOrderPage.confirmPhoneNumber(smsCode, guest.getPhoneNumber());

        var customerId = Admin.getInstance().getCustomerId(guest.getPhoneNumber());
        guest.setProfileId(customerId);

        user.atPlaceOrderPage.verifySuccessPageIsVisible();
        var requestId = user.atPlaceOrderPage.getRequestId();

        admin.atRequestsPage.openEditRequestById(requestId);
        admin.atRequestsPage.fillRequest(category);
        Thread.sleep(5000);

        user.atHomePage.openHomePage();
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        guest.setProfileId(user.atCustomerProfileRequestsPage.getCustomerProfileId());

        user.atPlaceOrderPage.openRequestsPage();
        user.atCustomerProfileRequestsPage.newRequestShouldBeVisible();

        user.atCustomerProfilePersonalInfoPage
                .openPersonalInfo()
                .verifyProfileData(guest);
    }
}
