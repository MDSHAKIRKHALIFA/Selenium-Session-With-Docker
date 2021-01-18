package com.qa.Dynamic_Loading;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicLoading {
	
	String propPath = "/src/main/java/com/qa/properties/config.properties";
	
	String startPath = "//button[contains(text(),'Start')]";
	String finishPath = "//h4[contains(text(),'Hello World!')]";

	WebDriver driver;
	Properties prop;
	
	DynamicLoading(){
		prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+propPath);
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
			driver =  new ChromeDriver();
			
		}else if (prop.getProperty("browser").equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("DynamicLoading"));
	}
	
	@Test
	public void dynamicLoadingTest() {
		try {
		WebDriverWait uglyWait = new WebDriverWait(driver, 30);
		WebElement startElement = driver.findElement(By.xpath(startPath));
		startElement.click();
		uglyWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(finishPath)));
		String textPath = driver.findElement(By.xpath(finishPath)).getText();
		Assert.assertEquals(textPath, "Hello World!");
		System.out.println("The text is: "+textPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(enabled = false)
	public void verifyDynamicallyLoadedElement() {
		    try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			driver.findElement(By.xpath(startPath)).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(finishPath)));
			WebElement dynamicElement = driver.findElement(By.xpath(finishPath));
			String dynamicElementText = dynamicElement.getText();
			Assert.assertEquals(dynamicElementText, "Hello World!");
			System.out.println("###DYNAMIC ELEMENT TEXT IS: " + dynamicElementText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
