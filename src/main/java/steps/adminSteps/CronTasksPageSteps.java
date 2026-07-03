package steps.adminSteps;

import net.serenitybdd.core.steps.UIInteractions;

public class CronTasksPageSteps extends UIInteractions {

    private CronTasksPage cronTasksPage;

    public void waitForCronTaskCompleted(String taskId, int timeout) {
        cronTasksPage.openPage();
        cronTasksPage.waitForCronTaskCompleted(taskId, timeout);
    }

    public void performCategoriesUpdate() {
        cronTasksPage.openPage();
        cronTasksPage.runCategoriesUpdate();
    }
}
