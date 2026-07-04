package UITests.Master;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import entities.Master;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

// Мастер - выставление цен для услуг

@ExtendWith(SerenityJUnit5Extension.class)
@AddCategory(addRequest = true, addRequestPrice = true)
@AddMasters(masters = 1)
public class MasterServicesPricesTests extends TestBase {

    private Master master;

    @BeforeEach
    public void setup() throws TimeoutException, URISyntaxException, InterruptedException {
        master = getMaster();
        user.addMasterProfileImage(master, false);
        user.atMasterProfilePage.selectService();
        user.atMasterProfilePage.logsOut();
    }

    @Test
    public void masterServicePrice() throws TimeoutException {
        user.atHomePage.openHomePage();
        user.atHomePage.openCatalog();
        user.atHomePage.openCategory(master.getCategory().getName());
        user.atCatalogPage.verifyMasterServicePresent();
    }
}
