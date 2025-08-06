package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
    private static long scenarioDelay;


    static {
    try (FileInputStream fis = new FileInputStream("src/test/resources/cucumber.properties")) {
        Properties properties = new Properties();
        properties.load(fis);
        scenarioDelay = Long.parseLong(properties.getProperty("scenario.delay.ms", "0"));
    } catch (IOException e) {
        scenarioDelay = 0; // Default to 0 if file not found or on error
    }
    }

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

        if (scenarioDelay > 0) {
            try {
                System.out.println("Waiting for " + scenarioDelay + "ms before the next test...");
                Thread.sleep(scenarioDelay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
