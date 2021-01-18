package con.qa.Iframe;

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

public class Iframe {
	
String propPath = "/src/main/java/com/qa/properties/config.properties";

String iframePath = "mce_0_ifr";
String textPath = "//body[@id='tinymce']";
		
	WebDriver driver;
	Properties prop;
	
	Iframe(){
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
		driver.get(prop.getProperty("Iframe"));
	}
	
	@Test
	public void iframeTest() {
		driver.switchTo().frame(iframePath);
		WebElement element = driver.findElement(By.xpath(textPath));
		element.clear();
		element.sendKeys("I am testing the frame!");
		String gettheText = element.getText();
		System.out.println("The text is: "+gettheText);
		Assert.assertEquals(gettheText, "I am testing the frame!");
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
