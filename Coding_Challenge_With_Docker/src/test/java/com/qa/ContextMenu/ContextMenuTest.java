package com.qa.ContextMenu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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

public class ContextMenuTest {
	WebDriver driver;
	Properties prop;
	String urlLocation = "/src/main/java/com/qa/properties/config.properties";
	
	String boxXpath = "//div[@id='hot-spot']";

	public ContextMenuTest(){
		try {
			prop = new Properties();
			FileInputStream fis;
			fis = new FileInputStream(System.getProperty("user.dir")+urlLocation);
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
			driver = new ChromeDriver();
		}else if(prop.getProperty("browser").equalsIgnoreCase("ff")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(prop.getProperty("ContextMenu"));
	}
	
	@Test
	public void contextmenuTest() {
		Actions action = new Actions(driver);
		WebElement contextmenuXpath = driver.findElement(By.xpath(boxXpath));
		action.contextClick(contextmenuXpath).build().perform();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("The alert text is: "+alertText);
		Assert.assertEquals(alertText, "You selected a context menu");
		alert.accept();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
