package UITests.Catalog;

import UITests.TestBase;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

import java.util.concurrent.TimeoutException;

@WithTag("smoke")
@RunWith(SerenityRunner.class)
public class CatalogFilterTest extends TestBase {

    @Test
    public void catalogFilterTest() throws TimeoutException {
        user.atCatalogPage.openMastersCatalog();
        user.atCatalogPage.verifySiteAtHeader(getText("SiteDomainBuild_Short2"));

        user.atCatalogPage.selectFilterCategory(getText("FilterCategoryPlumber"));
        user.atCatalogPage.verifyOpenedCategory(getText("FilterCategoryPlumber"));

        var oldMastersCount = user.atCatalogPage.getCategoryMastersCount();
        var city1 = getText("FilterCity_" + Config.getCountryCode() + "_" + Config.getEnv() + "_1");
        user.atCatalogPage.openFilter();
        user.atCatalogPage.selectFilterCityAndDistrict(city1, "");
        user.atCatalogPage.applyFilter();
        var newMastersCount = user.atCatalogPage.getCategoryMastersCount();
        Assertions.assertThat(oldMastersCount).isGreaterThan(newMastersCount);

        user.atCatalogPage.openRandomMaster();
        user.atMasterProfilePage.verifyMasterCity(city1);

        user.atHomePage.openHomePage();
        user.atCatalogPage.openMastersCatalog();
        user.atCatalogPage.openFilter();
        var city = getText("FilterCity_" + Config.getCountryCode() + "_" + Config.getEnv());
        user.atCatalogPage.selectFilterCityAndDistrict(city, "");
        user.atCatalogPage.selectFilterSiteAndCategory(
                getText("SiteDomainAuto"),
                getText("FilterCategoryCallAuto")
        );
        user.atCatalogPage.applyFilter();
        user.atCatalogPage.verifyOpenedCategory(getText("FilterCategoryCallAuto"));

        user.atCatalogPage.openRandomMaster();
        user.atMasterProfilePage.verifyMasterCity(city);

        user.atHomePage.openHomePage();
        user.atCatalogPage.openMastersCatalog();
        user.atCatalogPage.applyFilterSort(getText("FilterSortQuickCall"));
        user.atCatalogPage.verifyAllMastersHaveQuickCallBadge();

        user.atCatalogPage.applyFilterSort(getText("FilterSort24Hours"));
        user.atCatalogPage.verifyAllMastersHave24HoursBadge();

        user.atCatalogPage.applyFilterSort(getText("FilterSortRating"));
        user.atCatalogPage.verifySortByReviewsCount();

    }
}
