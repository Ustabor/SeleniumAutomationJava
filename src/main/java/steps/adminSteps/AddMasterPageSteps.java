package steps.adminSteps;

import entities.Master;
import net.serenitybdd.core.steps.UIInteractions;
import pages.admin.AddMasterPage;

public class AddMasterPageSteps extends UIInteractions {

    private AddMasterPage addMasterPage;

    public void addMaster(Master master) {
        addMasterPage.openPage();
        addMasterPage.createMaster(master);
        addMasterPage.openMasterPage(master);
        addMasterPage.setCategoryToMaster(master);
    }
}
