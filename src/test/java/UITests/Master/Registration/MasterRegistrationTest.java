package UITests.Master.Registration;

import UITests.TestBase;
import entities.Master;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.io.IOException;

// Мастер - Создать/удалить профиль

@WithTag("smoke")
@RunWith(SerenityRunner.class)
public class MasterRegistrationTest extends TestBase {

    private final Master master = DataGenerator.getMaster();

    @Test
    public void verifyMasterCanCreateProfile() throws InterruptedException, IOException {
        watcher.users.add(master);

        user.register(master, true);
        user.atMasterProfilePage.masterFullNameShouldContain(master.getLastName());
        user.atMasterProfilePage.aboutMeShouldBe(master.getAboutMe());
        user.atMasterProfilePage.verifyMasterCity(master.getCity());
    }

    @After
    public void tearDown() {
        user.atMasterProfilePage.openProfileSettings();
        user.atMasterProfileSettingsPage.deleteProfile();
        master.setProfileId(null);
    }
}
