package starter.helpers;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

@DefaultUrl("https://www.surveymonkey.com/r/9MVSPYS")
public class SurveyMonkeyHomePage extends PageObject {

    static Target CHECKBOX_ELEMENT = Target.the("checkbox").locatedBy("div.radio-button-container input:nth-child(1)");

    static Target NEXT_BUTTON = Target.the("next button").locatedBy("div[class='survey-submit-actions center-text clearfix'] button");
}
