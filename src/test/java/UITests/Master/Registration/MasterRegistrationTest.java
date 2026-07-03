package UITests.Master.Registration;

import UITests.TestBase;
import entities.Master;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.DataGenerator;

import java.io.IOException;

// Мастер - Создать/удалить профиль

@WithTag("smoke")
@ExtendWith(SerenityJUnit5Extension.class)
public class MasterRegistrationTest extends TestBase {

    private final Master master = DataGenerator.getMaster();

    @Test
    public void verifyMasterCanCreateProfile() throws InterruptedException, IOException {
        users.add(master);

        user.register(master, true);
        user.atMasterProfilePage.masterFullNameShouldContain(master.getLastName());
        user.atMasterProfilePage.aboutMeShouldBe(master.getAboutMe());
        user.atMasterProfilePage.verifyMasterCity(master.getCity());
    }

    @AfterEach
    public void tearDown() {
        user.atMasterProfilePage.openProfileSettings();
        user.atMasterProfileSettingsPage.deleteProfile();
        master.setProfileId(null);
    }
}
