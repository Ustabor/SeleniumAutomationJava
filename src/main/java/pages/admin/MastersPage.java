package pages.admin;

import entities.Master;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.util.List;

public class MastersPage extends BaseAdminPage {

    @FindBy(xpath = "//a[contains(@href,'master/view')]")
    private List<WebElementFacade> mastersList;

    @FindBy(xpath = "//input[@id='form_balance_sum']")
    private WebElementFacade amountInput;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElementFacade submitBtn;

    @FindBy(xpath = "//div[@title='Actions']")
    private WebElementFacade actionsBtn;
    
    @FindBy(xpath = "//a[@class='edit']")
    private WebElementFacade editMasterBtn;

    @FindBy(xpath = "//input[contains(@id, 'form_data_badges')]")
    private List<WebElementFacade> badges;

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "master");
    }

    public void openMasterProfileByName(String masterLastName) {
        mastersList.stream()
                .filter(master -> master.containsText(masterLastName))
                .findFirst()
                .get()
                .click();
    }

    public void addMoneyToAccount(int amount) {
        amountInput.sendKeys(String.valueOf(amount));
        submitBtn.click();
    }

    public void openEditMasterPage() {
        actionsBtn.click();
        editMasterBtn.click();
    }

    public void addAllBadgesToMaster(Master master) {
        badges.forEach(WebElementFacade::click);
        master.setCountOfBadges(badges.size() - 2);
        submitBtn.click();
    }

    public void deleteMaster(String profileId) {
        getDriver().get(Config.getAdminUrl() + "master/delete/" + profileId);
        searchSubmitShouldBeVisible();
    }
}
