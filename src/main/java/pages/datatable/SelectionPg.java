/*@author Chindhu Babu*/
/* Selection Tab Page Class */
package pages.datatable;

import org.openqa.selenium.*;

public class SelectionPg extends BasePage {

    public SelectionPg(WebDriver drv) {
        super(drv);
    }

    public String verifyCheckBox(String text) {
        String xCustom = "//*[contains(translate(text() ,'" + text.replace(" ", "").toUpperCase() + "','" + text.replace(" ", "").toLowerCase() + "'),'" + text.toLowerCase() + "')]";
        By checkboxelem = By.xpath("//div/h5[contains(translate(text() ,'CHECKBOX','checkbox'),'checkbox')]/parent::div" + xCustom + "/parent::tr//*[@role='checkbox']");
        By checkboxelemchecked = By.xpath("//div/h5[contains(translate(text() ,'CHECKBOX','checkbox'),'checkbox')]/parent::div" + xCustom + "/parent::tr//*[@checked]");

        if (elemutil.isElementVisible(checkboxelem, driver)) {
            Rectangle rect = elemutil.findElement(checkboxelem,driver).getRect();
            elemutil.scriptExecuteScrollWindow(driver,rect.x-500,rect.y-500);
            elemutil.clickOnElement(checkboxelem, driver);
            return elemutil.getAttribute(checkboxelemchecked, "aria-checked", driver);
        } else {
            return "";
        }
    }


}