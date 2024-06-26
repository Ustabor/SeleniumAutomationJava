package steps.masterProfileSteps;

import net.serenitybdd.annotations.Step;
import pages.masterProfile.MasterWalletPage;

public class MasterWalletPageSteps extends MasterProfileSteps {

    private MasterWalletPage masterWalletPage;

    @Step
    public void pageShouldBeVisible() {
        masterWalletPage.walletTabsShouldBeVisible();
    }
}
