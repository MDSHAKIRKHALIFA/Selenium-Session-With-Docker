package com.qa.MouseHover;

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
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MouseHover {
	
String propPath = "/src/main/java/com/qa/properties/config.properties";

String user1Path = "//div[@class='example']//div[1]//img[1]";
String user2Path = "//div[@class='example']//div[2]//img[1]";
String user3Path = "//div[@class='example']//div[3]//img[1]";

String user1Messege = "//h5[contains(text(),'name: user1')]";
String user2Messege = "//h5[contains(text(),'name: user2')]";
String user3Messege = "//h5[contains(text(),'name: user3')]";
	
	
	WebDriver driver;
	Properties prop;
	
	MouseHover(){
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
		driver.get(prop.getProperty("MouseHover"));
	}
	
	@Test
	public void user1() {
		WebElement element1 = driver.findElement(By.xpath(user1Path));
		mouseHover(element1);
		String textofuser1 = driver.findElement(By.xpath(user1Messege)).getText();
		System.out.println("The text of user1 is: "+textofuser1);
		Assert.assertEquals(textofuser1, "name: user1");
	}
	@Test
	public void user2() {
		WebElement element2 = driver.findElement(By.xpath(user2Path));
		mouseHover(element2);
		String textofuser2 = driver.findElement(By.xpath(user2Messege)).getText();
		System.out.println("The text of user2 is: "+textofuser2);
		Assert.assertEquals(textofuser2, "name: user2");
	}
	
	@Test
	public void user3() {
		WebElement element3 = driver.findElement(By.xpath(user3Path));
		mouseHover(element3);
		String textofuser3 = driver.findElement(By.xpath(user3Messege)).getText();
		System.out.println("The text of user3 is: "+textofuser3);
		Assert.assertEquals(textofuser3, "name: user3");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public void mouseHover(WebElement target) {
		Actions action = new Actions(driver);
		action.moveToElement(target).build().perform();
		
	}

}
