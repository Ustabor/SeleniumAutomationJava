package steps.masterProfileSteps;

import entities.Project;
import net.thucydides.core.annotations.Step;
import pages.masterProfile.MasterProjectsPage;
import utils.WaitHelper;

import java.util.concurrent.TimeoutException;

public class MasterProjectsPageSteps extends MasterProfileSteps {

    private MasterProjectsPage masterProjectsPage;

    @Step
    public void addNewProjectInCategory(
            Project project, boolean promotion, boolean minPrice) throws TimeoutException {
        int countOfProjects = masterProjectsPage.getCountOfProjects();
        masterProjectsPage.openNewProjectForm();
        masterProjectsPage.enterProjectName(project.getName());
        masterProjectsPage.addProjectImage();
        masterProjectsPage.enterProjectDescription(project.getDescription());
        masterProjectsPage.selectCategory(project.getCategory());
        if (promotion) {
            if (minPrice) {
                masterProjectsPage.selectMinimalPrice();
            } else {
                masterProjectsPage.selectRecommendedPrice();
            }
        } else {
            masterProjectsPage.selectNoPromotionCheckboxIfPresent();
        }
        masterProjectsPage.saveNewProject();
        masterProjectsPage.waitTillProjectsAreVisible();
        WaitHelper.pollingWait(10000, 500, () ->
                masterProjectsPage.getCountOfProjects() == countOfProjects + 1);
        masterProjectsPage.getProjectSystemId(project);
    }

    @Step
    public void pageShouldBeVisible() {
        masterProjectsPage.addProjectBtnShouldBeVisible();
    }

    @Step
    public void logsOut() {
        masterProjectsPage.logsOut();
    }
}
