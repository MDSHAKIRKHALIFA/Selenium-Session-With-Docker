package com.qa.Login;
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

public class LoginTest {
	WebDriver driver;
	Properties prop;
	
	String prop_Path = "/src/main/java/com/qa/properties/config.properties"; 
	
	public LoginTest() {
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
	public void initialization() {
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(prop.getProperty("browser").equalsIgnoreCase("ff")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("loginUrl"));
	}
	
	@Test
	public void successfulLogin() {
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
		String siccessfulloginText = driver.findElement(By.xpath("//div[@id='flash']")).getText();
		System.out.println("The successful login text is: "+siccessfulloginText);
		Assert.assertEquals(siccessfulloginText, "You logged into a secure area!\n"+"×");
	}
	
	@Test
	public void invalidLogin() {
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("Password");
		driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
		String invalidloginText = driver.findElement(By.xpath("//div[@class='flash error']")).getText();
		System.out.println("The invalid login text is: "+invalidloginText);
		Assert.assertEquals(invalidloginText, "Your password is invalid!\n"+"×");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
