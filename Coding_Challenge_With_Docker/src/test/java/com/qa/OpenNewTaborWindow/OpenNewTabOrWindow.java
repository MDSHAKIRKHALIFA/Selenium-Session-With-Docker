package com.qa.OpenNewTaborWindow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
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

public class OpenNewTabOrWindow {

	WebDriver driver;
	Properties prop;

	String prop_Path = "/src/main/java/com/qa/properties/config.properties";

	OpenNewTabOrWindow(){
		try {
			prop = new Properties();
			FileInputStream fis;
			fis = new FileInputStream(System.getProperty("user.dir")+prop_Path);
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
		}else if (prop.getProperty("browser").equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("OpeninNewTab"));
	}
	
	@Test
	public void newWindowTest() {
		driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();
		Set<String> windowhandler = driver.getWindowHandles();
		Iterator<String> iterator = windowhandler.iterator();
		String parentWindow = iterator.next();
		String childWindow= iterator.next();
		driver.switchTo().window(childWindow);
		String newText = driver.findElement(By.xpath("//div[@class='example']")).getText();
		System.out.println("The text of the new window is: "+newText);
		Assert.assertEquals(newText, "New Window");
	}
	
	@AfterMethod 
	public void tearDown() {
		driver.quit();
	}
}
