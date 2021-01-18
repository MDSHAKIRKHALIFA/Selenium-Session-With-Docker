package com.qa.dynamicControls;

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

public class DynamicControls {
	
	WebDriver driver;
	Properties prop;
	
	String propPath = "/src/main/java/com/qa/properties/config.properties";
	
	//Check_Bo
	String checkBox = "//div[contains(text(),' A checkbox')]";
	String removeButton = "//button[contains(text(),'Remove')]";
	String addButton = "//button[contains(text(),'Add')]";
	
	//Text_Box
	String textBox = "//input[@type='text']";
	String enableButton = "//button[contains(text(),'Enable')]";
	String disableButton = "//button[contains(text(),'Disable')]";
	
	//Message
	String textMessage = "//p[@id='message']";
	
	DynamicControls(){
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
		driver.get(prop.getProperty("DynamicControls"));
	}
	
	@Test()
	public void checkBoxTest() {
		WebElement removeButtonPath = driver.findElement(By.xpath(removeButton));
		if (removeButtonPath.isDisplayed()) {
			removeButtonPath.click();
			String rButtonText = driver.findElement(By.xpath(textMessage)).getText();
			System.out.println(rButtonText);
			Assert.assertEquals(rButtonText,"It's gone!");
			
		}else {
			driver.findElement(By.xpath(addButton)).click();
			String aButtonText = driver.findElement(By.xpath(textMessage)).getText();
			System.out.println(aButtonText);
			Assert.assertEquals(aButtonText, "It's back!");
		}
	}
	
	@Test()
	public void textBoxTest() {
		WebElement enButton = driver.findElement(By.xpath(enableButton));
		if (enButton.isDisplayed()) {
			enButton.click();
			String enButtonText = driver.findElement(By.xpath(textMessage)).getText();
			System.out.println(enButtonText);
			Assert.assertEquals(enButtonText, "It's enabled!");
		}else  {
			driver.findElement(By.xpath(disableButton)).click();
			String dsButtonText = driver.findElement(By.xpath(textMessage)).getText();
			System.out.println(dsButtonText);
			Assert.assertEquals(dsButtonText, "It's disabled!");
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	//Webdriver/explecit wait method
	public void dynamicWait(String locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
