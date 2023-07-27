/*@author Chindhu Babu*/
/* Initialize WebDriver as per @argument
* @Browser */

package utils;

import config.ConfigProperty;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Properties;

public class WebDriverSetUp {
    private  static WebDriver driver;
    private static DesiredCapabilities capability=new DesiredCapabilities();
    public static Properties getProperties() {
        return ConfigProperty.getInstance();
    }
    public  static WebDriver getWebDriver(Browser browser){

        switch (browser) {
            case CHROME:
                // Takes the system proxy settings automatically
                driver = new ChromeDriver();
                break;

            case CHROME_HEADLESS:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;

            case FIREFOX:
                // Takes the system proxy settings automatically
                driver = new FirefoxDriver();
                break;

            case EDGE:
                // Takes the system proxy settings automatically
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Unhandled browser or driver!");
        }
        return driver;
    }
}
