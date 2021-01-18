package com.qa.JSAlertError;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JSAlertError {
	
	String propPath = "/src/main/java/com/qa/properties/config.properties";
	
	String errorPath = "//p[contains(text(),'This page has a JavaScript error in the onload eve')]";
	
	WebDriver driver;
	Properties prop;
	
	JSAlertError(){
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
			driver = new ChromeDriver();
		}else if (prop.getProperty("browser").equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("JavaScriptError"));
	}
	
	@Test
	public void jsErrorTest() {
		String errorText = driver.findElement(By.xpath(errorPath)).getText();
		System.out.println("The error message is: "+errorText);
		Assert.assertEquals(errorText, "This page has a JavaScript error in the onload event. This is often a problem to using normal Javascript injection techniques.");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
