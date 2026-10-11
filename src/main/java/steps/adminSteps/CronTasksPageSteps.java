package steps.adminSteps;

import net.serenitybdd.core.steps.UIInteractions;

public class CronTasksPageSteps extends UIInteractions {

    private CronTasksPage cronTasksPage;

    public void performCategoriesUpdate() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cronTasksPage.openPage();
        cronTasksPage.runCategoriesUpdate();
    }
}
