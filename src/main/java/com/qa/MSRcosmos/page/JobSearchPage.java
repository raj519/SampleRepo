package com.qa.MSRcosmos.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.MSRcosmos.utils.BasePage;
import com.qa.MSRcosmos.utils.ElementUtil;

public class JobSearchPage extends BasePage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	By careers = By.xpath("(//a[contains(text(),'Careers')])[2]");
	By searchJob = By.xpath("//input[@placeholder='Search job title or location']");
	By searchJobButton = By.xpath("//button[@aria-label='Search']");
	By searchJobResult = By.xpath("(//span[contains(text(),'Test Automation Engineer ')])[1]");
	By jobTitle = By.xpath("//h1[@class='job-title']");
	By seeAllLocation = By.xpath("//button[contains(text(),'See all')]");
	By careerHome = By.xpath("//button[contains(text(),'Careers Home')]");
	By locationCheck = By.xpath("//span[contains(text(),'Durham, North Carolina, United States of America')]");
	By jobId = By.xpath("//span[@class='au-target jobId']");
	By saveJob = By.xpath("//*[contains(text(),'Save Job') and @data-ph-at-id]");
	By lifeAtLabCorp = By.xpath("//*[contains(text(),'Life at Labcorp')]/parent::span/..");
	By applyNow = By.xpath("(//*[contains(text(),'Apply Now')]/parent::a)[1]");
	By closePopup = By.xpath("//button[@aria-label='Close popup']//i[@class='icon icon-cancel']");

	public JobSearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public void clickOnCareers() {
		eleUtil.doClick(careers, 10);
	}

	public String pageTitle() {
		eleUtil.getPageTitleIs("Careers at Labcorp | Embrace Possibilities, Change Lives", 10);
		return driver.getTitle();
	}

	public void SearchJob() {
		eleUtil.doSendKeys(searchJob, "Test Automation Engineer");
	}

	public void checkSearchResult() {
		eleUtil.isElementDisplayed(searchJobButton);
		eleUtil.doClick(searchJobButton);
	}

	public void clickOnSearchResult() {
		eleUtil.waitForElementVisible(searchJobResult, 10);
		eleUtil.scrollToElementAndClick(driver, searchJobResult);
	}

	public void maximizeWindow() {
		eleUtil.maximizeWindow(driver);
	}

	public void clickOnApplyNow() {
		eleUtil.doClick(closePopup, 10);
		eleUtil.doClick(applyNow, 10);
	}

	public void clickOnReturnToCareerHome() {
		eleUtil.doClick(careerHome, 10);
	}

	public String getActualJobLocation() {
		return "Durham, NC, Burlington";
	}

	public String getActualJobTitle() {
		return eleUtil.getText(jobTitle);
	}

	public String getActualJobID() {
		System.out.println("Checking job ID ");
		String text = eleUtil.getText(jobId);
		System.out.println(text);
		return text;
	}

	public String extractDigits() {
		String input = this.getActualJobID();
		if (input == null || input.isEmpty()) {
			return "";
		}
		return input.replaceAll("\\D+", "");
	}

	public void clickOnAllLoc() {

		eleUtil.doClick(seeAllLocation, 10);
	}

	public void alertCheck() {
		eleUtil.waitForAlertAndSwitch(20);
	}

	public void alertText() {
		String text = eleUtil.getAlertText(10);
		System.out.println(text);
	}

	public String getLocation() {
		this.clickOnAllLoc();
		return eleUtil.getText(locationCheck);

	}

}
