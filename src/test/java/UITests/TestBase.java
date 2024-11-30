package UITests;

import annotations.AddCategory;
import annotations.AddMasters;
import entities.Category;
import entities.RequestResult;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.core.Serenity;

import net.serenitybdd.annotations.Steps;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import steps.UserSteps;
import steps.adminSteps.AdminSteps;
import utils.*;

import java.util.concurrent.TimeoutException;


public class TestBase {
    public final Category category = new Category();

    @Rule
    public Watcher watcher = new Watcher();

    @Steps
    public UserSteps user;

    @Steps
    public AdminSteps admin;

    @Managed
    public WebDriver driver;

    @Before
    public void setUp() throws TimeoutException, InterruptedException {
        Serenity.throwExceptionsImmediately();
        Admin.getInstance();

        var annotation = this.getClass().getAnnotation(AddCategory.class);
        if (annotation != null) {
            watcher.category = category;
            admin.addTestCategory(category);

            if (annotation.addRequest()) {
                admin.addRequest(category, annotation.addRequestPrice());
                admin.addRequestRootQuestion(category, getText("Question_main"));
                admin.setRequestQuestionPrices(Config.getCountry(), "100", "200");

                for(int i = 1; i < annotation.requestQuestionsCount(); i++) {
                    var question = getText(String.format("Question_%d", i));
                    admin.addSubQuestion(question);
                    Thread.sleep(500);
                    admin.addSubQuestion(question);
                    Thread.sleep(500);
                }

                admin.atServicePricesPage.addServicePrice(category);
            }

            if (annotation.promotionAndClickPrice()) {
                admin.atCategoriesPage.setPromotionAndClickPrice(
                        category.getSystemId(),
                        "100",
                        "200",
                        "1000");
            }

            if (annotation.addService()) {
                admin.addService(category);
            }
        }

        var mastersAnno = this.getClass().getAnnotation(AddMasters.class);
        if (mastersAnno != null) {
            for (int i = 0; i < mastersAnno.masters(); i++) {
                var master = DataGenerator.getMaster(category);
                watcher.users.add(master);
                admin.addMaster(master);
                Thread.sleep(500);
            }
        }

        user.atHomePage.openHomePage();
        setCountryLanguageAndLocation();
    }

    public String getText(String key) {
        return XmlParser.getTextByKey(key);
    }

    public void setBrowserMobileWindowSize() {
        driver.manage().window().setSize(new Dimension(375, 667));
        driver.navigate().refresh();
    }

    public void setCountryLanguageAndLocation() {
        user.atHomePage.setLanguage(Config.getLang());

        if (Config.isUstabor() || Config.isBildrlist()) {
            user.atHomePage.selectCity(getText(Config.getCountryCode() + "_city"));
            return;
        }

        user.atHomePage.selectLocation(Config.getCountry(), getText(Config.getCountryCode() + "_city"));
    }

    public RequestResult createRequest(boolean logout, boolean assignFree) throws TimeoutException, InterruptedException {
        var guest = DataGenerator.getGuestCustomer();
        watcher.users.add(guest);

        user.atHomePage.openHomePage();
        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.placeOrder(guest, category);
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atPlaceOrderPage.openRequestsPage();

        guest.setProfileId(user.atCustomerProfileRequestsPage.getCustomerProfileId());
        var requestId = user.atCustomerProfileRequestsPage.getRequestId();

        if (logout) {
            user.atHomePage.logsOut();
        }

        admin.atRequestsPage.openRequestById(requestId);
        admin.atRequestsPage.verifyRequest(guest, category, getText("Question_main"));

        if (assignFree) {
            if (!admin.atRequestsPage.isMasterAssigned()) {
                admin.atRequestsPage.assignRequestToMasterForFree(watcher.getMaster());
            }
        } else {
            if (!admin.atRequestsPage.isMasterAssigned()) {
                admin.atRequestsPage.assignRequestToMasterForPayment(watcher.getMaster(0));
                admin.atRequestsPage.assignRequestToMasterForPayment(watcher.getMaster(1));
            } else {
                admin.atRequestsPage.reassignRequestToMasterForPayment(watcher.getMaster(0));
                admin.atRequestsPage.reassignRequestToMasterForPayment(watcher.getMaster(1));
            }
        }

        return new RequestResult(requestId, guest);
    }
}
