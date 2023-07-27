/*@author Chindhu Babu*/
/* Base Page extends every Page Class */
package pages.datatable;

import org.openqa.selenium.WebDriver;
import utils.WebElementUtility;

public class BasePage {
    WebDriver driver;
    WebElementUtility elemutil;

    public BasePage(WebDriver drv) {
        this.driver = drv;
        elemutil = new WebElementUtility();
    }

}
