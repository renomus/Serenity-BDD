package starter;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MoneylionSteps {

    // Declare WebDriver instance
    private WebDriver driver;
    private Actions actions;

    @Given("I am a new customer")
    public void iAmANewCustomer() {
        // Initialize WebDriver, e.g., with ChromeDriver
        driver = new ChromeDriver();

        // Optionally, maximize the browser window
        driver.manage().window().maximize();
    }

    @And("access to the MoneyLion website")
    public void accessToTheMoneyLionWebsite() {
        // Access the MoneyLion website
        driver.get("https://www.moneylion.com");

        // Wait until the page title contains "MoneyLion"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("MoneyLion"));
    }

    @When("I hover on “About Us” and click on “Careers” at the top of the webpage")
    public void iHoverOnAboutUsAndClickOnCareersAtTheTopOfTheWebpage() {
        // Locate the "About Us" element
        WebElement aboutUsMenu = driver.findElement(By.xpath("//*[contains (text(), 'About us')]"));

        // Perform hover action
        actions = new Actions(driver);
        actions.moveToElement(aboutUsMenu).perform();

        // Locate and click on the "Careers" link
        WebElement careersLink = driver.findElement(By.xpath("//*[contains (text(), 'Careers')]"));
        actions.moveToElement(careersLink).perform();
        careersLink.click();
    }

    @Then("I should redirected to the MoneyLion’s careers page")
    public void iShouldRedirectedToTheMoneyLionSCareersPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("careers"));
    }
    @And("I should be able to see “New York City, Sioux Falls, Kuala Lumpur and Around the globe.” blocks displayed next to “WHERE WE WORK”")
    public void iShouldBeAbleToSeeLocationBlocks() {

        actions.moveToElement(driver.findElement(By.xpath("//*[contains(text(), 'Where we work')]"))).perform();
        List<WebElement> locationBlocks = driver.findElements(By.xpath(
                "//figcaption[contains(text(), 'New York City')]" +
                        "| //figcaption[contains(text(), 'Sioux Falls')]" +
                        "| //figcaption[contains(text(), 'Kuala Lumpur')]" +
                        "| //figcaption[contains(text(), 'Around the globe')]"
        ));

        // Assert that the expected blocks are displayed
        if (locationBlocks.size() != 4) {
            throw new AssertionError("Not all location blocks are displayed.");
        }
    }
}
