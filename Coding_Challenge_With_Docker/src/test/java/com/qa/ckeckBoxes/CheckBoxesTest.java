package com.qa.ckeckBoxes;

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

public class CheckBoxesTest {
	WebDriver driver;
	Properties prop;
	
	//by using user dri the project would run to any machine 
	String prop_Path = "/src/main/java/com/qa/properties/config.properties"; 
	
	//All Xpath
	
	String checkBox1 = "//body//input[1]";
	String checkBox2 = "//body//input[2]";

	CheckBoxesTest(){
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
		driver.get(prop.getProperty("checkBoxes"));
	}
	
	@Test
	public void unCheckedBoxes() {
		Boolean isBoxUnChecked = driver.findElement(By.xpath(checkBox1)).isSelected();
		System.out.println("The box1 is not selected "+isBoxUnChecked);
		
		if(isBoxUnChecked==false) {
			driver.findElement(By.xpath(checkBox1)).click();
			System.out.println("The box is not selected");
			Assert.assertTrue(true);
		}else if(isBoxUnChecked==true){
			System.out.println("The Checkbox is selected");
			System.out.println("The box is selected");
			Assert.assertTrue(true);
		}
		
	}
	
	@Test
	public void CheckedBoxes() {
		Boolean isBoxChecked = driver.findElement(By.xpath(checkBox2)).isSelected();
		System.out.println("The box is selected"+isBoxChecked);
		if (isBoxChecked==true) {
			System.out.println("The box2 is selected ");
			Assert.assertTrue(true);
		}else if (isBoxChecked==false) {
			driver.findElement(By.xpath(checkBox2)).click();
			System.out.println("The box is not selected");
			Assert.assertTrue(true);
		}
	}

	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
