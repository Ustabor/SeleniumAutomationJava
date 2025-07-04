package UITests.Customer;

import UITests.TestBase;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

//Заказчик - удаление профиля

@WithTag("smoke")
@RunWith(SerenityRunner.class)
public class CustomerRegistrationTest extends TestBase {

    @Test
    public void customerRegistration() throws InterruptedException, TimeoutException {
        var customer = DataGenerator.getCustomer();
        watcher.users.add(customer);

        user.registerAsCustomer(customer);
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.deleteProfile();
    }
}
