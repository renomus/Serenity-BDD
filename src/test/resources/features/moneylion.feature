Feature: Moneylion

  @AboutPage
  Scenario: Access to MoneyLion about page
    Given I am a new customer
    And access to the MoneyLion website
    When I hover on "About us" and click on "Careers" at the top of the webpage
    Then I should redirected to the MoneyLion’s careers page
    And I should be able to see "New York City, Sioux Falls, Kuala Lumpur and Around the globe" blocks displayed next to "Where we work"