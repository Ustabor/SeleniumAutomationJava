package steps.adminSteps;

import entities.Category;
import net.serenitybdd.core.steps.UIInteractions;
import pages.admin.AddServicePage;

public class AddServicePageSteps extends UIInteractions {

    private AddServicePage addServicePage;

    public void addService(Category category) {
        addServicePage.openPage();
        addServicePage.createService(category);
        addServicePage.waitForQuickSearchIsVisible();
        var serviceId = addServicePage.getServiceId(category.getName());
        category.getService().setId(serviceId);
    }
}
