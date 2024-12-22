package com.qa.steps;

import java.util.Map;
import java.util.Properties;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;


import org.openqa.selenium.WebDriver;


import com.qa.MSRcosmos.page.JobSearchPage;
import com.qa.MSRcosmos.utils.BasePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class JobSearchStep {
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	JobSearchPage jobSearchPage;
	@Given("I open a browser and navigate to labCorp Application")
	public void i_open_a_browser_and_navigate_to_lab_corp_application() {
		basePage = new BasePage();
		prop = basePage.init_Properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_Driver(browserName);
		driver.get(prop.getProperty("url"));
		jobSearchPage = new JobSearchPage(driver);

	}

	@When("I find and click on the Careers link")
	public void i_find_and_click_on_the_careers_link() {
		jobSearchPage.maximizeWindow();
		jobSearchPage.clickOnCareers();
	}

	@When("I search for a job opening")
	public void i_search_for_a_job_opening() {
		jobSearchPage.SearchJob();
		jobSearchPage.checkSearchResult();
	}

	@When("I select and navigate to a job opening from the search results")
	public void i_select_and_navigate_to_a_job_opening_from_the_search_results() {
		jobSearchPage.clickOnSearchResult();
	}
//	@Then("I verify the jobTitle as {string}")
//	public void i_verify_the_job_title_as(String Title) {
//		String actualJobTitle = jobSearchPage.getActualJobTitle();
//		Assert.assertEquals("Job Title does not match", Title, actualJobTitle);
//	}
//
//	@Then("I verify the jobLocation as {string}")
//	public void i_verify_the_job_location_as(String string) {
//		 System.out.println("code implementation pendin");
//	}
//
//	@Then("I verify the jobId as {string}")
//	public void i_verify_the_job_id_as(String string) {
//	    System.out.println("code implementation pendin");
//	}

	@Then("I verify the following job Details:")
	public void i_verify_the_following_job_details(io.cucumber.datatable.DataTable dataTable) {
		
	        // Convert the DataTable to a Map for easy access
	        Map<String, String> jobDetails = dataTable.asMap(String.class, String.class);

	        // Extract details from the map
	        String expectedJobTitle = jobDetails.get("Job Title");
	        String expectedJobLocation = jobDetails.get("Job Location");
	        String expectedJobID = jobDetails.get("Job ID");

	        
	        String actualJobTitle = jobSearchPage.getActualJobTitle(); 

//	        String actualJobLocation = jobSearchPage.getActualJobLocation();
//	        String actualJobID = jobSearchPage.getActualJobID(); 
System.out.println(actualJobTitle);
System.out.println(expectedJobTitle.replace("\"",""));
	        // Assertions
	        Assert.assertEquals("Job Title does not match", expectedJobTitle.replace("\"",""), actualJobTitle.trim());
//	        Assert.assertEquals("Job Location does not match", expectedJobLocation, actualJobLocation);
//	        Assert.assertEquals("Job ID does not match", expectedJobID, actualJobID);
	    

	    

	   
	    

	    
	}


	@When("I click on Apply Now")
	public void i_click_on_apply_now() {
	    jobSearchPage.clickOnApplyNow();
	}

	@When("I click to Return to Job Search")
	public void i_click_to_return_to_job_search() {
	    jobSearchPage.clickOnReturnToCareerHome();
	}

	@Then("I should be navigated back to the job search results page.")
	public void i_should_be_navigated_back_to_the_job_search_results_page() {
	    driver.quit();
	}
	
	
	
	

}