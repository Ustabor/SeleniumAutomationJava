package pages;

import entities.Master;
import freemarker.template.utility.NullArgumentException;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;


public class CatalogPage extends SearchBlock {

    private static final String masterNameXpath = ".//div[@class='presentation']";
    private static final String ratingXpath = ".//div[contains(@class,'rating')]";
    private static final String reviewsCountXpath = ".//span[@class='reviews-count']";
    private static final String avatarXpath = ".//div[@class='image']";
    private static final String districtXpath = "//div[@class='item-wrapper' and text()='%s']";
    private static final String sortXpath = "//div[@class='item-wrapper' and text()='%s']";
    private static final String filterSite = "//div[@id='categories']//li[text()='%s']";
    private static final String filterCategory = "//a[./span[text()='%s']]";

    private static final Logger logger = LoggerFactory.getLogger(CatalogPage.class);

    @FindBy(xpath = "//div[contains(@class, 'row-sites')]//a[@class='btn-catalog']")
    private WebElementFacade catalogButton;

    @FindBy(xpath = "//table[@class='services']")
    private WebElementFacade masterServicesTable;

    //region Catalog header elements
    @FindBy(xpath = "//a[contains(@class, 'request')]")
    private WebElementFacade requestsTab;
    @FindBy(xpath = "//a[contains(@class, 'catalog')]")
    private WebElementFacade mastersTab;
    @FindBy(xpath = "//a[@class='prices']")
    private WebElementFacade pricesTab;
    @FindBy(xpath = "//a[@class='services']")
    private WebElementFacade servicesTab;
    @FindBy(xpath = "//div[@class='chat']")
    private WebElementFacade chatTab;
    @FindBy(xpath = "//div[@id='btn-filter']")
    private WebElementFacade filterButton;
    @FindBy(xpath = "//div[@class='ui-selectbox-button']")
    private WebElementFacade dropdownSelectedCategory;
    @FindBy(xpath = "//div[@id='projects-gallery']//a[not(contains(@data-ad, '1'))]")
    private List<WebElementFacade> mastersCards;
    @FindBy(xpath = "//div[@id='projects-gallery']//a[not(contains(@data-ad, '1'))]//span[@class='emergency']")
    private List<WebElementFacade> emergencyBadges;
    @FindBy(xpath = "//div[@id='projects-gallery']//a[not(contains(@data-ad, '1'))]//span[@class='b24_7']")
    private List<WebElementFacade> hours24Badges;
    @FindBy(xpath = "//button[@id='load-more']")
    private WebElementFacade loadMoreButton;
    //endregion

    //region Filter elements
    @FindBy(xpath = "//div[@id='btn-filter']")
    private WebElementFacade filterBtn;

    @FindBy(xpath = "//div[./input[@id='category_id']]/div")
    private WebElementFacade filterCategoryBtn;

    @FindBy(xpath = "//div[./select[@id='city_id']]/div")
    private WebElementFacade filterCityBtn;

    @FindBy(xpath = "//div[@class='ui-selectbox district']")
    private WebElementFacade filterDistrictBtn;

    @FindBy(xpath = "//div[contains(@class,'item')]")
    private List<WebElementFacade> filterCitiesList;

    @FindBy(xpath = "//div[./select[@id='order']]/div")
    private WebElementFacade filterSortingBtn;

    @FindBy(xpath = "//div[@class='catalog-filter']//button[@type='submit']")
    private WebElementFacade filterSubmitBtn;

    @FindBy(xpath = "//div[@class='catalog-filter']//div[@class='btn-close']")
    private WebElementFacade filterCloseBtn;

    @FindBy(xpath = "//button[@type='reset']")
    private WebElementFacade filterResetBtn;

    @FindBy(xpath = "//div[@class='window window-categories']//div[@id='categories']")
    private WebElementFacade filterCategoryWindow;

    @FindBy(xpath = "//div[@class='window window-categories']//div[@class='btn-close']")
    private WebElementFacade filterCategoryWindowCloseBtn;

    @FindBy(xpath = "//div[contains(@class, 'ui-selectbox icon order')]")
    private WebElementFacade filterOrderButton;
    //endregion

    //region Callback form
    @FindBy(xpath = "//a//button[@class='button-contacts icon']")
    private WebElementFacade callMasterButton;
    @FindBy(xpath = "//div[contains(@class, 'master-info')]/div[@class='btn']")
    private WebElementFacade callbackButton;
    @FindBy(xpath = "//input[@type='tel']")
    private WebElementFacade callbackPhoneInput;
    @FindBy(xpath = "//div[@class='radio-item']")
    private WebElementFacade morningTimePeriod;
    @FindBy(xpath = "//form[@id='form-callback']//button[@class='btn-submit']")
    private WebElementFacade submitCallback;
    @FindBy(xpath = "//div[@class='window-shadow' and .//div[@id='window-master-contact']]//div[@class='btn-close']")
    private WebElementFacade closeCallback;
    //endregion




    @FindBy(xpath = "//nav[@class='breadcrumbs']/a")
    private List<WebElementFacade> headerNavigationElements;

    @FindBy(xpath = "//div[@class='ui-selectbox icon category']")
    private WebElementFacade selectedCategory;

    @FindBy(xpath = "//div[contains(@class,'window-contacts')]")
    private WebElementFacade projectContactPopup;

    @FindBy(xpath = "//div[contains(@class,'window-contacts')]//div[@class='btn-close']")
    private WebElementFacade closeContactPopup;

    @FindBy(xpath = "//div[@class='results']")
    private WebElementFacade projectsCounter;

    @FindBy(xpath = "//div[@class='catalog-search-empty']//div[@class='text']")
    private WebElementFacade emptyCatalogMessage;

    //region New
    @FindBy(xpath = "//div[@id='projects-gallery']/a[not(@data-ad)]")
    private List<WebElementFacade> mastersList;
    //endregion

    public void openPage() {
        catalogButton.click();
    }

    public void clickPricesTab() {
        pricesTab.click();
    }

    public void verifyOpenedCategory(String category) {
        mastersTab.shouldContainText(category);
    }

    public void verifyPricesShowsForCategory(String text) {
        dropdownSelectedCategory.shouldContainText(text);
    }

    public void verifyTabsAreVisible() {
        requestsTab.shouldBeVisible();
        mastersTab.shouldBeVisible();
        pricesTab.shouldBeVisible();
        servicesTab.shouldBeVisible();
    }

    public void verifyFilterButtonIsVisible() {
        filterButton.shouldBeVisible();
    }

    public void verifyMastersCardsAreVisible() {
        assertThat(mastersCards.isEmpty()).isFalse();
    }

    public void clickLoadMoreButton() {
        loadMoreButton.click();
    }

    public int getMastersCardsCount() {
        return mastersCards.size();
    }

    //region Filter actions
    public void openFilter() {
        filterBtn.click();
        waitForLoaderDisappears();
    }

    public void filterCategoryBtnShouldBeVisible() {
        filterCategoryBtn.shouldBeVisible();
    }

    public void filterCityBtnShouldBeVisible() {
        filterCityBtn.shouldBeVisible();
    }

    public void filterSortingBtnShouldBeVisible() {
        filterSortingBtn.shouldBeVisible();
    }

    public void filterSearchBtnShouldBeVisible() {
        filterSubmitBtn.shouldBeVisible();
    }

    public void filterResetBtnShouldBeVisible() {
        filterResetBtn.shouldBeVisible();
    }

    public void filterCloseBtnShouldBeVisible() {
        filterCloseBtn.shouldBeVisible();
    }

    public void openFilterCategoryPopup() {
        filterCategoryBtn.click();
    }

    public void selectFilterSite(String site) {
        getDriver().findElement(By.xpath(String.format(filterSite, site))).click();
    }

    public void selectFilterCategory(String category) {
        getDriver().findElement(By.xpath(String.format(filterCategory, category))).click();
    }

    public void closeFilterCategoryWindow() {
        filterCategoryWindowCloseBtn.click();
    }

    public void openFilterCityDropdown() {
        filterCityBtn.click();
    }

    public void selectCity(String expectedCity) {
        WebElementFacade foundCity = filterCitiesList.stream()
                .filter(city -> expectedCity.contains(city.getText()))
                .findFirst()
                .orElse(null);

        if (foundCity == null) throw new NullArgumentException(
                String.format("%s is not in cities list", expectedCity));

        foundCity.click();
    }

    public void openDistrictDropdown() {
        filterDistrictBtn.click();
    }

    public void selectDistrict(String districtName) {
        getDriver().findElement(By.xpath(String.format(districtXpath, districtName))).click();
    }

    public void openFilterSort() {
        filterOrderButton.click();
    }

    public void selectSort(String sort) {
        getDriver().findElement(By.xpath(String.format(sortXpath, sort))).click();
    }

    public void applyFilter() {
        filterSubmitBtn.click();
    }

    public void clickFilterReset() {
        filterResetBtn.click();
    }

    public void closeFilter() {
        filterCloseBtn.click();
    }
    //endregion

    public void openRandomMasterProfile() {
        var number = ThreadLocalRandom.current().nextInt(mastersList.size());
        var randomMaster = mastersList.get(number);
        randomMaster.findElement(By.xpath(avatarXpath)).click();
    }

    public void verifyMastersHaveQuickCallBadge() {
        assertThat(mastersCards.size()).isEqualTo(emergencyBadges.size());
    }

    public void verifyAllMastersHave24HoursBadge() {
        assertThat(mastersCards.size()).isEqualTo(hours24Badges.size());
    }

    public void verifySortByReviewsCount() {
        var first = mastersCards.get(0)
                .findElement(By.xpath("//span[@class='reviews-count']"))
                .getText()
                .replaceAll("[^0-9]", "");
        var second = mastersCards.get(1)
                .findElement(By.xpath("//span[@class='reviews-count']"))
                .getText()
                .replaceAll("[^0-9]", "");
        assertThat(Integer.valueOf(first)).isGreaterThanOrEqualTo(Integer.valueOf(second));
    }

    public int getCategoryMastersCount() {
        var count = mastersTab.findElement(By.xpath("//i")).getText();
        return Integer.parseInt(count);
    }



    public void verifySelectedCategoryEquals(String expectedCategory) {
        assertThat(selectedCategory.getText()).isEqualTo(expectedCategory);
    }

    public void openMasterContactsByName(String masterName) {
        var master = mastersList.stream()
                .filter(p -> p.getText().contains(masterName)).findFirst().orElse(null);

        if (master == null) {
            throw new NullPointerException(String.format("%s was not found", masterName));
        }

        master.findElement(By.xpath(".//button[@class='button-contacts icon']")).click();
    }

    public void projectContactPopupShouldBeVisible(){
        projectContactPopup.shouldBeVisible();
    }

    public void closeContactPopup() {
        closeContactPopup.click();
    }

    public void contactPopupShouldNotBeVisible() {
        projectContactPopup.shouldNotBeVisible();
    }

    public void verifyCategoryFilterBtnTextIsNot(String text) {
        assertThat(filterCategoryBtn.getText()).isNotEqualTo(text);
    }

    public void verifyCityFilterTextIsNot(String city) {
        assertThat(filterCityBtn.getText()).isNotEqualTo(city);
    }

    public void verifyCityFilterText(String city) {
        assertThat(filterCityBtn.getText()).isEqualTo(city);
    }

    public void verifyDistrictFilterText(String district) {
        assertThat(filterDistrictBtn.getText()).isEqualTo(district);
    }

    public int getProjectsCounterValue() {
        if (isSearchCatalogEmpty()) {
            return 0;
        }
        return Integer.parseInt(projectsCounter
                .getText()
                .replaceAll("[^0-9]", ""));
    }

    public boolean isSearchCatalogEmpty() {
        setTimeouts(1, ChronoUnit.SECONDS);
        boolean result = emptyCatalogMessage.isVisible();
        resetTimeouts();
        return result;
    }

    private Master getMasterInfo(WebElementFacade selectedMaster) {
        var master = new Master();
        master.setFirstName(selectedMaster.findElement(By.xpath(masterNameXpath)).getText());
        master.setRating(selectedMaster.findElement(By.xpath(ratingXpath)).getAttribute("class"));

        if (!selectedMaster.findElements(By.xpath(reviewsCountXpath)).isEmpty()) {
            master.setFeedback(selectedMaster.findElement(By.xpath(reviewsCountXpath)).getText().replaceAll("[^0-9]", ""));
        }

        return master;
    }

    public void verifyMasterWithBadges(Master master) {
        int badge = Integer.parseInt(mastersList.get(0).getAttribute("data-id"));
        int expected = Integer.parseInt(master.getProfileId());
        assertThat(badge).isEqualTo(expected);
    }

    public void verifyMasterAtFirstPosition(Master master) {
        logger.info("Master id: " + master.getProfileId());
        logger.info("Promo id: " + master.getCategory().getPromoId());
        var firstMaster = mastersList.stream().findFirst();
        int dataId = Integer.parseInt(firstMaster.get().getAttribute("data-id"));
        int promoId = Integer.parseInt(firstMaster.get().getAttribute("data-promotion").replaceAll("\\s", ""));

        assertThat(dataId).isEqualTo(Integer.parseInt(master.getProfileId()));
        assertThat(promoId).isEqualTo(1);
    }

    public void verifyMastersSortedByRate(Master master, int rating) {
        var masterRating = 0;

        var attribute = mastersList
                .get(0)
                .findElements(By.xpath(ratingXpath))
                .get(0)
                .getAttribute("class");

        var ratingString = attribute.substring(attribute.length() - 1);

        if (!ratingString.equals("")) {
            masterRating = Integer.parseInt(ratingString);
        }

        assertThat(mastersList.get(0).getAttribute("data-id")).isEqualTo(master.getProfileId());
        assertThat(masterRating).isEqualTo(rating);
    }

    public boolean isBreadcrumbsLongEnough() {
        return headerNavigationElements.size() > 3;
    }

    public void clickCallMasterButton() {
        callMasterButton.click();
    }

    public void clickCallbackButton() {
        callbackButton.click();
    }

    public void enterCustomerPhoneNumber(String phoneNumber) {
        callbackPhoneInput.sendKeys(phoneNumber);
    }

    public void selectTimePeriod() {
        morningTimePeriod.click();
    }

    public void submitCallback() {
        submitCallback.click();
    }

    public void closeCallbackForm() {
        closeCallback.click();
    }

    public void verifyMasterServicePresent() {
        masterServicesTable.shouldContainText("Autotest");
        masterServicesTable.shouldContainText("100");
    }
}
