package UITests.Customer.Requests;

import UITests.TestBase;
import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;
import utils.XmlParser;

import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

//Создание заявки - количество мастеров в индикаторе

@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true, addServicePrice = true)
@AddMasters(masters = 2)
public class CustomerRequestMastersFilterTest extends TestBase {

    @Before
    public void setup() throws TimeoutException, URISyntaxException, InterruptedException {
        user.addMasterProfileImage(watcher.getMaster(0), true);
        user.addMasterProfileImage(watcher.getMaster(1), false);
        user.atMasterProfilePage.selectService();
        user.atMasterProfilePage.logsOut();
    }
    @Test
    public void verifyMastersCount() throws TimeoutException, InterruptedException {
        var request = XmlParser.getTextByKey("Service");
        var guest = DataGenerator.getGuestCustomer();

        user.atHomePage.openHomePage();

        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.selectBuildDomain();

        user.atPlaceOrderPage.verifyMastersCountMoreThan(2);
        user.atPlaceOrderPage.enterDescription();
        user.atPlaceOrderPage.enterPhoneNumber(guest);
        user.atPlaceOrderPage.clickPlaceOrder();

        user.atPlaceOrderPage.waitForCodeForm();
        var smsCode = user.atPlaceOrderPage.getSmsCode(guest);
        user.atPlaceOrderPage.confirmPhoneNumber(smsCode, guest.getPhoneNumber());

        user.atPlaceOrderPage.verifySuccessPageIsVisible();
        user.atPlaceOrderPage.clickFillRequest();

        user.atPlaceOrderPage.selectCategory(category);
        user.atPlaceOrderPage.verifyMastersCountEquals(2);
        user.atPlaceOrderPage.selectRequest(request);
        user.atPlaceOrderPage.verifyMastersCountEquals(1);
    }
}
