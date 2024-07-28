package UITests;

import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;

// Мастер - изменить пароль

@RunWith(SerenityRunner.class)
public class MasterChangePasswordTest extends TestBase {
    @Test
    public void masterChangePassword() throws InterruptedException, TimeoutException {
        var master = DataGenerator.getMaster();
        watcher.users.add(master);

        user.register(master, true);
        user.atMasterProfilePage.openProfileSettings();

        var newPassword = DataGenerator.getPassword();
        user.atMasterProfileSettingsPage.changePassword(newPassword);

        user.atMasterProfilePage.logsOut();
        assertThat(user.atHomePage.login(master.getLogin(), master.getPassword(), true))
                .isFalse();

        user.atHomePage.login(master.getLogin(), newPassword, false);
        user.atHomePage.verifyUserIsLoggedIn();
    }
}