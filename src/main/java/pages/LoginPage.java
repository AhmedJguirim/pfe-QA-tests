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
        driver.get("http://127.0.0.1:8000/");
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

    public boolean checkSuccessUrl() {
        // Wait until the URL contains '/admin'
        return wait.until(ExpectedConditions.urlContains("/admin"));
    }

    public String getErrorMessage() {
        // This selector finds the <p> tag with the specific error message class
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.fi-fo-field-wrp-error-message")));
        return errorMessageElement.getText();
    }

}
