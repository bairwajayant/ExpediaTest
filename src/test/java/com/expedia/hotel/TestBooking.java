package com.expedia.hotel;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.expedia.Config;
import com.expedia.UIUtility;

public class TestBooking {
	private WebDriver driver = null;
	private static Config appConfig;

	static {
		try {
			appConfig = new Config("src//test//resources//Config//AppConfig.prop");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void suiteSetup() throws IOException {
		String browserName = appConfig.getPropertyValue("BrowserName");

		switch (browserName.toUpperCase()) {
		case "CHROME":
			driver = UIUtility.createDriver(browserName, appConfig.getPropertyValue("ChromeDriverPath"));
			break;
		case "FIREFOX":
			driver = UIUtility.createDriver(browserName, appConfig.getPropertyValue("GeckoDriverPath"));
			break;
		case "IE":
		case "INTERNETEXPLORER":
			driver = UIUtility.createDriver(browserName, appConfig.getPropertyValue("IEDriverPath"));
			break;
		default:
			break;
		}
	}

	@BeforeMethod
	public void testSetup() {
		driver.get(appConfig.getPropertyValue("AppURL"));
		driver.manage().window().maximize();

		int waitTime = Integer.parseInt(appConfig.getPropertyValue("AVGWAITTTIME"));
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(waitTime, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(waitTime, TimeUnit.SECONDS);
	}

	@Test(priority = 1, enabled = true)
	public void verifyHotelSearch() {
		Assert.assertEquals("Expedia Travel: Search Hotels, Cheap Flights, Car Rentals & Vacations", driver.getTitle());
		Assert.assertTrue(UIUtility.isElementExist(driver, By.id("tab-hotel-tab-hp")), "Hotel tab does not exist");
		
		driver.findElement(By.id("tab-hotel-tab-hp")).click();
		driver.findElement(By.xpath("//*[@id='hotel-destination-hp-hotel']")).sendKeys("cancun");
		
		List<WebElement> destinations = driver.findElements(By.xpath("//ul[@id='typeaheadDataPlain']//a"));
		
		Assert.assertTrue(destinations.size() > 0, "Destinations are not existing");
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}