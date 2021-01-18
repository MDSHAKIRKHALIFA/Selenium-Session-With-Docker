package StepsDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test {

	WebDriver driver;
	
	@Before
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.target.com/");
	}
	
	@Given("^I enter valid cridentional$")
	public void i_enter_valid_cridentional() {
	 
		
		
	}

	@Then("^I click on enter button$")
	public void i_click_on_enter_button()  {
	  
	}

	@When("^I login I should see my name on the to left corner$")
	public void i_login_I_should_see_my_name_on_the_to_left_corner() {

	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}
