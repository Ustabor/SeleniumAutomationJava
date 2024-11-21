package UITests.Master.Registration;

import UITests.TestBase;
import entities.Master;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

@RunWith(SerenityRunner.class)
public class MasterLoginFailureTest extends TestBase {

    private final Master master = DataGenerator.getMaster();

    @Before
    public void setup() throws InterruptedException {
        watcher.users.add(master);

        user.register(master, true);
        user.atMasterProfilePage.logsOut();
    }

    @Test
    public void verifyMasterCantLoginWithWrongPassword() {
        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.login(
                master.getLogin(),
                "thisIsWrongPassword",
                false
        );
        user.atHomePage.loginErrorWithTextShouldBeVisible(getText("LoginFailedError"));
    }
}
