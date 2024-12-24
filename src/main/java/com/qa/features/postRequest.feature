 @HTTPPostAPI
Feature: Test POST request with REST Assured

  @postRequest
  Scenario: Validate POST request response
    Given I send a POST request to "http://echo.free.beeceptor.com" and set parameter with payload from file "src/test/resources/payload.json"
    Then the response should include customer information, payment details, and product information
  