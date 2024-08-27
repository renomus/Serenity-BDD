package starter.helpers;

import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BasePage {

    protected WebDriver driver = new ChromeDriver();
    protected Actions actions = new Actions(driver);

    public void navigateTo(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("MoneyLion"));
    }

    public void hoverOn(String aboutUS, String careers) {
        WebElement aboutUsMenu = driver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]", aboutUS)));
        actions.moveToElement(aboutUsMenu).perform();
        WebElement careersLink = driver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]",careers)));
        actions.moveToElement(careersLink).perform();
        careersLink.click();
    }

    public void verifyDisplayPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("careers"));
    }

    public void verifyLocation( String locations, String section) {
        String[] locationArray = locations.split(",\\s*|\\s+and\\s*|\\.");

        List<String> expectedLocations = Arrays.stream(locationArray)
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(location -> !location.isEmpty())
                .collect(Collectors.toList());

        actions.moveToElement(driver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]",section)))).perform();
        List<WebElement> figcaptions = driver.findElements(By.xpath("//figcaption"));

        List<String> actualLocations = figcaptions.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        for (String actualLocation : actualLocations) {
            if (!expectedLocations.contains(actualLocation)){
                Assert.fail("Unexpected location found: " + actualLocation);
            }
        }

       Assert.assertTrue("Expected locations not found in actual locations",
                actualLocations.containsAll(expectedLocations));


    }
}
