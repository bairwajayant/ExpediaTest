package com.expedia;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class UIUtility {
	public static WebDriver createDriver(String browserName, String driverPath) {
		WebDriver driver = null;

		switch (browserName.toUpperCase()) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", new File(driverPath).getAbsolutePath());
			driver = new ChromeDriver();
			break;
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", new File(driverPath).getAbsolutePath());
			driver = new FirefoxDriver();
			break;
		case "IE":
		case "INTERNETEXPLORER":
			System.setProperty("webdriver.ie.driver", new File(driverPath).getAbsolutePath());
			driver = new InternetExplorerDriver();
			break;
		default:
			break;
		}

		return driver;
	}

	public static boolean isElementExist(WebDriver driver, By by) {
		List<WebElement> elements = driver.findElements(by);

		if (elements.size() == 0) {
			return false;
		} else {
			return true;
		}
		// return (driver.findElements(by).size() != 0);
	}
}
