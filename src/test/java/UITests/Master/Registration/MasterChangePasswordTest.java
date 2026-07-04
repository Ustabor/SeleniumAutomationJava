package UITests.Master.Registration;

import UITests.TestBase;
import entities.Master;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.DataGenerator;

import java.io.IOException;

// Мастер - изменить пароль

@ExtendWith(SerenityJUnit5Extension.class)
public class MasterChangePasswordTest extends TestBase {

    private final Master master = DataGenerator.getMaster();

    @BeforeEach
    public void setup() throws InterruptedException, IOException {
        users.add(master);

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