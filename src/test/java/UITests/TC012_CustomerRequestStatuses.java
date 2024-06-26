package UITests;

import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true)
@AddMasters(masters = 1, useAdminSite = true)
public class TC012_CustomerRequestStatuses extends TestBase {

    @Test
    public void verifyCustomerRequestStatuses() throws TimeoutException, InterruptedException {
        var customer = DataGenerator.getGuestCustomer();
        watcher.users.add(customer);

        user.atHomePage.openHomePage();
        setCountryLanguageAndLocation();

        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.placeOrderForLoggedUser(customer, category);
        user.atCustomerProfileRequestsPage.openRequestsPage();

        customer.setPassword(Admin.getInstance().getSmsPassword(customer.getPhoneNumber()));
        var requestId = user.atCustomerProfileRequestsPage.getRequestId();
        customer.setProfileId(user.atCustomerProfileRequestsPage.getCustomerProfileId());

        user.atHomePage.logsOut();

        if (getTashkentHour() >= 9 && getTashkentHour() < 18) {
            admin.atRequestsPage.openRequestById(requestId);
            admin.atRequestsPage.assignRequestToMasterForFree(watcher.getMaster());
            Thread.sleep(15000);
        }

        user.atHomePage.openHomePage();
        user.atHomePage.login(watcher.getMaster(), true);
        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.verifyRequestStatus(getText("RequestNew"));
        user.atMasterProfileRequestsPage.openRequest();
        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.verifyRequestStatus(getText("RequestWatched"));
        user.atMasterProfileRequestsPage.openRequest();
        user.atMasterRequestPage.clickConnectClientButton();
        user.atMasterRequestPage.closeConnectCustomerPopup();
        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.verifyRequestStatus(getText("RequestPaid"));
        user.atMasterProfileRequestsPage.openRequest();
        user.atMasterRequestPage.clickConnectClientButton();

        user.atMasterRequestPage.sendMessageToCustomer(getText("SmsTestMessage"));
        var msg = Admin.getInstance().getSmsByText(customer.getPhoneNumber(), getText("SmsMasterMessage"));
        assertThat(msg).isNotNull();

        user.atMasterRequestPage.makeOfferToCustomer("1000");
        var offer = Admin.getInstance().getSmsByText(customer.getPhoneNumber(), getText("SmsMasterPrice"));
        assertThat(offer).isNotNull();

        user.atMasterRequestPage.closeConnectCustomerPopup();
        user.atHomePage.logsOut();

        user.atHomePage.login(customer, true);
        user.atCustomerProfileRequestsPage.openRequestsPage();
        user.atCustomerRequestPage.openRequest();
        user.atCustomerRequestPage.verifyMasterSmsText(getText("SmsTestMessage"));
        user.atCustomerRequestPage.verifyMasterOffer("1000");
        user.atCustomerRequestPage.hideMasterOffer();
        user.atCustomerProfileRequestsPage.closePopup();
        user.atCustomerRequestPage.verifyOfferIsHidden();

        user.atCustomerProfileRequestsPage.openRequestsPage();
        user.atCustomerProfileRequestsPage.hideRequest();
        user.atCustomerProfileRequestsPage.closePopup();
        user.atCustomerProfileRequestsPage.verifyRequestStatus(getText("RequestClosed"));
        user.atHomePage.logsOut();

        user.atHomePage.login(watcher.getMaster(), true);
        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.verifyRequestsTableIsEmpty();
        user.atHomePage.logsOut();

        admin.atRequestsPage.deleteRequest(requestId);

        user.atHomePage.openHomePage();
        user.atHomePage.login(customer, true);
        user.atCustomerProfileRequestsPage.openRequestsPage();
        user.atCustomerProfileRequestsPage.closePopup();
        user.atCustomerProfileRequestsPage.verifyRequestsTableIsEmpty();
        user.atHomePage.logsOut();
    }
}
