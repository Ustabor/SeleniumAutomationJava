package UITests.Customer.Services;

import UITests.TestBase;
import annotations.AddCategory;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;
import utils.DataGenerator;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//Сервисы - заказ

@WithTag("smoke")
@RunWith(SerenityRunner.class)
@AddCategory(addService = true)
public class CustomerSelectServiceTest extends TestBase {

    @Test
    public void customerSelectServiceTest() throws InterruptedException, TimeoutException, IOException {
        var customer = DataGenerator.getGuestCustomer();
        watcher.users.add(customer);

        user.atCustomerServicesPage.openPage();
        user.atCustomerServicesPage.selectCategory(category.getName());
        user.atCustomerServicesPage.verifyServiceIsVisible(category.getService());

        user.atCustomerServicesPage.clickOrderService();
        user.atCustomerServicesPage.enterCustomerInfo(customer);
        user.atCustomerServicesPage.increaseServicesCount();
        user.atCustomerServicesPage.verifyPrice(String.valueOf(category.getService().getPrice() * 2));
        user.atCustomerServicesPage.submitServiceOrder();

        user.atCustomerServicesPage.waitForCodeForm();
        var smsCode = user.atCustomerServicesPage.getSmsCode(customer);
        user.atCustomerServicesPage.confirmPhoneNumber(smsCode, customer.getPhoneNumber());

        var customerId = Admin.getInstance().getCustomerId(customer.getPhoneNumber());
        customer.setProfileId(customerId);

        var serviceId = user.atCustomerServicesPage.getServiceId();
        user.atCustomerServicesPage.verifyConfirmationInfo(
                customer,
                String.valueOf(category.getService().getPrice() * 2)
        );

        admin.atServiceDetailsPage.clickSendPaymentLink(serviceId);

        var serviceUrl = user.atCustomerServicesPage.getUrlFromSms(customer);
        user.atHomePage.openHomePage();
        user.atHomePage.logsOut();
        user.atHomePage.openUrl(serviceUrl);
//        user.atCustomerServicesPage.enterPaymentCardInfo("UZCARD", "8600-0200-0000-0000", "12/31");

        user.atCustomerProfilePersonalInfoPage.openCustomerProfile();
        user.atCustomerProfileRequestsPage.openRequestsPage();
        user.atCustomerProfileRequestsPage.verifyServiceRequestCreated();
    }
}
