package steps.adminSteps;

import entities.Category;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.By;
import pages.admin.AddRequestPage;

import java.util.regex.Pattern;

public class AddRequestPageSteps extends ScenarioSteps {

    private static final String categoryUrlByNameXpath = "//tr[./td[text()='%s']]//a";

    private AddRequestPage servicePage;

    public void openPage() {
        servicePage.openPage();
    }

    public void fillAddServiceForm(Category category) {
        servicePage.enterName("Autotest");
        servicePage.selectCategory(category.getName());
        servicePage.enterHeader("Test");
    }

    public void saveService() {
        servicePage.saveService();
    }

    public void setPrices() {
        servicePage.setPrices();
    }

    public void findService(Category category) {
        servicePage.findCategory(category.getName());
    }

    public String getServiceIdByName(String categoryName) {
        var url = getDriver()
                .findElement(By.xpath(String.format(categoryUrlByNameXpath, categoryName)))
                .getDomAttribute("href");

        var matcher = Pattern.compile("(?<=/)\\d{3,}(?=/)").matcher(url);
        if (matcher.find()) {
            return matcher.group(0);
        }

        return null;
    }
}
