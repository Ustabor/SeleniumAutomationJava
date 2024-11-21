package UITests.Customer.Services;

import UITests.TestBase;
import annotations.AddCategory;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

//Сервисы - подробнее

@RunWith(SerenityRunner.class)
@AddCategory(addService = true)
public class CustomerServiceDetailsTest extends TestBase {

    @Test
    public void customerSelectServiceTest() {
        var customer = DataGenerator.getGuestCustomer();
        watcher.users.add(customer);

        user.atCustomerServicesPage.openPage();
        user.atCustomerServicesPage.selectCategory(category.getName());
        user.atCustomerServicesPage.verifyServiceIsVisible(category.getService());

        user.atCustomerServicesPage.clickServiceDetails();
        user.atCustomerServicesPage.verifyServiceDetails(category.getService());
        user.atCustomerServicesPage.verifyOrderFormIsVisible();
        user.atCustomerServicesPage.closeServiceDetails();

        user.atCustomerServicesPage.clickOrderService();
        user.atCustomerServicesPage.verifyServiceDetails(category.getService());
        user.atCustomerServicesPage.verifyOrderFormIsVisible();
    }
}