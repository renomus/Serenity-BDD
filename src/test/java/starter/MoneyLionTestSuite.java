package starter;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/moneylion.feature"
        },
        plugin = {"pretty", "json:target/cucumber.json"},
        glue = {"starter", "starter.hooks"}
)

public class MoneyLionTestSuite {
}
