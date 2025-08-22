package UITests.Customer;

import UITests.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.io.IOException;

//Заказчик - Удаление/добавление карты

@RunWith(SerenityRunner.class)
public class CustomerAddPaymentCardTest extends TestBase {

    @Before
    public void setup() throws InterruptedException, IOException {
        var customer = DataGenerator.getCustomer();
        watcher.users.add(customer);
        user.registerAsCustomer(customer);
    }

    @Test
    public void customerAddPaymentCard() {
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfileWalletPage.openWalletTab();
        user.atCustomerProfileWalletPage.openPaymentCardsSection();

        user.atCustomerProfileWalletPage.addPaymentCard("UZCARD", "8600-0200-0000-0000", "12/31", true);
        user.atCustomerProfileWalletPage.verifyUzcardCardAdded();
        user.atCustomerProfileWalletPage.verifyUzcardIsMainCard();

        user.atCustomerProfileWalletPage.addPaymentCard("HUMO", "9860-0100-0000-0000", "12/31", true);
        user.atCustomerProfileWalletPage.verifyHumoCardAdded();
        user.atCustomerProfileWalletPage.verifyHumoIsMainCard();

        user.atCustomerProfileWalletPage.deleteHumoCard();
        user.atCustomerProfileWalletPage.verifyUzcardIsMainCard();
    }
}
