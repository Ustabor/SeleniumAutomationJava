package UITests;

import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

// Мастер - выставление цен для услуг

@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true, addServicePrice = true)
@AddMasters(masters = 1, useAdminSite = true)
public class MasterServicesPricesTests extends TestBase {
    @Test
    public void masterServicePrice() throws TimeoutException, InterruptedException {
        var master = watcher.getMaster();

        user.atHomePage.openHomePage();
        user.atHomePage.login(master, true);
        user.atMasterProfilePage.openProfilePage();
        user.atMasterProfilePage.selectService();
        user.atMasterProfilePage.logsOut();

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(master.getCategory().getName());

        user.atCatalogPage.verifyMasterServicePresent();
    }
}
