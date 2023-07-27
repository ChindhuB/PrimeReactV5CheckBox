/*@author Chindhu Babu*/
/* Capture Screen Shot on Test Failure  called in Custom Listener*/
package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
public class CaptureScreen {
    public String bscreenshotname;;
    public void captureScreen(WebDriver driver, String screenschotname)  {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        bscreenshotname=screenschotname+dtf.format(java.time.LocalDateTime.now()).toString().replace(":","");
        TakesScreenshot ts=(TakesScreenshot)driver;
        File scr=ts.getScreenshotAs(OutputType.FILE);
        File trgt=new File("./target/test-output/Screenshots/" + bscreenshotname + ".png");
        try {
            FileUtils.copyFile(scr, trgt);
        }catch(Exception e){
           e.printStackTrace();
        }
        System.out.println("Screenshot taken");
    }
}
