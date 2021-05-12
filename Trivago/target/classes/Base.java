/**
 * This class will initialise the browser driver for the entire project
 */
package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Vigneshwaran Ravindran
 */
public class Base {
	public WebDriver driver;
	public Properties props;
	public WebDriverWait w;
	public Actions actions;

	public WebDriver initialiseDriver() throws IOException {
		props = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		props.load(fis);
		String browserName = props.getProperty("browser");
		String chromeDriver = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe";
		String fireFoxDriver = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\geckodriver.exe";

		if (browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromeDriver);
			ChromeOptions chOptions = new ChromeOptions();
			if (browserName.contains("headless")) {
				chOptions.addArguments("headless");
			}
			driver = new ChromeDriver();
			w = new WebDriverWait(driver, 20);
			actions = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}

		else if (browserName.contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", fireFoxDriver);
			driver = new FirefoxDriver();
		}
		return driver;
	}
}
