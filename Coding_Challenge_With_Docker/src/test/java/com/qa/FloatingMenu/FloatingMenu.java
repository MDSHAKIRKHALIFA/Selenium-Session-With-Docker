package com.qa.FloatingMenu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FloatingMenu {
	
	String propPath = "/src/main/java/com/qa/properties/config.properties";
	
	String floatingMenuPath = "//div[@id='menu']";
	
	WebDriver driver;
	Properties prop;
	
	FloatingMenu(){
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
		driver.get(prop.getProperty("FloatingMenu"));
	}
	
	@Test
	public void floatingMenuHomeTest() {
		scrollPage();
		boolean menuDisplayed = driver.findElement(By.xpath(floatingMenuPath)).isDisplayed();
		Assert.assertEquals(true, menuDisplayed);
	}
	
	@Test
	public void getFloatingMenuText() {
		String text = driver.findElement(By.xpath(floatingMenuPath)).getText();
		System.out.println("The menu are: "+text);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public  void scrollPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}

}
