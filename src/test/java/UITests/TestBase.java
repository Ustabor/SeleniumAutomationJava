package UITests;

import annotations.AddCategory;
import annotations.AddMasters;
import entities.Category;
import entities.Master;
import entities.RequestResult;
import entities.User;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.core.Serenity;

import net.serenitybdd.annotations.Steps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import steps.UserSteps;
import steps.adminSteps.AdminSteps;
import utils.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class TestBase {
    public final Category category = new Category();
    public List<User> users = new ArrayList<>();
    public User customer;

    @Steps
    public UserSteps user;
    @Steps
    public AdminSteps admin;
    @Managed
    public WebDriver driver;

    @BeforeEach
    public void setUp() throws TimeoutException, InterruptedException, IOException {
        Serenity.throwExceptionsImmediately();

        var annotation = this.getClass().getAnnotation(AddCategory.class);
        if (annotation != null) {
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
                        category,
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
                users.add(master);
                admin.addMaster(master);
                Thread.sleep(500);
            }
        }

        user.atHomePage.openHomePage();
        setCountryLanguageAndLocation();
    }

    @AfterEach
    public void tearDown() throws TimeoutException {
        users.forEach((user) -> {
            if (user.getProfileId() != null) {
                switch (user.getClass().getSimpleName()) {
                    case "Master":
                        try {
                            Admin.getInstance().deleteMaster(user.getProfileId());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "User":
                        try {
                            Admin.getInstance().deleteCustomer(user.getProfileId());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                }
            }
        });

        try {
            if (category.getPromotionAndClickId() != null) {
                Admin.getInstance().deletePromotionAndClick(category.getPromotionAndClickId());
            }
            if (category.getRequestInnerId() != null) {
                Admin.getInstance().deleteRequest(category.getSystemId());
            }
            if (category.getService().getId() != null) {
                Admin.getInstance().deleteUstaborService(category.getService().getId());
            }
            if (category.getRequestServiceId() != null) {
                Admin.getInstance().deleteServiceRequest(category.getRequestServiceId());
            }
            if (category.getSystemId() != null) {
                Admin.getInstance().deleteCategory(category.getSystemId());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getText(String key) {
        return XmlParser.getTextByKey(key);
    }

    public void setBrowserMobileWindowSize() {
        driver.manage().window().setSize(new Dimension(375, 900));
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

    public RequestResult createRequest(boolean logout, boolean assignFree) throws TimeoutException, InterruptedException, IOException {
        var guest = DataGenerator.getGuestCustomer();
//        watcher.users.add(guest);

        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.placeOrder(guest, category);
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atPlaceOrderPage.openRequestsPage();

        var requestInnerId = user.atCustomerProfileRequestsPage.getRequestInnerId();
        category.setRequestInnerId(requestInnerId);
        var requestOuterId = user.atCustomerProfileRequestsPage.getRequestOuterId();

        if (logout) {
            user.atHomePage.logsOut();
        }

        admin.atRequestsPage.openRequestById(requestInnerId);
        admin.atRequestsPage.verifyRequest(guest, category, getText("Question_main"));

        if (assignFree) {
            if (!admin.atRequestsPage.isMasterAssigned()) {
//                admin.atRequestsPage.assignRequestToMasterForFree(watcher.getMaster());
            }
        } else {
            if (!admin.atRequestsPage.isMasterAssigned()) {
//                admin.atRequestsPage.assignRequestToMasterForPayment(watcher.getMaster(0));
//                admin.atRequestsPage.assignRequestToMasterForPayment(watcher.getMaster(1));
            } else {
//                admin.atRequestsPage.reassignRequestToMasterForPayment(watcher.getMaster(0));
//                admin.atRequestsPage.reassignRequestToMasterForPayment(watcher.getMaster(1));
            }
        }

        return new RequestResult(requestInnerId, requestOuterId, guest);
    }

    public Master getMaster() {
        return getMaster(0);
    }

    public Master getMaster(int index) {
        var masters = users
                .stream()
                .filter(user -> user.getClass().getSimpleName().equals("Master"))
                .toArray(User[]::new);

        return (Master) masters[index];
    }
}
