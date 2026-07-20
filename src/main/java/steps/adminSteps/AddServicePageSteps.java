package steps.adminSteps;

import entities.Category;
import net.serenitybdd.core.steps.UIInteractions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.admin.AddServicePage;

public class AddServicePageSteps extends UIInteractions {

    private static final Logger logger = LoggerFactory.getLogger(AddServicePageSteps.class);

    private AddServicePage addServicePage;

    public void addService(Category category) {
        addServicePage.openPage();
        addServicePage.createService(category);
        addServicePage.waitForQuickSearchIsVisible();
        var serviceId = addServicePage.getServiceId(category.getName());
        category.getService().setId(serviceId);
        logger.info("Test service (price-services) with id '{}' has been created", serviceId);
    }
}
