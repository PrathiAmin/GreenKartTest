@GreenKartHome
Feature: GreenKart HomePage

  @GreenKartHomeLoginAndVegetableCount
  Scenario: GreenKart HomePage Vegetables Verification
    Given User launches the "GreenKart" application
  #  When The user is on the home page
    Then User verifies the title
    And User verifies the number of Vegetables
    And User closes the browser

  @GreenKartHomeSearchVegetable
  Scenario Outline: GreenKart HomePage Search button verification
    Given User launches the "GreenKart" application
    When The user is on the home page
    Then User enters the "<name>" of the vegetable
    And Checks if the vegetable "<name>" with picture is displayed
    And User closes the browser
      Examples:
        |name|
        |cucumber|
        |tomato  |
        |Fish    |



