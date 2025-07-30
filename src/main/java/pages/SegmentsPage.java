package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SegmentsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private final By segmentsSidebarLink = By.xpath("//span[normalize-space()='Segments']");
    private final By newSegmentButton = By.cssSelector("button[wire\\:click=\"mountAction('create')\"]");
    private final By segmentNameInput = By.cssSelector("input[wire\\:model='mountedActionsData.0.name']");
    private final By createButton = By.xpath("//button[@type='submit' and .//span[normalize-space()='Create']]");

    public SegmentsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void navigateToSegments() {
        wait.until(ExpectedConditions.elementToBeClickable(segmentsSidebarLink)).click();
    }

    public void clickNewSegmentButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newSegmentButton)).click();
    }

    public void enterSegmentName(String name) {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(segmentNameInput));
        nameInput.sendKeys(name);
    }

    public void clickCreateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }

    public boolean isRedirectedToEditPage(String segmentName) {
        By editPageHeader = By.xpath("//h1[normalize-space()=\"Edit '" + segmentName + "' Segment\"]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(editPageHeader)).isDisplayed();
    }
}