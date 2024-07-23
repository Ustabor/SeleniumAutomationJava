package UITests;

import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

//Мастер - Изменение инфо профиля

@RunWith(SerenityRunner.class)
public class MasterEditPersonalDataTest extends TestBase {

    @Before
    public void registerMaster() throws InterruptedException {
        var master = DataGenerator.getMaster();
        watcher.users.add(master);

        user.register(master, true);
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();

        master.setProfileId(user.atMasterProfilePage.getProfileId());
    }
    @Test
    public void editPersonalData() throws TimeoutException {
        var newMasterData = DataGenerator.getMaster();

        user.atMasterProfilePage.openProfileSettings();
        user.atMasterProfilePage.changeMasterInfo(newMasterData);
        user.atMasterProfilePage.saveChanges();

        user.atMasterProfilePage.openProfileSettings();
        user.atMasterProfilePage.verifyMasterInfo(newMasterData);
    }
}
