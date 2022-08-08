package com.techfios;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.techfios.utilities.ExcelUtilities;

public class AccountTest {
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://techfios.com/billing/?ng=admin/");
	}

	@DataProvider
	public Object[][] getLoginData() throws IOException {
		Object[][] data = ExcelUtilities.getData("Login");
		return data;

	}

	@Test(dataProvider = "getLoginData")
	public void newAccountTest(String Username, String Password) {
		driver.findElement(By.id("username")).sendKeys(Username);
		driver.findElement(By.id("password")).sendKeys(Password);
		driver.findElement(By.name("login")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='col-lg-12']/h2")).getText(), "Dashboard"," Login no successfull");
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
