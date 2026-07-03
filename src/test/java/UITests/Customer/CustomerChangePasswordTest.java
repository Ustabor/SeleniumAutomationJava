package UITests.Customer;

import UITests.TestBase;
import entities.User;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.DataGenerator;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//Заказчик - изменить пароль

@ExtendWith(SerenityJUnit5Extension.class)
public class CustomerChangePasswordTest extends TestBase {

    private final User customer = DataGenerator.getCustomer();

    @BeforeEach
    public void setup() throws InterruptedException, IOException {
        users.add(customer);
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

    @AfterEach
    public void tearDown() throws TimeoutException {
        user.atHomePage.openHomePage();
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.deleteProfile();
        customer.setProfileId("");
    }
}
