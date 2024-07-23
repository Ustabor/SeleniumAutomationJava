package steps.masterProfileSteps;

import entities.Master;
import net.serenitybdd.annotations.Step;
import pages.masterProfile.MasterProfilePage;

public class MasterProfilePageSteps extends MasterProfileSteps {

    private MasterProfilePage masterProfilePage;

    @Step
    public void openProfilePage() {
        masterProfilePage.openMasterProfilePage();
    }

    @Step
    public void masterProfilePagePageShouldBeVisible() {
        masterProfilePage.projectsTabShouldBeVisible();
    }

    @Step
    public void masterFullNameShouldContain(String firstName) {
        masterProfilePage.masterFullNameShouldContain(firstName);
    }

    @Step
    public void aboutMeShouldBe(String aboutMe) {
        masterProfilePage.aboutMeTextShouldBeEqual(aboutMe);
    }

    @Step
    public void logsOut() {
        masterProfilePage.logsOut();
    }

    @Step
    public void verifyMasterCity(String city) {
        masterProfilePage.verifyMasterCity(city);
    }

    @Step
    public void openProfileSettings() {
        masterProfilePage.openProfileSettings();
    }

    public String getProfileId() {
        return masterProfilePage.getProfileId();
    }

    @Step
    public void selectService() throws InterruptedException {
        masterProfilePage.selectService();
    }

    @Step
    public void changeMasterInfo(Master newMasterData) {
        masterProfilePage.enterName(newMasterData.getFirstName());
//        masterProfilePage.enterEmail(newMasterData.getEmail());
//        masterProfilePage.enterPhoneNumber(newMasterData.getPhoneNumber());
        masterProfilePage.changeCity();
        masterProfilePage.changeExperience();
        masterProfilePage.enterAboutMeInfo(newMasterData.getAboutMe());
    }

    @Step
    public void saveChanges() {
        masterProfilePage.clickSaveButton();
    }

    @Step
    public void verifyMasterInfo(Master newMasterData) {
        masterProfilePage.masterNameInputShouldContain(newMasterData.getFirstName());
        masterProfilePage.masterAboutMeInfoShouldContain(newMasterData.getAboutMe());
    }
}
