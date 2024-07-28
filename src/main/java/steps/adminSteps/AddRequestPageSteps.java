package steps.adminSteps;

import entities.Category;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AddRequestPage;

public class AddRequestPageSteps extends ScenarioSteps {

    private AddRequestPage servicePage;

    public void openPage() {
        servicePage.openPage();
    }

    public void fillAddServiceForm(Category category) {
        servicePage.enterName("Autotest");
        servicePage.selectCategory(category.getName());
        servicePage.enterHeader("Test");
    }

    public void saveService() {
        servicePage.saveService();
    }

    public void setPrices() {
        servicePage.setPrices();
    }
}
