package UITests;

import static org.assertj.core.api.Assertions.*;

import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

@WithTag("smoke")
@RunWith(SerenityRunner.class)
public class UU305_CatalogUiTest extends TestBase {

    @Test
    public void catalogUiTest() {
        var sitesNamesList = user.atCatalogPage.getSitesNames();
        user.atCatalogPage.openSiteWithName(sitesNamesList.get(0));
        user.atCatalogPage.verifyCatalogPage();

        var oldMastersCount = user.atCatalogPage.getMastersCardsCount();
        user.atCatalogPage.clickLoadMore();
        var newMastersCount = user.atCatalogPage.getMastersCardsCount();
        assertThat(newMastersCount).isGreaterThan(oldMastersCount);

        user.atCatalogPage.openFilter();
        user.atCatalogPage.verifyFilter();

        user.atCatalogPage.openFilterCategory();
        user.atCatalogPage.verifyFilterCategoriesAreVisible();

        var city = getText("FilterCity_" + Config.getCountryCode() + "_" + Config.getEnv());
        var district = getText("FilterDistrict_" + Config.getCountryCode());
        user.atCatalogPage.selectFilterCityAndDistrict(city, district);

        user.atCatalogPage.resetFilter();
        user.atCatalogPage.closeFilter();

        user.atCatalogPage.openRandomMaster();
    }
}
