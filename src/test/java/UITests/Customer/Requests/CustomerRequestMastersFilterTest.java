package UITests.Customer.Requests;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.Admin;
import utils.DataGenerator;
import utils.XmlParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

//Создание заявки - количество мастеров в индикаторе

@ExtendWith(SerenityJUnit5Extension.class)
@AddCategory(addRequest = true, addRequestPrice = true)
@AddMasters(masters = 2)
public class CustomerRequestMastersFilterTest extends TestBase {

    @BeforeEach
    public void setup() throws TimeoutException, URISyntaxException, InterruptedException {
        user.addMasterProfileImage(getMaster(0), true);
        user.addMasterProfileImage(getMaster(1), false);
        user.atMasterProfilePage.selectService();
        user.atMasterProfilePage.logsOut();
    }
    @Test
    public void verifyMastersCount() throws TimeoutException, InterruptedException, IOException {
        var request = XmlParser.getTextByKey("Service");
        var guest = DataGenerator.getGuestCustomer();

        user.atHomePage.openHomePage();

        user.atHomePage.openPlaceOrderPage();

        user.atPlaceOrderPage.verifyMastersCountMoreThan(2);
        user.atPlaceOrderPage.enterDescription();
        user.atPlaceOrderPage.enterPhoneNumber(guest);
        user.atPlaceOrderPage.clickPlaceOrder();

        user.atPlaceOrderPage.waitForCodeForm();
        var smsCode = user.atPlaceOrderPage.getSmsCode(guest);
        user.atPlaceOrderPage.confirmPhoneNumber(smsCode, guest.getPhoneNumber());

        var customerId = Admin.getInstance().getCustomerId(guest.getPhoneNumber());
        guest.setProfileId(customerId);

        user.atPlaceOrderPage.verifySuccessPageIsVisible();
        user.atPlaceOrderPage.clickFillRequest();

        user.atPlaceOrderPage.selectCategory(category);
        user.atPlaceOrderPage.verifyMastersCountEquals(2);
        user.atPlaceOrderPage.selectRequest(request);
        user.atPlaceOrderPage.verifyMastersCountEquals(2);
    }
}
