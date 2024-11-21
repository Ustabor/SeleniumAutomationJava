package UITests.Master;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

//Мастер - Добавление/удаление карты

@RunWith(SerenityRunner.class)
@AddCategory
@AddMasters(masters = 1)
public class MasterAddPaymentCardTest extends TestBase {

    @Test
    public void masterAddPaymentCard() throws TimeoutException {
        var master = watcher.getMaster();

        user.atHomePage.openHomePage();
        user.atHomePage.login(master, true);
        user.atMasterProfilePage.openProfilePage();
        user.atMasterProfilePage.openWalletTab();
        user.atMasterWalletPage.openPaymentCardsSection();

        user.atMasterWalletPage.addPaymentCard("UZCARD", "8600-0200-0000-0000", "12/31", true);
        user.atMasterWalletPage.verifyUzcardCardAdded();
        user.atMasterWalletPage.verifyUzcardIsMainCard();

        user.atMasterWalletPage.addPaymentCard("HUMO", "9860-0100-0000-0000", "12/31", true);
        user.atMasterWalletPage.verifyHumoCardAdded();
        user.atMasterWalletPage.verifyHumoIsMainCard();

        user.atMasterWalletPage.deleteHumoCard();
        user.atMasterWalletPage.verifyUzcardIsMainCard();
    }
}
