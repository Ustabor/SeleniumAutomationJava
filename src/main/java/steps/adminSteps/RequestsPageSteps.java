package steps.adminSteps;

import entities.Master;
import entities.Category;
import entities.User;
import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.RequestsPage;

public class RequestsPageSteps extends ScenarioSteps {

    private RequestsPage requestsPage;

    @Step
    public void openRequestById(String requestId) {
        requestsPage.openPage(requestId);
    }

    @Step
    public void openEditRequestById(String requestId) {
        requestsPage.openEditPage(requestId);
    }

    @Step
    public void verifyRequest(User customer, Category category, String question) {
        requestsPage.verifyCustomerLogin(customer.getFirstName());
        requestsPage.verifyCategory(category.getName());
        requestsPage.verifyWhatToDoQuestion(question);
        requestsPage.verifyCustomerName(customer.getFirstName());
        requestsPage.verifyAddress(customer.getCity());
        requestsPage.verifyPhoneNumber(customer.getPhoneNumber());
    }

    @Step
    public void assignRequestToMasterForFree(Master master) {
        requestsPage.openAssignMasterForm();
        requestsPage.findMaster(master.getFirstName());
        requestsPage.assignForFree();
        requestsPage.waitForLoaderDisappears(180000);
    }

    @Step
    public void assignRequestToMasterForPayment(Master master) {
        requestsPage.openAssignMasterForm();
        requestsPage.findMaster(master.getFirstName());
        requestsPage.assignForPayment();
        requestsPage.waitForLoaderDisappears(180000);
        requestsPage.closeAssignMasterForm();
    }

    @Step
    public void reassignRequestToMasterForPayment(Master master) {
        requestsPage.openAssignMasterForm();
        requestsPage.findMaster(master.getFirstName());
        requestsPage.resetAssign();
        requestsPage.assignForPayment();
        requestsPage.waitForLoaderDisappears(180000);
        requestsPage.closeAssignMasterForm();
    }

    @Step
    public void deleteRequest(String requestId) {
        openRequestById(requestId);
        requestsPage.clickMenu();
        requestsPage.deleteRequest();
        requestsPage.waitForLoaderDisappears();
    }

    @Step
    public void fillRequest(Category category) {
        requestsPage.selectCategory(category.getSystemId());
        requestsPage.selectCity();
        requestsPage.enterName();
        requestsPage.enterAddress();
        requestsPage.clickSubmit();
    }

    @Step
    public boolean isMasterAssigned() {
        return requestsPage.isMasterAssigned();
    }

    @Step
    public void waitForPageIsOpened() {
        requestsPage.waitForQuickSearchIsVisible();
    }
}
