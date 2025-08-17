package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriverWait wait;
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:8000/");
    }

    public void enterUsername(String username) {
        WebElement usernameField = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("data.email")));
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("data.password")));
        passwordField.sendKeys(password);
    }

    public void submitLogin() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        btn.click();
    }

    public WebElement waitForDashboardHeading() {
        // Define the locator for the h1 element containing the text "Dashboard"
        By dashboardHeadingLocator = By.xpath("//h1[normalize-space()='Dashboard']");
        
        // Wait until the element is visible on the page and return it
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeadingLocator));
    }

    public String getErrorMessage() {
        // This selector finds the <p> tag with the specific error message class
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.fi-fo-field-wrp-error-message")));
        return errorMessageElement.getText();
    }

}
