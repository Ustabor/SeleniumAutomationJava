package steps.masterProfileSteps;

import net.serenitybdd.annotations.Step;
import pages.masterProfile.MasterPromotionPage;

public class MasterPromotionPageSteps extends MasterProfileSteps {

    private MasterPromotionPage masterPromotionPage;

    @Step
    public void pageShouldBeVisible() {
        masterPromotionPage.categorySelectorShouldBeVisible();
        masterPromotionPage.sendToModerationBtnShouldBeVisible();
    }

    @Step
    public void promoteCategory(String categoryName, MasterPromotionPage.PromotionType promotionType) {
        masterPromotionPage.selectCategoryToPromote(categoryName);
        masterPromotionPage.selectPromotionType(promotionType);
        masterPromotionPage.sendToModeration();
    }
}
