/*@author Chindhu Babu*/
/* Listener Class for Report */
package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.IOUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import uitests.TestBase;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class CustomListener extends TestListenerAdapter {
    public ExtentSparkReporter htmlReporter;
    ExtentReports extent;
    ExtentTest logger;
    public String repName;
    public String timeStamp;

    public void onStart(ITestContext testContext) {

        timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
        // specify name and location of the report
        repName =  "./target/test-output/" +"Test-Report-" + timeStamp + ".html";
        htmlReporter = new ExtentSparkReporter(repName);

        try {
            htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environemnt", "QA");
        extent.setSystemInfo("user", "Chindhu");
        extent.setReportUsesManualConfiguration(true);


        htmlReporter.config().setDocumentTitle("PrrimeReactV5"); // Title of report
        htmlReporter.config().setTheme(Theme.STANDARD);

    }

    public void onTestStart(ITestResult tr) {

        htmlReporter.config().setReportName(TestBase.browserparam+" Test Report"); // name of the report
        extent.setSystemInfo("Host name", TestBase.URL);
        extent.setSystemInfo("Browser", TestBase.browserparam);
        // create new entry in the report
        String qualifiedName = tr.getTestClass().getName();
        int last = qualifiedName.lastIndexOf(".");
        String className = qualifiedName.substring(last+1);
        logger = extent.createTest(className, tr.getMethod().getDescription());
        logger.assignCategory(tr.getTestContext().getSuite().getName());
        logger.getModel().setStartTime(getTime(tr.getStartMillis()));
    }

    public void onTestSuccess(ITestResult tr) {
        // send the passed information to the report with GREEN color highlighted
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
        logger.getModel().setEndTime(getTime(tr.getEndMillis()));
    }

    public void onTestFailure(ITestResult tr) {
        CaptureScreen  cs=new CaptureScreen();
        // send the failed information to the report with RED color highlighted
        Object testObj=tr.getInstance();
        Class cls=tr.getTestClass().getRealClass().getSuperclass();
        String screenshotname="firstscreenshotname";
        cs.captureScreen(TestBase.driver,tr.getName().toString());
        try {
            screenshotname =cs.bscreenshotname;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
        logger.getModel().setEndTime(getTime(tr.getEndMillis()));
        String screenshotpath ="./target/test-output/Screenshots/" + screenshotname + ".png";
        File screenshot = new File(screenshotpath);
        if (screenshot.exists()) {
            try {
                InputStream in = new FileInputStream(screenshotpath);
                byte[] imageBytes = IOUtils.toByteArray(in);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);
                logger.fail("Screenshot is below:" +screenshotpath);
                logger.log(Status.FAIL, tr.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String("data:image/png;base64,"+base64).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            logger.log(Status.FAIL, tr.getThrowable());
        }
    }

    public void onTestSkipped(ITestResult tr) {
        // send the skipped information to the report with ORANGE color highlighted
        logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
        logger.getModel().setEndTime(getTime(tr.getEndMillis()));
        try {
            logger.log(Status.SKIP, tr.getThrowable());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
    }

}

