package steps.customerProfileSteps;

import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.customerProfile.CustomerProfileWalletPage;

public class CustomerProfileWalletPageSteps extends ScenarioSteps {

    private CustomerProfileWalletPage customerProfileWalletPage;

    @Step
    public void openWalletTab() {
        customerProfileWalletPage.clickWalletTab();
    }

    @Step
    public void openPaymentCardsSection() {
        customerProfileWalletPage.clickCardsSection();
    }

    @Step
    public void addPaymentCard(String name, String number, String expiryDate, boolean markAsMain) {
        customerProfileWalletPage.clickAddCard();
        customerProfileWalletPage.enterCardName(name);
        customerProfileWalletPage.enderCardNumber(number);
        customerProfileWalletPage.enterExpiryDate(expiryDate);

        if (markAsMain) {
            customerProfileWalletPage.selectCardAsMain();
        }

        customerProfileWalletPage.clickSubmitFormButton();
    }

    @Step
    public void verifyUzcardCardAdded() {
        customerProfileWalletPage.verifyUzcardAdded();
    }

    @Step
    public void verifyUzcardIsMainCard() {
        customerProfileWalletPage.verifyUzcardIsMainCard();
    }

    @Step
    public void verifyHumoCardAdded() {
        customerProfileWalletPage.verifyHumoCardAdded();
    }

    @Step
    public void verifyHumoIsMainCard() {
        customerProfileWalletPage.verifyHumoIsMainCard();
    }

    @Step
    public void deleteHumoCard() {
        customerProfileWalletPage.clickHumoOptions();
        customerProfileWalletPage.clickHumoDelete();
    }
}
