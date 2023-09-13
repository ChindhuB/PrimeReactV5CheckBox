
/*@author Chindhu Babu*/
/* Base Test Class extends every Test
 annotations @BeforeTest, @Parameters, @AfterTest */
package uitests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.BrowserVer;
import utils.CaptureScreen;
import utils.WebDriverSetUp;

import java.time.Duration;

public class TestBase {
    public static String URL;
    public static WebDriver driver;
    public static String browserparam;
    public CaptureScreen cs;
    public static String screenshotnm;

    @BeforeTest
    @Parameters("browser")
    public  void setUp(@Optional("chrome")String browser) {
        cs=new CaptureScreen();
        browserparam=browser.toUpperCase();
        URL = WebDriverSetUp.getProperties().getProperty("url");
        driver =WebDriverSetUp.getRemoteWebDriver(browserparam,"linux");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(180));
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    BrowserVer getBrowser(String browser){
        BrowserVer b;
        switch (browser) {
            case "chrome":
                b= BrowserVer.CHROME;
                break;
            case "firefox":
                b= BrowserVer.FIREFOX;
                break;
            case "chrome_headless":
                b= BrowserVer.CHROME_HEADLESS;
                break;
            case "edge":
                b= BrowserVer.EDGE;
                break;
            default:
                throw new RuntimeException("Unhandled browser!");
        }
        return b;

    }

}
