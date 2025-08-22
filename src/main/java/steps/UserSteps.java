package steps;

import entities.Master;
import entities.User;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AddServicePage;
import pages.customerProfile.CustomerProfileWalletPage;
import steps.customerProfileSteps.CustomerProfilePersonalInfoPageSteps;
import steps.customerProfileSteps.CustomerProfileRequestsPageSteps;
import steps.customerProfileSteps.CustomerProfileWalletPageSteps;
import steps.masterProfileSteps.*;
import utils.Admin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

public class UserSteps extends ScenarioSteps {

    @Steps
    public HomePageSteps atHomePage;
    @Steps
    public CatalogPageSteps atCatalogPage;
    @Steps
    public CustomerProfilePersonalInfoPageSteps atCustomerProfilePersonalInfoPage;
    @Steps
    public MasterProfilePageSteps atMasterProfilePage;
    @Steps
    public MasterProjectsPageSteps atMasterProjectsPage;
    @Steps
    public MasterPromotionPageSteps atMasterPromotionPage;
    @Steps
    public MasterProfileSettingsPageSteps atMasterProfileSettingsPage;
    @Steps
    public MasterWalletPageSteps atMasterWalletPage;
    @Steps
    public MasterNotificationsPageSteps atMasterNotificationsPage;
    @Steps
    public MasterFaqPageSteps atMasterFaqPage;
    @Steps
    public PlaceOrderPageSteps atPlaceOrderPage;
    @Steps
    public CustomerProfileRequestsPageSteps atCustomerProfileRequestsPage;
    @Steps
    public MasterProfileRequestsPageSteps atMasterProfileRequestsPage;
    @Steps
    public CustomerRequestPageSteps atCustomerRequestPage;
    @Steps
    public MasterRequestPageSteps atMasterRequestPage;
    @Steps
    public CustomerProfileWalletPageSteps atCustomerProfileWalletPage;
    @Steps
    public CustomerServicePageSteps atCustomerServicesPage;

    @Step
    public void register(Master master, boolean randomCategory) throws InterruptedException, IOException {
        atHomePage.registerAsMaster(master, randomCategory);

        var smsCode = Admin.getInstance().getSmsCode(master.getPhoneNumber());
        atHomePage.enterAuthCodeAndSubmit(smsCode, master.getPhoneNumber());
        atMasterProfilePage.masterProfilePagePageShouldBeVisible();

        master.setLastSmsCode(smsCode);
        master.setProfileId(atMasterProfilePage.getProfileId());
    }

    @Step
    public void registerAsCustomer(User customer) throws InterruptedException, IOException {
        atHomePage.registerAsCustomer(customer);

        var smsCode = getSmsCode(customer);
        atHomePage.enterAuthCodeAndSubmit(smsCode, customer.getPhoneNumber());

        var customerId = Admin.getInstance().getCustomerId(customer.getPhoneNumber());
        customer.setProfileId(customerId);

        var password = Admin.getInstance().getSmsPassword(customer.getPhoneNumber());
        customer.setPassword(password);
    }

    @Step
    public String getSmsCode(User user) throws InterruptedException, IOException {
        var smsCode = Admin.getInstance().getSmsCode(user.getPhoneNumber());

        if (smsCode == null) {
            atHomePage.resendCode();
            Thread.sleep(5000);
        }

        return Admin.getInstance().getSmsCode(user.getPhoneNumber());
    }

    @Step
    public void addMasterProfileImage(Master master, boolean logout) throws TimeoutException, URISyntaxException, InterruptedException {
        atHomePage.openHomePage();
        atHomePage.login(master, true);
        atMasterProfilePage.openProfilePage();
        atMasterProfilePage.openProfileSettings();
        atMasterProfilePage.uploadProfileImage();
        if (logout) atMasterProfilePage.logsOut();
    }
}
