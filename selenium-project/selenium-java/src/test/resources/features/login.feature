Feature: SauceDemo Shopping Functionality

  @Selenium
  Scenario: 1. Successful Login
    Given the user navigates to the SauceDemo login page
    When the user enters credentials "standard_user" and "secret_sauce"
    And clicks the Login button
    Then the user is redirected to the inventory page

  @Selenium
  Scenario: 2. Add an item to the cart
    Given the user is logged in and on the inventory page
    When the user adds the product "Sauce Labs Backpack" to the cart
    Then the shopping cart icon shows "1"

  @Playwright
  Scenario: 3. Remove an item from the cart
    Given the user has added "Sauce Labs Backpack" to the shopping cart
    When the user navigates to the cart page
    And removes the product from the cart
    Then the shopping cart is empty

  @Playwright
  Scenario: 4. Complete Checkout
    Given the user has added "Sauce Labs Backpack" to the cart and is on the cart page
    When the user proceeds to checkout
    And fills the form with "John", "Doe" and "12345"
    And finishes the purchase
    Then the user sees the confirmation message "Thank you for your order!"