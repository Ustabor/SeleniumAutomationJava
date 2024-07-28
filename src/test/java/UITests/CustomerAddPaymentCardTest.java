package UITests;

import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

//Заказчик - Удаление/добавление карты

@RunWith(SerenityRunner.class)
public class CustomerAddPaymentCardTest extends TestBase {

    private User customer;
    @Before
    public void addCustomer() throws InterruptedException {
        customer = DataGenerator.getCustomer();
        watcher.users.add(customer);

        user.atHomePage.registerAsCustomer(customer);
        var smsCode = user.getSmsCode(customer);
        user.atHomePage.enterAuthCodeAndSubmit(smsCode, customer.getPhoneNumber());

        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        customer.setProfileId(user.atCustomerProfilePersonalInfoPage.getCustomerProfileId());
    }
    @Test
    public void masterAddPaymentCard() {
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
