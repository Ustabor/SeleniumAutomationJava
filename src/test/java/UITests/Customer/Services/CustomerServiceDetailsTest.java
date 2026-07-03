package UITests.Customer.Services;

import UITests.TestBase;
import annotations.AddCategory;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.DataGenerator;

//Сервисы - подробнее

@ExtendWith(SerenityJUnit5Extension.class)
@AddCategory(addService = true)
public class CustomerServiceDetailsTest extends TestBase {

    @Test
    public void customerSelectServiceTest() {
        var customer = DataGenerator.getGuestCustomer();
        users.add(customer);

        user.atCustomerServicesPage.openPage();
        user.atCustomerServicesPage.selectCategory(category.getName());
        user.atCustomerServicesPage.verifyServiceIsVisible(category.getService());

        user.atCustomerServicesPage.clickServiceDetails();
        user.atCustomerServicesPage.verifyServiceDetails(category.getService());
        user.atCustomerServicesPage.verifyOrderFormIsVisible();
        user.atCustomerServicesPage.closeServiceDetails();

        user.atCustomerServicesPage.selectCategory(category.getName());
        user.atCustomerServicesPage.clickOrderService();
        user.atCustomerServicesPage.verifyServiceDetails(category.getService());
        user.atCustomerServicesPage.verifyOrderFormIsVisible();
    }
}