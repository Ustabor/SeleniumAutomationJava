package UITests;

import annotations.AddCategory;
import annotations.AddMasters;
import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

//Заказчик - обратный звонок мастеру

@RunWith(SerenityRunner.class)
@AddCategory()
@AddMasters(masters = 1, useAdminSite = true)
public class CustomerMasterCallbackTest extends TestBase {
    protected User customer;

    @Before
    public void setUp() throws TimeoutException, InterruptedException {
        super.setUp();

        customer = DataGenerator.getCustomer();
        watcher.users.add(customer);
        user.atHomePage.openHomePage();
        setCountryLanguageAndLocation();

        user.atHomePage.registerAsCustomer(customer);
        var smsCode = Admin.getInstance().getSmsCode(customer.getPhoneNumber());
        customer.setLastSmsCode(smsCode);
        user.atHomePage.enterAuthCodeAndSubmit(smsCode, customer.getPhoneNumber());
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        customer.setProfileId(user.atCustomerProfilePersonalInfoPage.getCustomerProfileId());
    }

    @Test
    public void masterCallback() throws TimeoutException {
        var master = watcher.getMaster();
        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(master.getCategory().getName());
        user.atCatalogPage.orderCallback(customer.getPhoneNumber());
        user.atHomePage.logsOut();

        user.atHomePage.login(master, true);
        user.atMasterProfilePage.openProfilePage();
        user.atMasterProfilePage.openNotificationTab();
        user.atMasterNotificationsPage.verifyNewCallbackNotification();
    }
}
