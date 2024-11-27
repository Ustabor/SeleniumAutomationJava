package UITests.Customer.Requests;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;

import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true)
@AddMasters(masters = 1)
public class CustomerRequestStatusesTest extends TestBase {

    @Test
    public void verifyCustomerRequestStatuses() throws TimeoutException, InterruptedException {
        var result = createRequest(true, true);

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
        var msg = Admin.getInstance().getSmsByText(result.guest.getPhoneNumber(), getText("SmsMasterMessage"));
        assertThat(msg).isNotNull();

        user.atMasterRequestPage.makeOfferToCustomer("1000");
        var offer = Admin.getInstance().getSmsByText(result.guest.getPhoneNumber(), getText("SmsMasterPrice"));
        assertThat(offer).isNotNull();

        user.atMasterRequestPage.closeConnectCustomerPopup();
        user.atHomePage.logsOut();

        user.atHomePage.login(result.guest, true);
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

        admin.atRequestsPage.deleteRequest(result.requestId);

        user.atHomePage.openHomePage();
        user.atHomePage.login(result.guest, true);
        user.atCustomerProfileRequestsPage.openRequestsPage();
        user.atCustomerProfileRequestsPage.closePopup();
        user.atCustomerProfileRequestsPage.verifyRequestsTableIsEmpty();
        user.atHomePage.logsOut();
    }
}
