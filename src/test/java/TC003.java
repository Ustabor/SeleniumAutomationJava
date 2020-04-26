import entities.Master;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Email;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class TC003 extends TestBase {

    private Master master;

    @Test
    public void masterAccountForgotPassword() throws Exception {
        master = data.getFullInfoMasterValidEmail(Email.INSTANCE.getEmail());

        user.atHomePage.registerAsMaster(master);
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();
        user.atMasterProfilePage.logsOut();

        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.clickForgotPassword();
        user.atHomePage.requestNewPasswordAtEmail(master.getLogin());

        var url = Email.INSTANCE.getForgotPasswordUrl();
        user.atMasterProfileSettingsPage.open(url);
        String newPassword = data.getPassword();
        user.atMasterProfileSettingsPage.changePassword(newPassword);
        user.atMasterProfileSettingsPage.logsOut();

        assertThat(user.atHomePage.loginAsMaster(
                master.getLogin(), master.getPassword(), true))
                .isFalse();
        user.atHomePage.loginAsMaster(master.getLogin(), newPassword, false);
        user.atHomePage.verifyUserIsLoggedIn();
    }

    @After
    public void tearDown() {
        admin.atAdminHomePage.loginAsAdmin();
        if (master.getProfileId() != null) {
            admin.atMastersPage.deleteMaster(master);
        }
    }
}
