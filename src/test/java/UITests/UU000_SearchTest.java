package UITests;

import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@WithTag("smoke")
@RunWith(SerenityRunner.class)
public class UU000_SearchTest extends TestBase {

    @Test
    public void findCategory() {
        user.atHomePage.enterTextAndSearch(getText("SearchRequestPlumber"));
        user.atPlaceOrderPage.verifyCategory(getText("SearchRequestPlumber"));
        user.atPlaceOrderPage.clickCategoryTab();
        user.atCatalogPage.clickPricesTab();
        user.atCatalogPage.verifyTabShowPricesForCategory(getText("SearchRequestPlumber"));
    }
}
