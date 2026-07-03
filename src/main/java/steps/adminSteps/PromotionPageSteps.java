package steps.adminSteps;

import entities.Category;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractions;
import pages.admin.PromotionPage;

public class PromotionPageSteps extends UIInteractions {

    private PromotionPage promotionPage;

    @Step
    public void approvePromotion(Category category) {
        promotionPage.openPage();
        var promoId = promotionPage.getCategoryPromoId(category.getName());
        category.setPromoId(promoId);
        promotionPage.approvePromotionByCategoryName(category.getName());
    }
}
