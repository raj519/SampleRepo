@HTTPGetAPI
Feature: Test GET request with Rest Assured

  Scenario: Validate GET request response
    Given I perform a GET request to "https://echo.free.beeceptor.com" and set parameter
    Then the response should include fields such as path, ip, headers
