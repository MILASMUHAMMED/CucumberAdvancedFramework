package stepDefinitions;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.TestContextSetup;

public class OfferPageStepDefinition {
public WebDriver driver;
public String landingPageproductName;
public String offerPageProductName;
TestContextSetup testContextSetup;

public OfferPageStepDefinition(TestContextSetup testContextSetup)
{
	this.testContextSetup = testContextSetup;
}

	@Then("user searched for {string} shortname in offers page")
	public void user_searched_for_same_shortname_in_offers_page(String shortname) throws InterruptedException {		
		switchToOffersPage();
		testContextSetup.driver.findElement(By.xpath("//input[@type='search']")).clear();
		Thread.sleep(3000);
		testContextSetup.driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortname);
		Thread.sleep(3000);
		offerPageProductName = testContextSetup.driver.findElement(By.cssSelector("tr td:nth-child(1)")).getText();
	}
	
	public void switchToOffersPage() {
		testContextSetup.driver.findElement(By.linkText("Top Deals")).click();
		Set<String> s1 = testContextSetup.driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		String parentWindow = i1.next();
		String childWindow = i1.next();		
		testContextSetup.driver.switchTo().window(childWindow);
	}
	
    @And("^validate productname in offers page matches with landing page$")
    public void validate_productname_in_offers_page_matches_with_landing_page() throws Throwable {
    	Assert.assertEquals(offerPageProductName, testContextSetup.landingPageproductName);
    	testContextSetup.driver.quit();
    }
}
