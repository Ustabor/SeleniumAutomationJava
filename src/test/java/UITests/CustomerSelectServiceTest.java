package UITests;

import annotations.AddCategory;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

//Сервисы - заказ

@RunWith(SerenityRunner.class)
@AddCategory(addService = true)
public class CustomerSelectServiceTest extends TestBase {

    @Test
    public void customerSelectServiceTest() {
        var customer = DataGenerator.getGuestCustomer();
        watcher.users.add(customer);

        user.atCustomerServicesPage.openPage();
        user.atCustomerServicesPage.selectCategory(category.getName());
        user.atCustomerServicesPage.verifyServiceIsVisible(category.getService());

        user.atCustomerServicesPage.clickServiceDetails();
        user.atCustomerServicesPage.verifyServiceDetails(category.getService());

        user.atCustomerServicesPage.clickOrderService();
        user.atCustomerServicesPage.increaseServicesCount();
        user.atCustomerServicesPage.verifyPrice(String.valueOf(category.getService().getPrice() * 2));
        user.atCustomerServicesPage.enterAddress();
        user.atCustomerServicesPage.submitServiceOrder();

        user.atCustomerServicesPage.verifyConfirmationInfo(
                customer,
                String.valueOf(category.getService().getPrice() * 2)
        );

        user.atCustomerServicesPage.enterPaymentCardInfo("UZCARD", "8600-0200-0000-0000", "12/31");
//        user.atCustomerServicesPage.enterSmsCode();
//        user.atCustomerProfilePersonalInfoPage.openCustomerProfile();
    }
}
