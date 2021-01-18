package cogni;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InterviewWithCoz {

	//<input type="submit" id=" submit_334350" value="Subscribe">
	WebDriver driver;
	@BeforeMethod
	public void setUp() {
		
	}
	
	@Test
	public void findElement() throws InterruptedException {
		//driver.findElement(By.xpath("//input[@id='submit_']"));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(""))).build().perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
		
		
	}
	
}
