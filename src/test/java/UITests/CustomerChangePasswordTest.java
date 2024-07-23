package UITests;

import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;

//Заказчик - изменить пароль

@RunWith(SerenityRunner.class)
public class CustomerChangePasswordTest extends TestBase {

    private User customer;

    @Test
    public void customerChangePassword() throws InterruptedException {
        customer = DataGenerator.getCustomer();
        watcher.users.add(customer);

        user.atHomePage.registerAsCustomer(customer);
        var smsCode = user.getSmsCode(customer);
        user.atHomePage.enterAuthCodeAndSubmit(smsCode, customer.getPhoneNumber());

        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        customer.setProfileId(user.atCustomerProfilePersonalInfoPage.getCustomerProfileId());

        var newPassword = DataGenerator.getPassword();
        user.atCustomerProfilePersonalInfoPage.changePassword(newPassword);

        user.atCustomerProfilePersonalInfoPage.logsOut();
        assertThat(user.atHomePage.login(customer.getLogin(), customer.getPassword(), true))
                .isFalse();

        user.atHomePage.login(customer.getLogin(), newPassword, false);
        user.atHomePage.verifyUserIsLoggedIn();
    }

    @After
    public void tearDown() throws TimeoutException {
        user.atHomePage.openHomePage();
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.deleteProfile();
        customer.setProfileId(null);
    }
}
