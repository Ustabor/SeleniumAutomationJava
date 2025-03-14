package steps;

import entities.Master;
import net.serenitybdd.annotations.Step;
import pages.CatalogPage;
import utils.XmlParser;

public class CatalogPageSteps extends CommonSteps {

    private CatalogPage catalogPage;

    @Step
    public void verifySelectedCategoryEquals(String expectedCategory) {
        catalogPage.openFilter();
        catalogPage.verifySelectedCategoryEquals(expectedCategory);
        catalogPage.closeFilter();
    }

    @Step
    public void openMasterContactsAndVerify(String projectName) {
        catalogPage.openMasterContactsByName(projectName);
        catalogPage.projectContactPopupShouldBeVisible();
        catalogPage.closeContactPopup();
        catalogPage.contactPopupShouldNotBeVisible();
    }

    @Step
    public void suggestionDropdownShouldBeVisible() {
        catalogPage.suggestionListShouldBeVisible();
    }

    @Step
    public void verifyFilterValues() {
        catalogPage.openFilter();
        catalogPage.verifyCategoryFilterBtnTextIsNot(XmlParser.getTextByKey("FilterCategoriesDefault"));
        if (catalogPage.isBreadcrumbsLongEnough()) {
            catalogPage.verifyCityFilterTextIsNot(XmlParser.getTextByKey("FilterCityDefault"));
        }
    }

    @Step
    public void selectFilterCityAndDistrict(String cityName, String district) {
        catalogPage.openFilterCityDropdown();
        catalogPage.selectCity(cityName);
        catalogPage.waitForLoaderDisappears();

        if (!district.isEmpty()) {
            catalogPage.openDistrictDropdown();
            catalogPage.selectDistrict(district);
        }
    }
    @Step
    public void verifyFilterContainsValues(String city, String district) {
        catalogPage.openFilter();
        catalogPage.verifyCityFilterText(city);
        if (!district.isEmpty()) {
            catalogPage.verifyDistrictFilterText(district);
        }

    }

    public boolean isSearchResultEmpty() {
        return catalogPage.isSearchCatalogEmpty();
    }

    @Step
    public void verifyMasterWithBadgesPromoted(Master master) {
        catalogPage.verifyMasterWithBadges(master);
    }

    @Step
    public void verifyMasterCategoryPromoted(Master master) {
        catalogPage.verifyMasterAtFirstPosition(master);
    }

    @Step
    public void verifyMastersSortedByRate(Master master, int rating) {
        catalogPage.verifyMastersSortedByRate(master, rating);
    }

    @Step
    public void openMastersCatalog() {
        catalogPage.openPage();
    }

    @Step
    public void clickPricesTab() {
        catalogPage.clickPricesTab();
    }

    @Step
    public void verifyTabShowPricesForCategory(String text) {
        catalogPage.verifyPricesShowsForCategory(text);
    }

    @Step
    public void verifyCatalogPage() {
        catalogPage.verifyTabsAreVisible();
        catalogPage.verifyFilterButtonIsVisible();
        catalogPage.verifyMastersCardsAreVisible();
    }

    @Step
    public void clickLoadMore() {
        catalogPage.clickLoadMoreButton();
        catalogPage.waitForLoaderDisappears();
        catalogPage.scrollPageToTop();
    }

    @Step
    public int getMastersCardsCount() {
        return catalogPage.getMastersCardsCount();
    }

    @Step
    public void openFilter() {
        catalogPage.openFilter();
    }

    @Step
    public void verifyFilter() {
        catalogPage.filterCategoryBtnShouldBeVisible();
        catalogPage.filterCityBtnShouldBeVisible();
        catalogPage.filterSortingBtnShouldBeVisible();
        catalogPage.filterResetBtnShouldBeVisible();
        catalogPage.filterCloseBtnShouldBeVisible();
        catalogPage.filterSearchBtnShouldBeVisible();
    }

    @Step
    public void openFilterCategory() {
        catalogPage.openFilterCategoryPopup();
    }

    @Step
    public void verifyFilterCategoriesAreVisible() {
        catalogPage.filterSearchBtnShouldBeVisible();
        catalogPage.closeFilterCategoryWindow();
    }

    @Step
    public void resetFilter() {
        catalogPage.clickFilterReset();
    }

    @Step
    public void closeFilter() {
        catalogPage.closeFilter();
    }

    @Step
    public void openRandomMaster() {
        catalogPage.openRandomMasterProfile();
    }

    @Step
    public void selectFilterSiteAndCategory(String site, String category) {
        catalogPage.openFilterCategoryPopup();
        catalogPage.selectFilterSite(site);
        catalogPage.selectFilterCategory(category);
    }

    @Step
    public void selectFilterCategory(String category) {
        catalogPage.openFilter();
        catalogPage.openFilterCategoryPopup();
        catalogPage.selectFilterCategory(category);
        catalogPage.applyFilter();
    }

    @Step
    public void verifyOpenedCategory(String category) {
        catalogPage.verifyOpenedCategory(category);
    }

    @Step
    public void applyFilter() {
        catalogPage.applyFilter();
        catalogPage.waitForLoaderDisappears();
    }

    @Step
    public int getCategoryMastersCount() {
        return catalogPage.getCategoryMastersCount();
    }

    @Step
    public void applyFilterSort(String sort) {
        catalogPage.openFilter();
        catalogPage.openFilterSort();
        catalogPage.selectSort(sort);
        catalogPage.applyFilter();
        catalogPage.waitForLoaderDisappears();
    }

    @Step
    public void verifyAllMastersHaveQuickCallBadge() {
        catalogPage.verifyMastersHaveQuickCallBadge();
    }

    @Step
    public void verifyAllMastersHave24HoursBadge() {
        catalogPage.verifyAllMastersHave24HoursBadge();
    }

    @Step
    public void verifySortByReviewsCount() {
        catalogPage.verifySortByReviewsCount();
    }

    @Step
    public void orderCallback(String phoneNumber) {
        catalogPage.clickCallMasterButton();
        catalogPage.clickCallbackButton();
        catalogPage.enterCustomerPhoneNumber(phoneNumber);
        catalogPage.selectTimePeriod();
        catalogPage.submitCallback();
        catalogPage.closeCallbackForm();
    }

    @Step
    public void verifyMasterServicePresent() {
        catalogPage.verifyMasterServicePresent();
    }
}
