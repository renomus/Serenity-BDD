package starter;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import starter.helpers.BasePage;

public class MoneylionSteps extends BasePage{


    @Given("I am a new customer")
    public void iAmANewCustomer() {}

    @And("access to the MoneyLion website")
    public void accessToTheMoneyLionWebsite() {
        navigateTo("https://www.moneylion.com");
    }

    @When("I hover on {string} and click on {string} at the top of the webpage")
    public void iHoverOnAboutUsAndClickOnCareersAtTheTopOfTheWebpage(String aboutUs, String careers) {
        hoverOn(aboutUs, careers);
    }

    @Then("I should redirected to the MoneyLion’s careers page")
    public void iShouldRedirectedToTheMoneyLionSCareersPage() {
        verifyDisplayPage();
    }

    @And("I should be able to see {string} blocks displayed next to {string}")
    public void iShouldBeAbleToSeeLocationBlocks(String locations, String section) {
        verifyLocation(locations, section);
    }
}
