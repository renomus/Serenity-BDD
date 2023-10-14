package starter.helpers;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;
import net.serenitybdd.screenplay.actions.Click;

public class AnswerSurvey {

    public static Performable selectCheckbox() {
        return Task.where(
                Click.on(SurveyMonkeyHomePage.CHECKBOX_ELEMENT)      
        );
    }
    public static Performable clickNext() {
        return Task.where(
                Click.on(SurveyMonkeyHomePage.NEXT_BUTTON)
        );
    }
}
