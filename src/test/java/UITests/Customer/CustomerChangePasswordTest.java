package UITests.Customer;

import UITests.TestBase;
import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;

//Заказчик - изменить пароль

@RunWith(SerenityRunner.class)
public class CustomerChangePasswordTest extends TestBase {

    private final User customer = DataGenerator.getCustomer();

    @Before
    public void setup() throws InterruptedException {
        watcher.users.add(customer);
        user.registerAsCustomer(customer);
    }

    @Test
    public void customerChangePassword() {
        var newPassword = DataGenerator.getPassword();
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.changePassword(newPassword);

        user.atCustomerProfilePersonalInfoPage.logsOut();
        user.atHomePage.loginIfNeeded(customer);
        user.atHomePage.verifyUserIsLoggedOut();

        user.atHomePage.login(customer.getLogin(), newPassword, false);
        user.atHomePage.verifyUserIsLoggedIn();
    }

    @After
    public void tearDown() throws TimeoutException {
        user.atHomePage.openHomePage();
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.deleteProfile();
    }
}
