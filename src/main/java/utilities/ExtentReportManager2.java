package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager2 implements ITestNGListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest test;

    String reportName;

    public void onStart(ITestContext testContext){
        System.out.println("ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");

        //timestamp
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test-Report-" + timestamp + ".html";

        //specify location for the report
        sparkReporter = new ExtentSparkReporter("./petstore-api/testReports/" + reportName);

        sparkReporter.config().setDocumentTitle("apiProject");
        sparkReporter.config().setReportName("Pets store Users API");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Operation System", System.getProperty("os.name"));
        extentReports.setSystemInfo("Environment", "QA");
    }

    public void onTestSuccess(ITestResult result){
        test = extentReports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());

        //ExtentReports log operation for passed tests.
        test.log(Status.PASS, "Test passed");
    }
    public void onTestFailure (ITestResult result){
        test = extentReports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());

        //ExtentReports log operation for passed tests.
        test.log(Status.FAIL, "Test failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSkipped (ITestResult result){
        test = extentReports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());

        //ExtentReports log operation for passed tests.
        test.log(Status.SKIP, "Test skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    public void onFinish (ITestContext testContext){
        extentReports.flush();
    }
}
