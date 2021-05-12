/**
 * This class will have all
 * the locator elements needed in the Trivago Discover page  
 * the methods needed to access the elements
 */
package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Vigneshwaran Ravindran
 *
 */
public class TrivagoDiscoverPage {
	public WebDriver driver;
	
	
	//private By location             = By.xpath("//div[@data-qa='button-inspiration-destination']");
	private By locationClear        = By.cssSelector("button[class*='DestinationControl_iconClear']");
	private By location             = By.xpath("//input[@type='search']");
	private By cityDestination      = By.xpath("//li[@data-qa='destination-item'][1]");
	
	private By radius               = By.cssSelector("div[data-qa='button-inspiration-distance']");
	private By distance_None        = By.cssSelector("ul[class*='DistanceSelect_list'] li:nth-child(1)");
	
	private By stay_DateRange       = By.cssSelector("div[class*='Control_type-dateRange']");
	private By stay_Dates           = By.cssSelector("li[data-qa='stay-select']");
	private By stay_InputMinus      = By.cssSelector("div[data-qa='stay-input-plus']");
	private By stay_InputPlus       = By.cssSelector("div[data-qa='stay-input-plus']");
	private By stay_Apply           = By.cssSelector("div[class*='StaySelect_apply']");
	
	private By guests               = By.cssSelector("div[class*='Control_type-guest']");
	private By adults_InputMinus    = By.cssSelector("div[data-qa='guests-adults-input-minus']");
	private By adults_InputPlus     = By.cssSelector("div[data-qa='guests-adults-input-plus']");
	private By children_InputPlus   = By.cssSelector("div[data-qa='guests-children-input-plus']");
	private By rooms_InputPlus      = By.cssSelector("div[data-qa='guests-rooms-input-plus']");
	private By guests_Apply         = By.cssSelector("button[data-qa='apply-guests']");
	
	private By currency             = By.cssSelector("div[data-qa='button-currency']");
	private By currency_Euro        = By.xpath("//li[@data-qa='currency-option'][1]");
	
	private By viewDeal             = By.cssSelector("div[data-qa='tile-button-clickout']");

	public TrivagoDiscoverPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getLocationClear() {
		return driver.findElement(locationClear);
	}

	public WebElement getLocation() {
		return driver.findElement(location);
	}
	
	public WebElement getCityDestination() {
		return driver.findElement(cityDestination);
	}
	
	public WebElement getRadius() {
		return driver.findElement(radius);
	}
	
	public WebElement getDistanceNone() {
		return driver.findElement(distance_None);
	}
	
	public WebElement getStayDateRange() {
		return driver.findElement(stay_DateRange);
	}
	
	public List<WebElement> getStayDates() {
		return driver.findElements(stay_Dates);
	}

	public WebElement getStayInputMinus() {
		return driver.findElement(stay_InputMinus);
	}
	
	public WebElement getStayInputPlus() {
		return driver.findElement(stay_InputPlus);
	}
	
	public WebElement getStayApply() {
		return driver.findElement(stay_Apply);
	}
	
	public WebElement getGuests() {
		return driver.findElement(guests);
	}
	
	public WebElement getAdultInputMinus() {
		return driver.findElement(adults_InputMinus);
	}
	
	public WebElement getAdultInputPlus() {
		return driver.findElement(adults_InputPlus);
	}
	
	public WebElement getChildrenInputPlus() {
		return driver.findElement(children_InputPlus);
	}
	
	public WebElement getRoomsInputPlus() {
		return driver.findElement(rooms_InputPlus);
	}
	
	public WebElement getGuestsApply() {
		return driver.findElement(guests_Apply);
	}
	
	public WebElement getCurrencyEuro() {
		driver.findElement(currency).click();
		return driver.findElement(currency_Euro);
	}
	
	public WebElement getViewDeal() { 
		return driver.findElement(viewDeal);
	}

}
