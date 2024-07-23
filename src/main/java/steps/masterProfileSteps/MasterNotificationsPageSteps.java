package steps.masterProfileSteps;

import net.serenitybdd.annotations.Step;
import pages.masterProfile.MasterNotificationsPage;

public class MasterNotificationsPageSteps extends MasterProfileSteps {

    private MasterNotificationsPage masterNotificationsPage;

    @Step
    public void pageShouldBeVisible() {
        masterNotificationsPage.notificationsBtnShpuldBeVisible();
        masterNotificationsPage.feedbacksBtnShouldBeVisible();
    }

    @Step
    public void verifyNewCallbackNotification() {
        masterNotificationsPage.verifyNewCallbackNotificationIsVisible();
    }
}
