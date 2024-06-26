package UITests;

import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;
import utils.DataGenerator;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class TC003_MasterForgotPassword extends TestBase {

    @Test
    public void verifyMasterCanResetPassword() throws InterruptedException {
        var master = DataGenerator.getMaster();
        watcher.users.add(master);

        user.register(master, true);
        user.atMasterProfilePage.logsOut();

        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.clickForgotPassword();
        user.atHomePage.requestNewPassword(master.getLogin());

        var smsCode = Admin.getInstance().getSmsCode(master.getLogin());
        user.atHomePage.enterAuthCodeAndSubmit(smsCode, master.getPhoneNumber());

        var newPassword = DataGenerator.getPassword();

        user.atMasterProfileSettingsPage.changePassword(newPassword);
        user.atMasterProfileSettingsPage.logsOut();

        assertThat(user.atHomePage.login(master.getLogin(), master.getPassword(), true))
                .isFalse();

        user.atHomePage.login(master.getLogin(), newPassword, false);
        user.atHomePage.verifyUserIsLoggedIn();
    }
}
