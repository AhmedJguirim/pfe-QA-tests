package ExtentReport;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
            String timestamp = dateFormat.format(new Date());
            String reportPath = "target/extent-reports/" + timestamp + ".html";

            // ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath)
                .viewConfigurer()
                .viewOrder()
                .as(new ViewName[] { 
                    ViewName.DASHBOARD, 
                    ViewName.TEST, 
                    ViewName.CATEGORY,
                    ViewName.AUTHOR, 
                    ViewName.DEVICE, 
                    ViewName.EXCEPTION, 
                    ViewName.LOG 
                })
                .apply();
            extent = new ExtentReports();
            extent.attachReporter(reporter);

            // Set system information for the report
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Ahmed Jguirim");

            reporter.config().setTheme(Theme.DARK);
            reporter.config().setDocumentTitle("Test report");

        }
        return extent;
    }
}
