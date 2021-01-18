package com.qa.JSAlerts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JSAlerts {
	

	String propPath = "/src/main/java/com/qa/properties/config.properties";
	
	String JSAlert = "//button[contains(text(),'Click for JS Alert')]";
	String JSConfirm = "//button[contains(text(),'Click for JS Confirm')]";
	String jsPromptPath = "//button[contains(text(),'Click for JS Prompt')]";
	String resultPath = "//p[@id='result']";
	
	WebDriver driver;
	Properties prop;
	
	JSAlerts(){
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
		driver.get(prop.getProperty("JavaScriptAlerts"));
	}
	
	@Test
	public void jsAlertTest() {
		driver.findElement(By.xpath(JSAlert)).click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("The text of the alert is: "+alertText);
		Assert.assertEquals(alertText, "I am a JS Alert");
	}
	
	@Test
	public void jsConfirmTest() {
		driver.findElement(By.xpath(JSConfirm)).click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("The alert text is: "+alertText);
		Assert.assertEquals(alertText, "I am a JS Confirm");
		alert.accept();
		String result = driver.findElement(By.xpath(resultPath)).getText();
		System.out.println("the text od the result is: "+result);
		Assert.assertEquals(result, "You clicked: Ok");	
	}
	
	@Test
	public void jsPrompt() {
		driver.findElement(By.xpath(jsPromptPath)).click();
		Alert alert = driver.switchTo().alert();
		alert.sendKeys("I am sending this message in alert box!");
		alert.accept();
		String result = driver.findElement(By.xpath(resultPath)).getText();
		System.out.println("the text od the result is: "+result);
		Assert.assertEquals(result, "You entered: I am sending this message in alert box!");
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
