package UITests.Customer;

import UITests.TestBase;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.DataGenerator;

import java.io.IOException;

//Заказчик - удаление профиля

@WithTag("smoke")
@ExtendWith(SerenityJUnit5Extension.class)
public class CustomerRegistrationTest extends TestBase {

    @Test
    public void customerRegistration() throws InterruptedException, IOException {
        var customer = DataGenerator.getCustomer();
        users.add(customer);

        user.registerAsCustomer(customer);
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.deleteProfile();
        customer.setProfileId("");
    }
}
