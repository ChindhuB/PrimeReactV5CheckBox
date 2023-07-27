/*@author Chindhu Babu*/
/* WebElement Utility Methods with main arguments
* @By , @WebDriver  */
package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebElementUtility {

    public WebDriverWait driverwait(WebDriver drv) {
        return new WebDriverWait(drv, Duration.ofSeconds(60));
    }

    public boolean isElementVisible(By arg0, WebDriver drv) {
        boolean elementVisible = false;
        try {
            driverwait(drv).until(ExpectedConditions.visibilityOfElementLocated(arg0));
            elementVisible = true;

        } catch (TimeoutException ex) {
            elementVisible = false;
        }
        return elementVisible;
    }

    public void clickOnElement(By arg0, WebDriver drv) {
        Actions act = new Actions(drv);
        act.moveToElement(findElement(arg0, drv)).click().build().perform();
    }
    public WebElement findElement(By arg0, WebDriver drv) {
        return drv.findElement(arg0);
    }

    public String getAttribute(By arg0, String attr, WebDriver drv) {
        return findElement(arg0, drv).getAttribute(attr);
    }
    public WebElement findParentElement(By arg0, WebDriver drv){
        return findElement(arg0, drv).findElement(By.xpath("./.."));
    }

    public void scriptExecuteScrollWindow(WebDriver drv,int x,int y){
        scriptExecute(drv).executeScript("window.scrollBy("+x+","+y+")");
    }
    public JavascriptExecutor scriptExecute(WebDriver drv){
        JavascriptExecutor js = (JavascriptExecutor) drv;
        return js;
    }
}
