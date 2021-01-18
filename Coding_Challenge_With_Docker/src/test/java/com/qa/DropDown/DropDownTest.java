package com.qa.DropDown;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDownTest {

	WebDriver driver;
	Properties prop;

	String propPath = "/src/main/java/com/qa/properties/config.properties";

	DropDownTest(){
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+propPath);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void setUp() {
		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if (prop.getProperty("browser").equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("Dropdown"));
	}
	
	public void dropDown(String value) {
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='dropdown']"));
		dropdown.click();
		Select select = new Select(dropdown);
		select.selectByValue(value);
	}
	
	@Test
	public void option1() {
		dropDown("1");
		Assert.assertTrue(true);	
	}
	
	@Test
	public void option2() {
		dropDown("2");
		Assert.assertTrue(true);	
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
