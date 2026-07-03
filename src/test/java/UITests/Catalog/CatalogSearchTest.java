package UITests.Catalog;

import UITests.TestBase;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@WithTag("smoke")
@ExtendWith(SerenityJUnit5Extension.class)
public class CatalogSearchTest extends TestBase {

    @Test
    public void findCategory() {
        user.atHomePage.enterTextAndSearch(getText("SearchRequestPlumber"));
        user.atPlaceOrderPage.verifyCategory(getText("SearchRequestPlumber"));
        user.atPlaceOrderPage.clickCategoryTab();
        user.atCatalogPage.clickPricesTab();
        user.atCatalogPage.verifyTabShowPricesForCategory(getText("SearchRequestPlumber"));
    }
}
