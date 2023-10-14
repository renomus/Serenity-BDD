package starter.helpers;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {
    public static Performable surveyPage() {
        return Task.where(
                Open.browserOn().the(SurveyMonkeyHomePage.class));
    }
}