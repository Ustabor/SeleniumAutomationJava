package steps.customerProfileSteps;

import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.customerProfile.CustomerProfileRequestsPage;

public class CustomerProfileRequestsPageSteps extends ScenarioSteps {

    private CustomerProfileRequestsPage customerProfileRequestsPage;

    @Step
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
