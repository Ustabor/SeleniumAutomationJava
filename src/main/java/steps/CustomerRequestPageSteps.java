package steps;

import entities.User;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.CustomerRequestPage;

public class CustomerRequestPageSteps extends ScenarioSteps {

    private CustomerRequestPage customerRequestPage;

    @Step
    public void openRequest() {
        customerRequestPage.openRequest();
    }

    @Step
    public void openAssignedMasters() {
        customerRequestPage.openAssignedMasters();
    }

    public void verifyMasterAssigned(User master) {
        customerRequestPage.verifyMasterAssigned(master);
    }
}
