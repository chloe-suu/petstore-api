package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager extends TestListenerAdapter implements ITestNGListener{
    public ExtentSparkReporter spark;
    public ExtentReports extent;
    public ExtentTest test;

    String rpName;

    public void onStart(ITestContext testContext){
        //timestamp
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        rpName = "Test-Report-" + timestamp + ".html";

        //specify location for the report
        spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/testReports/" + rpName);

        spark.config().setDocumentTitle("apiProject");
        spark.config().setReportName("Pets store Users API");
        spark.config().setCss("css-string");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Operation System", System.getProperty("os.name"));
        extent.setSystemInfo("Environment", "QA");

    }

    public void onTestSuccess(ITestResult result){
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());

        //ExtentReports log operation for passed tests.
        test.log(Status.PASS, "Test passed");

    }
    public void onTestFailure (ITestResult result){
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());

        //ExtentReports log operation for passed tests.
        test.log(Status.FAIL, "Test failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSkipped (ITestResult result){
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());

        //ExtentReports log operation for passed tests.
        test.log(Status.SKIP, "Test skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    public void onFinish (ITestContext testContext){
        extent.flush();
    }
}
