package steps.adminSteps;

import entities.Category;
import net.serenitybdd.core.steps.UIInteractions;
import pages.admin.AddCategoryPage;

public class AddCategoryPageSteps extends UIInteractions {

    private AddCategoryPage addCategoryPage;

    public void addTestCategory(Category category) {
        addCategoryPage.openPage();

        addCategoryPage.selectDomain();
        addCategoryPage.enterName(category.getName());
        addCategoryPage.enterUrl(category.getUrl());

        addCategoryPage.openContentTab();
        addCategoryPage.enterTitle(category.getName());

        addCategoryPage.clickSubmit();
    }
}
