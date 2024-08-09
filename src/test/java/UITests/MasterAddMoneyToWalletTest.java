package UITests;

import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

//Мастер - пополнение кошелька

@RunWith(SerenityRunner.class)
@AddCategory
@AddMasters(masters = 1, useAdminSite = true)
public class MasterAddMoneyToWalletTest extends TestBase {

    @Test
    public void addMoneyToWallet() throws TimeoutException {
        var master = watcher.getMaster();

        user.atHomePage.openHomePage();
        user.atHomePage.login(master, true);
        user.atMasterProfilePage.openProfilePage();
        user.atMasterProfilePage.openWalletTab();
        user.atMasterWalletPage.addMoneyUseClick("10000");
        user.atMasterWalletPage.submitMoneyRequest();
        user.atMasterWalletPage.verifyClickPaymentSystemPageIsOpened();

        user.atHomePage.openHomePage();
        user.atMasterProfilePage.openProfilePage();
        user.atMasterProfilePage.openWalletTab();
        user.atMasterWalletPage.addMoneyUsePayme("10000");
        user.atMasterWalletPage.submitMoneyRequest();
        user.atMasterWalletPage.verifyPaycomPaymentSystemPageIsOpened();
    }
}