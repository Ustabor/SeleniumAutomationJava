package UITests.Master;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import entities.Master;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

// Мастер - выставление цен для услуг

@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true, addServicePrice = true)
@AddMasters(masters = 1)
public class MasterServicesPricesTests extends TestBase {

    private Master master;

    @Before
    public void setup() throws TimeoutException, URISyntaxException, InterruptedException {
        master = watcher.getMaster();
        user.addMasterProfileImage(master, false);
        user.atMasterProfilePage.selectService();
        user.atMasterProfilePage.logsOut();
    }

    @Test
    public void masterServicePrice() throws TimeoutException {
        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(master.getCategory().getName());
        user.atCatalogPage.verifyMasterServicePresent();
    }
}
