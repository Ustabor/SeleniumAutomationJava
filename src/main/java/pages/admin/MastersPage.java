package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MastersPage extends BaseAdminPage {

    @FindBy(xpath = "//a[@id='btn-master-balance']")
    private WebElementFacade addMoneyBnt;
    @FindBy(xpath = "//input[@id='form_data_sum']")
    private WebElementFacade amountInput;
    @FindBy(xpath = "//div[./button[@data-action='close']]//button[@type='submit']")
    private WebElementFacade submitBtn;
    @FindBy(xpath = "//div[@class='form-buttons']//button[@type='submit']")
    private WebElementFacade saveBtn;
    @FindBy(xpath = "//table[@class='table-grid']/tbody//tr")
    private List<WebElementFacade> transactionsList;
    @FindBy(xpath = "//span[@class='price-value']")
    private WebElementFacade masterBalance;

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "master");
    }

    public void openMasterPageByDirectUrl(String masterId) {
        getDriver().get(Config.getAdminUrl() + String.format("master/%s", masterId));
    }

    public void addMoneyToAccount(int amount) {
        scrollPageToBottom();
        scrollIntoView(addMoneyBnt);
        addMoneyBnt.click();
        amountInput.sendKeys(String.valueOf(amount));
        submitBtn.click();
        waitForLoaderDisappears();
    }

    public void verifyOnlyOneTransactionExist(String amount) {
        var transactions = transactionsList
                .stream()
                .map(WebElementFacade::getText)
                .filter(t -> t.contains(amount))
                .collect(Collectors.toList());

        assertThat(transactions.size()).isEqualTo(1);
    }

    public void verifyMasterBalance(int balance) {
        assertThat(masterBalance.getText()).contains(String.valueOf(balance));
    }
}
