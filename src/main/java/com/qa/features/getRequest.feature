@API
Feature: Test GET request with Rest Assured

  Scenario: Validate GET request response
    Given I perform a GET request to "https://echo.free.beeceptor.com/sample-request?author=beeceptor"
    Then the status code is 200    
    And the ip address is not empty
    And all headers are present
   