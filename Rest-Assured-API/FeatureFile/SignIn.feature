
Feature: Verify Login Functionality
Scenario: Login With Valid Credentials
Given I provided Post Request Endpoint
And I created Request Specification object
Then I provided File to request body
Then I Execute Post method
And I capture auth_Token
Then I got Status code as 200

Scenario: verfiy create user Functionality
Given I provided Post request Endpoint
And I created Request Specification Object
Then I provided json file path and store it in a body
Then I created response object and Executed Post Method
And I capture User id
Then I got Status code as 201


 I 