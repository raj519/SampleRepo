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
	By searchJobDropDown = By.xpath("//div[@class='phs-keyword-suggestions']/div/ul/li[1]/a");
	By jobTitle = By.xpath("//h1[@class='job-title']");
	By seeAllLocation = By.xpath("//button[contains(text(),'See all')]");

	By locationCheckOne = By.xpath("//span[contains(text(),'Durham, North Carolina, United States of America')]");
	By locationCheckTwo = By.xpath("//span[contains(text(),'Fairfax, Virginia, United States of America')]");
	By locationCheckThree = By.xpath("//span[contains(text(),'Burlington, North Carolina, United States of America')]");
	By jobId = By.xpath("//span[contains(@class, 'jobId')]/text()[normalize-space()]");
	By saveJob = By.xpath("//*[contains(text(),'Save Job') and @data-ph-at-id]");
	By lifeAtLabCorp = By.xpath("//*[contains(text(),'Life at Labcorp')]/parent::span/..");
	By applyNow = By.xpath("(//*[contains(text(),'Apply Now')]/parent::a)[1]");

	//

	public JobSearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public void clickOnCareers() {
		eleUtil.doClick(careers, 10);
	}

	public String pageTitle() {
		eleUtil.getPageTitleIs("Lab Diagnostics &amp; Drug Development, Global Life Sciences Leader | Labcorp", 10);
		return driver.getTitle();
	}

	public void clickOnSearchJob() {
		eleUtil.doSendKeys(searchJob, "Test Automation Engineer");
	}

	public void checkSearchResult() {
		eleUtil.isElementDisplayed(searchJobDropDown);
	}

	public void clickOnSearchResult() {
		eleUtil.selectDropDownValueUsingSelect(searchJobDropDown, "Test Automation Engineer");
		eleUtil.doClick(searchJobDropDown);
	}

}
