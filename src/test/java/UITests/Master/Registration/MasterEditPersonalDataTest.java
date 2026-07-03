package UITests.Master.Registration;

import UITests.TestBase;
import io.cucumber.java.Before;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.DataGenerator;

import java.io.IOException;

//Мастер - Изменение инфо профиля

@ExtendWith(SerenityJUnit5Extension.class)
public class MasterEditPersonalDataTest extends TestBase {

    @Before
    public void setup() throws InterruptedException, IOException {
        var master = DataGenerator.getMaster();
        users.add(master);

        user.register(master, true);
    }
    @Test
    public void editPersonalData() {
        var newMasterData = DataGenerator.getMaster();

        user.atMasterProfilePage.openProfileSettings();
        user.atMasterProfilePage.changeMasterInfo(newMasterData);
        user.atMasterProfilePage.saveChanges();

        user.atMasterProfilePage.openProfileSettings();
        user.atMasterProfilePage.verifyMasterInfo(newMasterData);
    }
}
