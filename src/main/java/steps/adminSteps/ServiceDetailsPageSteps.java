package steps.adminSteps;

import net.thucydides.core.steps.ScenarioSteps;

public class ServiceDetailsPageSteps extends ScenarioSteps {

    private ServiceDetailsPage serviceDetailsPage;
    public void clickSendPaymentLink(String id) {
        serviceDetailsPage.openPage(id);
        serviceDetailsPage.clickSendPaymentLink();
        serviceDetailsPage.selectSmsPayment();
        serviceDetailsPage.clickSendButton();
    }
}
