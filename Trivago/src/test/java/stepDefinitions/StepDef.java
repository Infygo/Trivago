/**
 * 
 */
package stepDefinitions;

import resources.Base;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.TrivagoDiscoverPage;
import pageObjects.TrivagoHomePage;

/**
 * @author vravindran
 *
 */
public class StepDef extends Base {
	private static Logger log = LogManager.getLogger(StepDef.class.getName());
	TrivagoDiscoverPage discoverPage;
	TrivagoHomePage homePage;
	
	@Given("^Initialise driver and navigate to Trivago homepage$")
	public void initialise_driver_and_navigate_to_trivago_homepage() throws IOException {
		driver = initialiseDriver();
		driver.get(props.getProperty("homeurl"));
		homePage = new TrivagoHomePage(driver);
		discoverPage = new TrivagoDiscoverPage(driver);
		Assert.assertTrue(homePage.getTrivagoLogo().isEnabled());
		log.info("Navigated to Trivago home page");
	}

	@When("^Click on Disover tab$")
	public void click_on_disover_tab() {
		homePage.getDiscover();
		Assert.assertTrue(driver.getCurrentUrl().contains("discover"));
		log.info("Navigated to Discover page");
	}

	@When("^Input city location (.+)$")
	public void input_city_location(String cityName) throws IOException, InterruptedException {
		discoverPage.getLocation().click();
		
		w.until(ExpectedConditions.elementToBeClickable(discoverPage.getLocationClear()));
		discoverPage.getLocationClear().click();
		
		w.until(ExpectedConditions.elementToBeClickable(discoverPage.getLocation()));
		discoverPage.getLocation().click();
		
		discoverPage.getLocation().sendKeys(cityName);
		Thread.sleep(1000);
		actions.moveToElement(discoverPage.getCityDestination()).click().build().perform();
		log.info("Viewing deals for City:" + cityName);
	}

	@When("^Input Radius (\\d+) Stay dates (.+) (.+) and Guests (\\d+) (\\d+) (\\d+)$")
	public void input_radius_stay_dates_and_guests(int radius, String staysOn, String stayNights, int adults,
			int children, int rooms) throws InterruptedException {
		
		WebElement distanceList = driver.findElement(
				                  By.cssSelector("ul[class*='DistanceSelect_list'] li:nth-child(" + radius + ")"));

		/* Radius */
		discoverPage.getRadius().click();
		if (radius != 4) {
			actions.moveToElement(distanceList).click().build().perform();
		}

		/* Stay dates */
		actions.moveToElement(discoverPage.getStayDateRange()).click().build().perform();
		int stayCheckBoxSize = discoverPage.getStayDates().size();
		List<WebElement> stayDates = discoverPage.getStayDates();
		for (int i = 0; i <= stayCheckBoxSize - 1; i++) {
			if (!staysOn.equals(stayDates.get(i).getText())) {
				int j = i + 1;
				actions.moveToElement(
						driver.findElement(By.cssSelector("ul[class*='StaySelect_list'] li:nth-child(" + j + ")")))
						.click().build().perform();
				;
			}
		}
		if (stayNights.equals("-")) {
			discoverPage.getStayInputMinus().click();
		} else if (stayNights.equals("+")) {
			discoverPage.getStayInputPlus().click();
		}
		discoverPage.getStayApply().click();

		/* Guests */
		discoverPage.getGuests().click();
		if (adults > 2) {
			for (int i = 2; i < adults; i++) {
				discoverPage.getAdultInputPlus().click();
			}
		} else if (adults < 2) {
			discoverPage.getAdultInputMinus().click();
		}

		if (children > 0) {
			for (int i = 1; i <= children; i++) {
				discoverPage.getChildrenInputPlus().click();
			}

		}
		if (rooms > 1) {
			for (int i = 1; i < rooms; i++) {
				discoverPage.getRoomsInputPlus().click();
			}
		}

		discoverPage.getGuestsApply().click();
		log.info("Reservation details: Staydates-" +staysOn);
		log.info("Guests details: Adults-" +adults+" Children-"+ children+" Rooms-"+ rooms);

	}

	@When("^Change currency to Euros$")
	public void change_currency_to_euros() throws InterruptedException {
		discoverPage.getCurrencyEuro().click();
		//Thread.sleep(2000);
	}

	@When("^Deals are displayed click View deal$")
	public void deals_are_displayed_click_view_deal() {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		w.until(ExpectedConditions.elementToBeClickable(discoverPage.getViewDeal()));
		discoverPage.getViewDeal().click();
		log.info("View deal clicked");
	}

	@Then("^Verify if new tab is opened displaying the deals$")
	public void verify_if_new_tab_is_opened_displaying_the_deals() {
		Set<String> windowIds = driver.getWindowHandles();
		Iterator<String> its = windowIds.iterator();

		String trivagoWindow = its.next();
		String childWindow = its.next();

		driver.switchTo().window(childWindow);
		String externalTab = driver.getTitle();
		driver.switchTo().window(trivagoWindow);
		String trivagoTab = driver.getTitle();
		Assert.assertTrue(!externalTab.equals(trivagoTab));
		log.info("New tab opened on clicking View deal");
	}

	@And("^Close the browser$")
	public void close_the_browser() {
		log.info("Closing the browser");
		driver.quit();
	}

}
