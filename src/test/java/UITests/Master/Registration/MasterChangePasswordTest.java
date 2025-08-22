package UITests.Master.Registration;

import UITests.TestBase;
import entities.Master;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.io.IOException;

// Мастер - изменить пароль

@RunWith(SerenityRunner.class)
public class MasterChangePasswordTest extends TestBase {

    private final Master master = DataGenerator.getMaster();

    @Before
    public void setup() throws InterruptedException, IOException {
        watcher.users.add(master);

        user.register(master, true);
    }

    @Test
    public void masterChangePassword() {
        user.atMasterProfilePage.openProfileSettings();

        var newPassword = DataGenerator.getPassword();
        user.atMasterProfileSettingsPage.changePassword(newPassword);

        user.atMasterProfilePage.logsOut();
        user.atHomePage.loginIfNeeded(master);
        user.atHomePage.verifyUserIsLoggedOut();

        user.atHomePage.login(master.getLogin(), newPassword, false);
        user.atHomePage.verifyUserIsLoggedIn();
    }
}