package UITests.Master.Registration;

import UITests.TestBase;
import entities.Master;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;
import utils.DataGenerator;

import java.io.IOException;

@RunWith(SerenityRunner.class)
public class MasterForgotPasswordTest extends TestBase {

    private final Master master = DataGenerator.getMaster();

    @Before
    public void setup() throws InterruptedException, IOException {
        watcher.users.add(master);

        user.register(master, true);
        user.atMasterProfilePage.logsOut();
    }

    @Test
    public void verifyMasterCanResetPassword() throws InterruptedException, IOException {
        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.clickForgotPassword();
        user.atHomePage.requestNewPassword(master.getLogin());

        var smsCode = Admin.getInstance().getSmsCode(master.getLogin());
        user.atHomePage.enterAuthCodeAndSubmit(smsCode, master.getPhoneNumber());

        var newPassword = DataGenerator.getPassword();
        user.atMasterProfileSettingsPage.changePassword(newPassword);
        user.atMasterProfileSettingsPage.logsOut();

        user.atHomePage.loginIfNeeded(master);
        user.atHomePage.verifyUserIsLoggedOut();

        user.atHomePage.login(master.getLogin(), newPassword, false);
        user.atHomePage.verifyUserIsLoggedIn();
    }
}
