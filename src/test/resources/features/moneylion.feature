Feature: Moneylion

  Background:
    Given I am a new customer
    And access to the MoneyLion website

  @AboutPage
  Scenario: Access to MoneyLion about page
    When I hover on "About us" and click on "Careers" at the top of the webpage
    Then I should redirected to the MoneyLion’s careers page
    And I should be able to see "New York City, Sioux Falls, Kuala Lumpur and Around the globe" blocks displayed next to "Where we work"

    @FAQS
    Scenario Outline: Able to see all the Frequent Asked Question for Cash Advances
      When I hover on "Products" and click on "Instacash" at the top of the webpage
      And I scroll to view the "Frequently Asked Questions" section
      Then I should be able to see <question> is displayed

      Examples:
      | How do I get instacash?|
      | Will Instacash affect my credit? |