package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import starter.helpers.NavigateTo;
import starter.helpers.AnswerSurvey;
import starter.helpers.VerifyText;

public class AnswerSurveyStepDefinitions {
    @Given("{actor} wants to answer a survey")
    public void actor_navigate(Actor actor) {
        actor.wasAbleTo(NavigateTo.surveyPage());
    }
    @When("{actor} selects the “Good” radio button option")
    public void he_selects(Actor actor) {
       actor.attemptsTo(AnswerSurvey.selectCheckbox());
    }
    @And("{actor} clicks the “Next” Button")
    public void he_clicks_next(Actor actor) {
       actor.attemptsTo(AnswerSurvey.clickNext());
    }
    @Then("{actor} sees text “Have a nice day.” exists")
    public void he_sees_text(Actor actor) {
        actor.attemptsTo(Ensure.that(Text.of(VerifyText.VERIFY_TEXT)).contains("Have a nice day."));
    }

}