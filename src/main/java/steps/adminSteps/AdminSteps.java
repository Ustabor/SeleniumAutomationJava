package steps.adminSteps;

import entities.Master;
import entities.Category;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AddMasterPage;
import pages.admin.AddServicePage;

public class AdminSteps extends ScenarioSteps {

    @Steps
    public HomePageSteps atAdminHomePage;

    @Steps
    public MastersPageSteps atMastersPage;

    @Steps
    public CategoriesPageSteps atCategoriesPage;

    @Steps
    public PromotionPageSteps atPromotionPage;

    @Steps
    public AddCategoryPageSteps atAddCategoryPage;

    @Steps
    public AddRequestQuestionsPageSteps atAddRequestQuestionsPage;

    @Steps
    public RequestsPageSteps atRequestsPage;

    @Steps
    public AddRequestPageSteps atAddEditRequestPage;

    @Steps
    public CronTasksPageSteps atCronTasksPage;

    @Steps
    public AddMasterPage atAddMasterPage;
    @Steps
    public ServicePricePageSteps atServicePricesPage;
    @Steps
    public ServiceDetailsPageSteps atServiceDetailsPage;
    @Steps
    AddServicePage atAddServicePage;

    public void addTestCategory(Category category) {
        atAdminHomePage.loginAsAdmin();
        atAddCategoryPage.addTestCategory(category);
        atCategoriesPage.getCategoryId(category);
    }

    public void addMoneyToMaster(int amount, Master master, boolean isLoginNeeded) {
        if (isLoginNeeded) {
            atAdminHomePage.loginAsAdmin();
        }

        atMastersPage.addMoneyToMaster(amount, master.getProfileId());
    }

    public void approvePromotion(Category category) {
        atAdminHomePage.loginAsAdmin();
        atPromotionPage.approvePromotion(category);
    }

    public void addServiceQuestions(Category category, String question) {
        atAddRequestQuestionsPage.openPage();
        atAddRequestQuestionsPage.addQuestionToCategory(category, question);
    }

    public void addCategoryService(Category category, boolean price) {
        atAddEditRequestPage.openPage();
        atAddEditRequestPage.fillAddServiceForm(category);
        if (price) {
            atAddEditRequestPage.setPrices();
        }
        atAddEditRequestPage.saveService();
    }

    public void setServicePrices(String country, String minPrice, String maxPrice) {
        atAddRequestQuestionsPage.setPriceForCurrentCountry(country, minPrice, maxPrice);
    }

    @Step
    public void waitForCronTaskCompleted(String taskId, int timeout) {
        atCronTasksPage.waitForCronTaskCompleted(taskId, timeout);
    }

    public void addSubQuestion(String question) {
        atAddRequestQuestionsPage.addSubQuestion(question);
    }

    public void addMaster(Master master) {
        atAddMasterPage.openPage();
        atAddMasterPage.createMaster(master);
        atAddMasterPage.openMasterPage(master);
        atAddMasterPage.setCategoryToMaster(master);
    }

    public void addService(Category category) {
        atAddServicePage.openPage();
        atAddServicePage.createService(category);
    }
}
