package com.qa.NotificationMessage;

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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NotificationMessage {

	WebDriver driver;
	Properties prop;
	
	String prop_Path = "/src/main/java/com/qa/properties/config.properties";
	
	public NotificationMessage() {
		prop = new Properties();
		FileInputStream fis;
		try {
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
		driver.get(prop.getProperty("NotificationMessage"));
	}
	
	@Test
	public void notificationMessageTest() throws InterruptedException {
		
		WebElement element =driver.findElement(By.xpath("//a[contains(text(),'Click here')]"));
		
			element.click();

			
			String text = driver.findElement(By.xpath("//div[@id='flash']")).getText();
			
			if(text.contains("Action successful\n"+"×")) {
				
				Assert.assertEquals(text, "Action successful\n"+"×");
				System.out.println("The text is: "+text);
			}else if (text.contains("Action unsuccessful, please try again\n"+"×")) {
				Assert.assertEquals(text, "Action unsuccessful, please try again\n"+"×");
				
				System.out.println("The text is: "+text);
			}else if (text.contains("Action Unsuccessful\n"+"×")) {
				Assert.assertEquals(text, "Action Unsuccessful\n"+"×");
				System.out.println("The text is: "+text);
			}	
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
