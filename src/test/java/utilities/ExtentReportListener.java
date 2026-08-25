package utilities;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        String reportDirectory = System.getProperty("user.dir")
                + File.separator + "target"
                + File.separator + "ExtentReports";

        File directory = new File(reportDirectory);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String reportPath = reportDirectory
                + File.separator + "ExtentReport.html";

        System.out.println("Extent Report Path: " + reportPath);

        ExtentSparkReporter sparkReporter =
                new ExtentSparkReporter(reportPath);

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);

        System.out.println(
                "Extent Test Started: "
                + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");

        extent.flush();

        System.out.println("Extent Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        extent.flush();

        System.out.println("Extent Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test Skipped");

        extent.flush();

        System.out.println("Extent Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        if (extent != null) {
            extent.flush();
        }

        System.out.println("Extent Report Generated");
    }
}