package UITests.Master.Registration;

import UITests.TestBase;
import entities.Master;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.DataGenerator;

import java.io.IOException;

@ExtendWith(SerenityJUnit5Extension.class)
public class MasterLoginFailureTest extends TestBase {

    private final Master master = DataGenerator.getMaster();

    @BeforeEach
    public void setup() throws InterruptedException, IOException {
        users.add(master);

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
