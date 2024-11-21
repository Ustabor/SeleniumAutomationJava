package steps.masterProfileSteps;

import net.serenitybdd.annotations.Step;
import pages.masterProfile.MasterProjectsPage;


public class MasterProjectsPageSteps extends MasterProfileSteps {

    private MasterProjectsPage masterProjectsPage;

    @Step
    public void pageShouldBeVisible() {
        masterProjectsPage.addProjectBtnShouldBeVisible();
    }

    @Step
    public void logsOut() {
        masterProjectsPage.logsOut();
    }
}
