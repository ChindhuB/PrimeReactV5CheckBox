/*@author Chindhu Babu*/
/* Initialize WebDriver as per @argument
* @Browser */

package utils;

import config.ConfigProperty;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;

public class WebDriverSetUp {
    private  static WebDriver driver;
    private static DesiredCapabilities capability=new DesiredCapabilities();
    public static Properties getProperties() {
        return ConfigProperty.getInstance();
    }
    public  static WebDriver getWebDriver(String browser){

        switch (browser) {
            case "CHROME":
                // Takes the system proxy settings automatically
                System.setProperty("webdriver.chrome.driver","C:/Users/Cibin/Desktop/BusyQA/chrome-win64/chromedriver.exe");

                ChromeOptions co=new ChromeOptions();
                co.setBinary("C:/Users/Cibin/Desktop/BusyQA/chrome-win64/chrome.exe");
                driver = new ChromeDriver(co);
                break;

            case "CHROME_HEADLESS":
                ChromeOptions chromeOptions = new ChromeOptions();

                chromeOptions.addArguments("--headless");
                //chromeOptions.addArguments("--disable-gpu");
               chromeOptions.addArguments("--no-sandbox");
               /*////// chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("enable-automation");
                chromeOptions.addArguments("--disable-infobars");*/
                chromeOptions.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "FIREFOX":
                // Takes the system proxy settings automatically

                FirefoxOptions fo=new FirefoxOptions();
                //fo.setBinary(new FirefoxBinary(new File("~/usr/tmp/firefox/firefox.exe")));
                //fo.setBinary("~/usr/bin/firefox.exe");
                fo.addArguments("--headless");
                /*try {
                    System.out.println(System.getProperty("user.name"));
                    System.out.println(System.getProperty("user.dir"));
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                WebDriverManager.firefoxdriver().setup();
               /* Optional<Path> browserPath1 = WebDriverManager.firefoxdriver()
                        .getBrowserPath();
                System.out.println(browserPath1);*/
                driver = new FirefoxDriver(fo);

                break;

            case "EDGE":
                // Takes the system proxy settings automatically
                WebDriverManager.edgedriver().setup();
                EdgeOptions eo=new EdgeOptions();
                eo.setBinary("/usr/bin/msedge");
                driver = new EdgeDriver(eo);
                break;

            default:
                throw new RuntimeException("Unhandled browser or driver!");
        }
        return driver;
    }
    public  static WebDriver getRemoteWebDriver(String browser, String platform){
        DesiredCapabilities capability=new DesiredCapabilities();
        String remURL=getProperties().getProperty("remoteUrl");
        switch(platform) {
            case "windows":
                capability.setPlatform(Platform.WINDOWS);
                break;
            case "linux":
                //capability.setPlatform(Platform.LINUX);
                break;
            default:
                throw new RuntimeException("Unhandled Platform!");
        }

        switch (browser) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                ChromeOptions co=new ChromeOptions();
                co.setCapability("browserName",Browser.CHROME.browserName());
                System.out.println(Browser.CHROME.browserName()+" : "+remURL);
                try {
                    driver = new RemoteWebDriver(new URL(remURL), co);

                } catch (MalformedURLException e) {
                    System.out.println("Invalid grid URL");
                } catch (Exception e) {
                    System.out.println(e.getMessage());

                }
                break;
            case "FIREFOX":
                FirefoxOptions fo=new FirefoxOptions();
                fo.setCapability("browserName",Browser.FIREFOX.browserName());
                WebDriverManager.firefoxdriver().setup();
                System.out.println(Browser.FIREFOX.browserName()+" : "+remURL);
                try {
                    driver = new RemoteWebDriver(new URL(remURL), fo);;

                } catch (MalformedURLException e) {
                    System.out.println("Invalid grid URL");
                } catch (Exception e) {
                    System.out.println(e.getMessage());

                }
                break;
            case "CHROME_HEADLESS":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                capability.setCapability("browserName",Browser.CHROME.browserName());
                capability.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
                break;
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                EdgeOptions eo = new EdgeOptions();
                eo.setCapability("browserName",Browser.EDGE.browserName());
                System.out.println(Browser.EDGE.browserName()+" : "+remURL);
                try {
                    driver = new RemoteWebDriver(new URL(remURL), eo);

                } catch (MalformedURLException e) {
                    System.out.println("Invalid grid URL");
                } catch (Exception e) {
                    System.out.println(e.getMessage());

                }
                break;
            default:
                throw new RuntimeException("Unhandled browser!");
        }



        return driver;
    }
}
