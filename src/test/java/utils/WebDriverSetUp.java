/*@author Chindhu Babu*/
/* Initialize WebDriver as per @argument
* @Browser */

package utils;

import config.ConfigProperty;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
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
                System.setProperty("webdriver.chrome.driver","C:/Users/Cibin/Desktop/BusyQA/chrome-win64/chromedriver.exe");
                ChromeOptions co=new ChromeOptions();
                co.setBinary("C:/Users/Cibin/Desktop/BusyQA/chrome-win64/chrome.exe");
                driver = new ChromeDriver(co);
                break;

            case CHROME_HEADLESS:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;

            case FIREFOX:
                // Takes the system proxy settings automatically
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions fo=new FirefoxOptions();
                fo.setBinary(new FirefoxBinary(new File("/usr/tmp/firefox")));
                //fo.setBinary("~/usr/tmp/firefox/firefox.exe");
                driver = new FirefoxDriver(fo);
                break;

            case EDGE:
                // Takes the system proxy settings automatically
                WebDriverManager.edgedriver().setup();
                EdgeOptions eo=new EdgeOptions();
                eo.setBinary("~/.cache/selenium");
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Unhandled browser or driver!");
        }
        return driver;
    }
}
