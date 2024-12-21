Feature: Job Search and Verification on Labcorp Website

  Scenario: Search and verify a job position on Labcorp website
    Given I open a browser and navigate to "https://www.labcorp.com"
    When I find and click on the "Careers" link
    And I search for a position with the keyword "QA Test Automation Developer"
    And I select and navigate to a job position from the search results
    Then I verify the following job details:
      | Detail         | Expected Value                                     |
      | Job Title      | "QA Test Automation Developer"                     |
      | Job Location   | "Durham, NC"                                       |
      | Job ID         | "12345"                                            |
      | Requirement 1  | "The right candidate for this role will participate in the test automation technology development and best practice models." |
      | Requirement 2  | "Prepare test plans, budgets, and schedules."      |
      | Requirement 3  | "5+ years of experience in QA automation development and scripting." |
      | Tool Familiarity | "Selenium"                                       |
    When I click on "Apply Now"
    Then I confirm the following details on the proceeding page:
      | Detail         | Expected Value                                     |
      | Job Title      | "QA Test Automation Developer"                     |
      | Job Location   | "Durham, NC"                                       |
      | Job ID         | "12345"                                            |
      | Additional Text | "The right candidate for this role will participate in the test automation technology development and best practice models." |
    When I click "Return to Job Search"
    Then I should be navigated back to the job search results page.