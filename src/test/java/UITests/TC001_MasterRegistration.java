package UITests;

import entities.Master;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

// Мастер - Создать/удалить профиль

@WithTag("smoke")
@RunWith(SerenityRunner.class)
public class TC001_MasterRegistration extends TestBase {

    private Master master;
    @Test
    public void verifyMasterCanCreateProfile() throws InterruptedException {
        master = DataGenerator.getMaster();
        watcher.users.add(master);

        user.register(master, true);
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();

        master.setProfileId(user.atMasterProfilePage.getProfileId());

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
