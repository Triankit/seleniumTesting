package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

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

        ExtentSparkReporter sparkReporter =
                new ExtentSparkReporter(reportPath);

        // Report design
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
        sparkReporter.config().setReportName("OpenCart Selenium Test Execution Report");
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);

        // System information
        extent.setSystemInfo("Operating System",
                System.getProperty("os.name"));

        extent.setSystemInfo("OS Version",
                System.getProperty("os.version"));

        extent.setSystemInfo("Java Version",
                System.getProperty("java.version"));

        extent.setSystemInfo("User",
                System.getProperty("user.name"));

        extent.setSystemInfo("Environment",
                "QA");
    }

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        String className = result.getTestClass()
                .getName();

        String suiteName = result.getTestContext()
                .getName();

        ExtentTest extentTest =
                extent.createTest(testName);

        extentTest.assignCategory(className);
        extentTest.info("Test Class: " + className);
        extentTest.info("Test Suite: " + suiteName);

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().log(
                Status.PASS,
                "Test Passed Successfully"
        );

        extent.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().log(
                Status.FAIL,
                "Test Failed"
        );

        // Add exception details
        if (result.getThrowable() != null) {

            test.get().fail(
                    result.getThrowable()
            );
        }

        // Take screenshot
        String screenshotPath = captureScreenshot(result);

        if (screenshotPath != null) {

            try {

                test.get().fail(
                        "Failure Screenshot",
                        MediaEntityBuilder
                                .createScreenCaptureFromPath(
                                        screenshotPath)
                                .build()
                );

            } catch (Exception e) {

                test.get().warning(
                        "Unable to attach screenshot: "
                                + e.getMessage()
                );
            }
        }

        extent.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().log(
                Status.SKIP,
                "Test Skipped"
        );

        if (result.getThrowable() != null) {

            test.get().skip(
                    result.getThrowable()
            );
        }

        extent.flush();
    }

    @Override
    public void onFinish(ITestContext context) {

        if (extent != null) {
            extent.flush();
        }
    }

    private String captureScreenshot(ITestResult result) {

        try {

            Object testInstance = result.getInstance();

            if (!(testInstance instanceof BaseClass)) {
                return null;
            }

            BaseClass baseClass =
                    (BaseClass) testInstance;

            WebDriver driver =
                    baseClass.driver;

            if (driver == null) {
                return null;
            }

            String screenshotDirectory =
                    System.getProperty("user.dir")
                            + File.separator + "target"
                            + File.separator + "ExtentReports"
                            + File.separator + "screenshots";

            File directory =
                    new File(screenshotDirectory);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String timestamp =
                    new SimpleDateFormat(
                            "yyyyMMdd_HHmmss")
                            .format(new Date());

            String screenshotName =
                    result.getMethod()
                            .getMethodName()
                            + "_"
                            + timestamp
                            + ".png";

            String screenshotPath =
                    screenshotDirectory
                            + File.separator
                            + screenshotName;

            File screenshotFile =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(
                                    OutputType.FILE);

            screenshotFile.renameTo(
                    new File(screenshotPath)
            );

            // Relative path from ExtentReport.html
            return "screenshots/"
                    + screenshotName;

        } catch (Exception e) {

            test.get().warning(
                    "Screenshot capture failed: "
                            + e.getMessage()
            );

            return null;
        }
    }
}