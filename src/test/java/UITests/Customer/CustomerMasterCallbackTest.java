package UITests.Customer;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import entities.Master;
import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;
import utils.DataGenerator;

import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

//Заказчик - обратный звонок мастеру

@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true, addServicePrice = true)
@AddMasters(masters = 1)
public class CustomerMasterCallbackTest extends TestBase {
    private User customer;
    private Master master;


    @Before
    public void setup() throws TimeoutException, InterruptedException, URISyntaxException {
        master = watcher.getMaster();
        user.addMasterProfileImage(master, false);
        user.atMasterProfilePage.selectService();
        user.atMasterProfilePage.logsOut();

        customer = DataGenerator.getCustomer();
        watcher.users.add(customer);
        user.atHomePage.openHomePage();

        user.atHomePage.registerAsCustomer(customer);
        var smsCode = Admin.getInstance().getSmsCode(customer.getPhoneNumber());
        customer.setLastSmsCode(smsCode);
        user.atHomePage.enterAuthCodeAndSubmit(smsCode, customer.getPhoneNumber());
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        customer.setProfileId(user.atCustomerProfilePersonalInfoPage.getCustomerProfileId());
    }

    @Test
    public void masterCallback() throws TimeoutException {
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
