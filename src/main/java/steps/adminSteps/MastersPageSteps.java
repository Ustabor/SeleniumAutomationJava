package steps.adminSteps;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractions;
import pages.admin.MastersPage;

public class MastersPageSteps extends UIInteractions {

    private MastersPage mastersPage;

    @Step
    public void openMasterPage(String masterId) {
        mastersPage.openMasterPageByDirectUrl(masterId);
    }

    @Step
    public void addMoneyToMaster(int amount, String masterId) {
        mastersPage.openMasterPageByDirectUrl(masterId);
        mastersPage.addMoneyToAccount(amount);
    }

    @Step
    public void verifyOnlyOneTransactionExist(String amount) {
        mastersPage.verifyOnlyOneTransactionExist(amount);
    }

    @Step
    public void verifyBalance(String balance) {
        mastersPage.verifyMasterBalance(balance);
    }
}
