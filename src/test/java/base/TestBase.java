package base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
    private static WebDriver driver;
    private static WebDriverWait wait;
    public static ExtentTest test;
    private static ExtentReports extent;

    @BeforeEach
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void setTest(ExtentTest test) {
        TestBase.test = test;
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static String captureScreenshot(String scenarioName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);

        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

        String screenshotName = scenarioName + "_" + timestamp + ".png";
        String destination = System.getProperty("user.dir") + "/target/screenshots/" + screenshotName;

        try {
            String path = Files.write(Paths.get(destination), screenshot).toString();
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
