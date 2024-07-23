package pages.masterProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class MasterNotificationsPage extends MasterProfileBasePage{

    @FindBy(xpath = "//a[@href='#main']")
    private WebElementFacade notificationsBtn;
    @FindBy(xpath = "//a[@href='#reviews']")
    private WebElementFacade feedbacksBtn;
    @FindBy(xpath = "//div[contains(@class,'type-master-requestcallback')]")
    private WebElementFacade callbackNotification;

    public void notificationsBtnShpuldBeVisible() {
        notificationsBtn.shouldBeVisible();
    }

    public void feedbacksBtnShouldBeVisible() {
        feedbacksBtn.shouldBeVisible();
    }

    public void verifyNewCallbackNotificationIsVisible() {
        callbackNotification.shouldBeVisible();
    }
}
