package steps.masterProfileSteps;

import net.serenitybdd.annotations.Step;
import pages.masterProfile.MasterWalletPage;

public class MasterWalletPageSteps extends MasterProfileSteps {

    private MasterWalletPage masterWalletPage;

    @Step
    public void pageShouldBeVisible() {
        masterWalletPage.walletTabsShouldBeVisible();
    }

    @Step
    public void addMoneyUseClick(String amount) {
        masterWalletPage.enterAmount(amount);
        masterWalletPage.selectClickSystem();
    }

    @Step
    public void addMoneyUsePayme(String amount) {
        masterWalletPage.enterAmount(amount);
        masterWalletPage.selectPaycomSystem();
    }

    @Step
    public void submitMoneyRequest() {
        masterWalletPage.clickSubmitFormButton();
    }

    @Step
    public void verifyClickPaymentSystemPageIsOpened() {
        masterWalletPage.verifyClickSystemPage();
    }

    @Step
    public void verifyPaycomPaymentSystemPageIsOpened() {
        masterWalletPage.verifyPaycomSystemPage();
    }

    @Step
    public void openPaymentCardsSection() {
        masterWalletPage.clickCardsSection();
    }

    @Step
    public void addPaymentCard(String name, String number, String expiryDate, boolean markAsMain) {
        masterWalletPage.clickAddCard();
        masterWalletPage.enterCardName(name);
        masterWalletPage.enderCardNumber(number);
        masterWalletPage.enterExpiryDate(expiryDate);

        if (markAsMain) {
            masterWalletPage.selectCardAsMain();
        }

        masterWalletPage.clickSubmitFormButton();
    }

    @Step
    public void verifyUzcardCardAdded() {
        masterWalletPage.verifyUzcardAdded();
    }

    @Step
    public void verifyHumoCardAdded() {
        masterWalletPage.verifyHumoCardAdded();
    }

    @Step
    public void verifyUzcardIsMainCard() {
        masterWalletPage.verifyUzcardIsMainCard();
    }

    @Step
    public void verifyHumoIsMainCard() {
        masterWalletPage.verifyHumoIsMainCard();
    }

    @Step
    public void deleteHumoCard() {
        masterWalletPage.clickHumoOptions();
        masterWalletPage.clickHumoDelete();
    }
}
