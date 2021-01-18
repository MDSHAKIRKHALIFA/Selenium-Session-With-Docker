package com.qa.DragAndDrop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragAndDropTest {
	
	WebDriver driver;
	Properties prop;
	
	String prop_Path = "/src/main/java/com/qa/properties/config.properties"; 
	
	String boxA = "//div[@id='column-a']";
	String boxB = "//div[@id='column-b']";

	
	DragAndDropTest(){
		try {
			prop =  new Properties();
			FileInputStream fis; 	
			fis= new FileInputStream(System.getProperty("user.dir")+prop_Path);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@BeforeMethod
	public void setUp() {
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}else if(prop.getProperty("browser").equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("DragandDropurl"));
	}
	
	@Test
	public void dragNDrop() {
		Actions action = new Actions(driver);
		action.clickAndHold(driver.findElement(By.xpath(boxA))).moveToElement(driver.findElement(By.xpath(boxB))).build().perform();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}


}
