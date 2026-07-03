package steps;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractions;
import pages.FeedbackPage;

public class FeedbackPageSteps extends UIInteractions {

    private FeedbackPage feedbackPage;

    @Step
    public void leftFeedback(int rating, String comment) {
        feedbackPage.setRating(rating);
        feedbackPage.enterFeedbackComment(comment);
        feedbackPage.submitFeedback();
    }
}
