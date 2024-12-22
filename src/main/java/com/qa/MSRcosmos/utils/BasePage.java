package com.qa.MSRcosmos.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	WebDriver driver;
	Properties prop;

	public WebDriver init_Driver(String browserName) {
		if (browserName.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		return driver;
	}

	public Properties init_Properties() {

		Properties prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("src\\test\\resources\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("Some issue with config properties");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

}
