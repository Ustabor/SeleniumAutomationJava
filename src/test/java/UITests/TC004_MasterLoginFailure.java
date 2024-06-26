package UITests;

import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

@RunWith(SerenityRunner.class)
public class TC004_MasterLoginFailure extends TestBase {

    @Test
    public void verifyMasterCantLoginWithWrongPassword() throws InterruptedException {
        var master = DataGenerator.getMaster();
        watcher.users.add(master);

        user.register(master, true);
        user.atMasterProfilePage.logsOut();

        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.login(
                master.getLogin(),
                "thisIsWrongPassword",
                false);
        user.atHomePage.loginErrorWithTextShouldBeVisible(getText("LoginFailedError"));
    }
}
