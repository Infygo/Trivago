/**
 * This class will have all 
 * the locator elements needed in the Trivago home page 
 * the methods needed to access the elements
 */
package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Vigneshwaran Ravindran
 *
 */
public class TrivagoHomePage {
	public WebDriver driver;

	private By trivagoLogo = By.cssSelector("a[href='https://www.trivago.co.uk']");
	private By discover = By.id("discover-header-link");

	public TrivagoHomePage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getTrivagoLogo() {
		return driver.findElement(trivagoLogo);
	}

	public TrivagoDiscoverPage getDiscover() { // clicking this element will have the transition to discover page
		driver.findElement(discover).click();
		TrivagoDiscoverPage discoverPage = new TrivagoDiscoverPage(driver);
		return discoverPage;
	}

}
