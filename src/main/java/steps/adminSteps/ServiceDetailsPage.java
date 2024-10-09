package steps.adminSteps;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import utils.Config;

public class ServiceDetailsPage extends BasePage {

    @FindBy(xpath = "//a[@id='btn-payment-link']")
    private WebElementFacade paymentLink;

    @FindBy(xpath = "//select[@id='form_data_to']")
    private WebElementFacade paymentSelect;
    
    @FindBy(xpath = "//button[@class='btn-submit']")
    private WebElementFacade submit;

    public void openPage(String id) {
        getDriver().get(Config.getAdminUrl() + "ustabor-services-orders/" + id);
    }

    public void clickSendPaymentLink() {
        paymentLink.click();
    }

    public void clickSendButton() {
        submit.click();
    }

    public void selectSmsPayment() {
        paymentSelect.selectByIndex(1);
    }
}
