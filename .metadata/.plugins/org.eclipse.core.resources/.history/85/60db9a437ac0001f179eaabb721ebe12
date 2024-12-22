Feature: Job Search on Labcorp Website

  Scenario: Search job position on Labcorp website
    Given I open a browser and navigate to labCorp Application
    When I find and click on the Careers link
    And I search for a job opening
    And I select and navigate to a job opening from the search results
    Then I verify the following job Details:
    
    | Detail         | Expected Value                                     |
      | Job Title      | "Test Automation Engineer"                     |
      | Job Location   | "Durham, NC, Burlington"                                       |
      | Job ID         | "12345"                                            |     
    When I click on Apply Now
    When I click to Return to Job Search
    Then I should be navigated back to the job search results page.
    
   
 