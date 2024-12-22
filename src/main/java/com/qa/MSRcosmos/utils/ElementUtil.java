package com.qa.MSRcosmos.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;
	private Actions act;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);

	}

	public void doClick(By locator) {
		getElement(locator).click();
	}
	public String getText(By locator) {
		return getElement(locator).getText();
	}

	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void doClick(By locator, int timeOut) {
		waitForElementVisible(locator, timeOut).click();
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);

		return element;
	}

	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	public void doSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public boolean isElementDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not displayed : " + locator);
			return false;
		}
	}

	public String doGetElementText(By locator) {
		String eleText = getElement(locator).getText();
		if (eleText != null) {
			return eleText;
		} else {
			System.out.println("Element text is null:" + eleText);
			return null;
		}
	}

	public List<String> getDropDownOptionsTextList(By locator) {
		List<WebElement> optionsList = getSelect(locator).getOptions();
		System.out.println("total number of options: " + optionsList.size());

		List<String> optionsTextList = new ArrayList<String>();
		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsTextList.add(text);
		}

		return optionsTextList;
	}

	private Select getSelect(By locator) {
		return new Select(getElement(locator));
	}

	public void selectDropDownValueUsingSelect(By locator, String value) {
		List<WebElement> optionsList = getSelect(locator).getOptions();
		selectDropDown(optionsList, value);
	}

	private void selectDropDown(List<WebElement> optionsList, String value) {
		System.out.println("total number of options: " + optionsList.size());
		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public String getPageTitleIs(String expectedTitle, int timeOut) {
		if (waitForTitleIs(expectedTitle, timeOut)) {
			return driver.getTitle();
		} else {
			return "-1";
		}
	}

	public String getPageTitleContains(String expectedTitle, int timeOut) {
		if (waitForTitleContains(expectedTitle, timeOut)) {
			return driver.getTitle();
		} else {
			return "-1";
		}
	}

	public boolean waitForTitleIs(String expectedTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			return wait.until(ExpectedConditions.titleIs(expectedTitle));

		} catch (TimeoutException e) {
			System.out.println("title is not matched");
			return false;
		}
	}

	public boolean waitForTitleContains(String fractionTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			return wait.until(ExpectedConditions.titleContains(fractionTitle));
		} catch (TimeoutException e) {
			System.out.println("title is not matched");
			return false;
		}
	}

	public void doActionsSendKeys(By locator, String value) {
		act.sendKeys(getElement(locator), value).perform();
	}

	public Alert waitForAlertAndSwitch(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public Alert waitForAlertUsingFluentWaitAndSwitch(int timeOut) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoAlertPresentException.class).withMessage("====Js alert is not present===");
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public String getAlertText(int timeOut) {
		return waitForAlertAndSwitch(timeOut).getText();
	}

	public void scrollToElementAndClick(WebDriver driver, By locator) {
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

}
