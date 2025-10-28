package BaseAndCommons;

import StepDefinitions.Hooks;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static BaseAndCommons.BaseClass.driver;
import static StepDefinitions.Hooks.screenshotAsBase64;

public class Listeners implements ITestListener {
   // public static ExtentTest extentTest;

    ExtentReports extent = ExtentReporterTestClass.getReportObject();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
       // ITestListener.super.onTestStart(result);
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest); // important else the test will fail at onTestFailure with nullpointer exception

    }

    @Override
    public void onTestSuccess(ITestResult result) {
       // ITestListener.super.onTestSuccess(result);
        test.get().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
       // ITestListener.super.onTestFailure(result);
     //learn - how we can achive this
     Scenario scenario = ScenarioContext.getScenario();
        try {
            Hooks.captureScreenshotAsBase64(scenario);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.get().log(Status.FAIL,"Test Failed")
                .addScreenCaptureFromBase64String(screenshotAsBase64, "Screenshot on Failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
     //   ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
     //   ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
      //  ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
     //   ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
       // ITestListener.super.onFinish(context);
        extent.flush();
    }
}
