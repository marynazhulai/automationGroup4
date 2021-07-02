Feature: Sold Out Product

  Scenario: Verify Sold Out Product
    Given User goes to homepage
    When User enters email "m.z1@gmail.com" and password "1234567"
    When User clicks on login button
    When User finds sold out product
    When User tries to add to basket sold out product
    Then Error message is displayed