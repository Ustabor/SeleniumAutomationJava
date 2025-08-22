package UITests.Master.Registration;

import UITests.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.io.IOException;

//Мастер - Изменение инфо профиля

@RunWith(SerenityRunner.class)
public class MasterEditPersonalDataTest extends TestBase {

    @Before
    public void setup() throws InterruptedException, IOException {
        var master = DataGenerator.getMaster();
        watcher.users.add(master);

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
