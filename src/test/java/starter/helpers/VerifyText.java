package starter.helpers;

import net.serenitybdd.screenplay.targets.Target;

public class VerifyText {
    public static Target VERIFY_TEXT = Target.the("heading").locatedBy("div[role=heading] div");

}
