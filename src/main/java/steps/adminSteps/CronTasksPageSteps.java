package steps.adminSteps;

import net.serenitybdd.core.steps.UIInteractions;

public class CronTasksPageSteps extends UIInteractions {

    private CronTasksPage cronTasksPage;

    public void performCategoriesUpdate() {
        cronTasksPage.openPage();
        cronTasksPage.runCategoriesUpdate();
    }
}
