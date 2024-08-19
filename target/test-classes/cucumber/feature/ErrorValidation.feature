@tag
Feature: Error Validation on login
  I want to use this template for my feature file
  
  @ErrorValidation
  Scenario Outline: Login and place order
  	Given I landed on Ecommerce Page
  	When Logged in with username <username> and password <password>
  	Then Toast message "Incorrect email or password." is shown

    Examples: 
      | username  		| password	|
      | as@example.com| Abcd@1234 	|
  
