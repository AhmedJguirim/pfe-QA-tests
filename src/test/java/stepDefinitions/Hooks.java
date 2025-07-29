package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import ExtentReport.ExtentManager;
import base.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
// This import is not needed and can be removed

public class Hooks {

    static ExtentTest _Scenario;
    static ExtentReports extent = ExtentManager.getInstance();

    @Before
    public static void setUp(Scenario scenario) {
        if (TestBase.getDriver() == null) {
            TestBase.setUp();
        }

        _Scenario = extent.createTest(scenario.getName());
    }

    @After
    public static void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {

            String screenshotPath = TestBase.captureScreenshot(scenario.getName());
            _Scenario.fail(MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            System.out.println("Screenshot saved at: " + screenshotPath);

        }
        extent.flush();
        TestBase.tearDown();
    }
}
