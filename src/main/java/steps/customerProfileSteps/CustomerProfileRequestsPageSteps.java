package steps.customerProfileSteps;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractions;
import pages.customerProfile.CustomerProfileRequestsPage;

public class CustomerProfileRequestsPageSteps extends UIInteractions {

    private CustomerProfileRequestsPage customerProfileRequestsPage;

    @Step("Navigate to the requests page")
    public void openRequestsPage() {
        customerProfileRequestsPage.openPage();
    }

    @Step
    public void newRequestShouldBeVisible() {
        customerProfileRequestsPage.requestShouldBeVisible();
    }

    public String getRequestInnerId() {
        return customerProfileRequestsPage.getRequestInnerId();
    }

    public String getRequestOuterId() {
        return customerProfileRequestsPage.getRequestOuterId();
    }

    public String getCustomerProfileId() {
        return customerProfileRequestsPage.getCustomerId();
    }

    @Step
    public void deleteRequest() {
        customerProfileRequestsPage.deleteRequest();
        customerProfileRequestsPage.waitForLoaderDisappears();
    }

    @Step
    public void verifyRequestsTableIsEmpty() {
        customerProfileRequestsPage.verifyRequestsTabIsEmpty();
    }

    @Step
    public void hideRequest() {
        customerProfileRequestsPage.clickHideRequest();
    }

    @Step
    public void verifyRequestStatus(String status) {
        customerProfileRequestsPage.verifyRequestStatus(status);
    }

    @Step
    public void closePopup() {
        customerProfileRequestsPage.closePopup();
    }

    @Step
    public void verifyServiceRequestCreated() {
        customerProfileRequestsPage.verifyServiceRequestCreated();
    }
}
