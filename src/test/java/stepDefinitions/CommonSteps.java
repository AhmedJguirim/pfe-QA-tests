package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import base.TestBase;
import io.cucumber.java.en.Then;

public class CommonSteps extends TestBase {

    
    @Then("the user should be on the {string} page")
    public void the_user_should_be_on_the_page(String pageTitle) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            By pageHeader = By.xpath("//h1[normalize-space()='" + pageTitle + "']");
            boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader)).isDisplayed();
            assertTrue(isVisible, "User was not on the expected page: " + pageTitle);
            Hooks._Scenario.log(Status.PASS, "Successfully verified user is on the '" + pageTitle + "' page.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify page redirection: " + e.getMessage());
            throw e;
        }
    }
}