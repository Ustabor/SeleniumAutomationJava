package steps;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractions;
import pages.BasePage;

import java.util.List;

public class CommonSteps extends UIInteractions {

    private BasePage basePage;

    @Step
    public void openSiteWithName(String siteName) {
        basePage.openSiteWithName(siteName);
    }

    @Step
    public void verifySiteAtHeader(String site) {
        basePage.verifyHeaderSite(site);
    }

    @Step
    public List<String> getSitesNames() {
        return basePage.getSitesNames();
    }
}
