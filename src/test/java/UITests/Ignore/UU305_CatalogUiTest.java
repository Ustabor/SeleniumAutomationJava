package UITests.Ignore;

import UITests.TestBase;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

@WithTag("smoke")
@Ignore
@RunWith(SerenityRunner.class)
public class UU305_CatalogUiTest extends TestBase {

    @Test
    public void catalogUiTest() {
        if (Config.isMobileTag()) {
            setBrowserMobileWindowSize();
        }

        user.atCatalogPage.openMastersCatalog();

        user.atCatalogPage.openFilterAndVerify();
        user.atCatalogPage.openFilterCategoriesAndVerify();
        user.atCatalogPage.closeFilterCategoryWindow();
        user.atCatalogPage.openFilterCityDropdownAndVerify();

        var master = user.atCatalogPage.openRandomMasterProfile();
        user.atMasterProfilePage.verifyProfilePage(master);
    }
}
