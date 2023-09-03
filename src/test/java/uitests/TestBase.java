
/*@author Chindhu Babu*/
/* Base Test Class extends every Test
 annotations @BeforeTest, @Parameters, @AfterTest */
package uitests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.Browser;
import utils.CaptureScreen;
import utils.WebDriverSetUp;

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
        driver =WebDriverSetUp.getWebDriver(getBrowser(browserparam.toLowerCase()));
        driver.get(URL);
        driver.manage().window().maximize();
    }
    @AfterTest
    public void tearDown(){
        driver.close();
    }

    Browser getBrowser(String browser){
        Browser b;
        switch (browser) {
            case "chrome":
                b=Browser.CHROME;
                break;
            case "firefox":
                b=Browser.FIREFOX;
                break;
            case "chrome_headless":
                b=Browser.CHROME_HEADLESS;
                break;
            case "edge":
                b=Browser.EDGE;
                break;
            default:
                throw new RuntimeException("Unhandled browser!");
        }
        return b;

    }

}
