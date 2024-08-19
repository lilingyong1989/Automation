@tag
Feature: Place orders from Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page
  

  @Regression
  Scenario Outline: Login and place order
    Given Logged in with username <username> and password <password>
    When I add the products <products> to cart
    And Checkout the products
    And I fill in the country <country> and place the order
    Then I verify the orders in Order History page

    Examples: 
      | username  		| password	| products 												|country|
      | as@example.com| Abcd@123 	| ADIDAS ORIGINAL,IPHONE 13 PRO		|India	|
      | pm@example.com| Qwer@123 	| ZARA COAT 3											|China	|
