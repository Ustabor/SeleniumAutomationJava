package steps.adminSteps;

import entities.Category;
import net.serenitybdd.core.steps.UIInteractions;
import utils.XmlParser;

public class ServicePricePageSteps extends UIInteractions {

    private ServicePricePage servicePricePage;

    public void addServicePrice(Category category) {
        servicePricePage.openPage();
        servicePricePage.clickAdd();
        servicePricePage.selectCategory(category.getSystemId());
        servicePricePage.enterServiceName("Autotest");
        servicePricePage.clickSave();
        servicePricePage.waitForQuickSearchIsVisible();
    }
}
