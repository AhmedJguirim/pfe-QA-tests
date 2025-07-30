// ADD
package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JourneysPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By journeysSidebarLink = By.xpath("//span[normalize-space()='Journeys']");
    private final By searchInput = By.cssSelector("input[wire\\:model\\.live\\.debounce\\.500ms='tableSearch']");
    private final By viewLink = By.xpath("//a[.//span[normalize-space()='View']]");
    private final By activateButton = By.cssSelector("button[wire\\:click=\"mountAction('activate')\"]");
    private final By confirmButton = By.xpath("//button[.//span[normalize-space()='Confirm']]");
    private final By deactivateButton = By.cssSelector("button[wire\\:click=\"mountAction('deactivate')\"]");

    public JourneysPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void navigateToJourneys() {
        wait.until(ExpectedConditions.elementToBeClickable(journeysSidebarLink)).click();
    }

    public void searchJourney(String journeyName) throws InterruptedException {
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        searchField.clear();
        searchField.sendKeys(journeyName);
        Thread.sleep(700); // Wait for livewire debounce
    }

    public void clickViewLink() {
        wait.until(ExpectedConditions.elementToBeClickable(viewLink)).click();
    }

    public void clickActivateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(activateButton)).click();
    }

    public void clickConfirmButton() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }

    public boolean isJourneyPageVisible(String journeyName) {
        By pageHeader = By.xpath("//h1[normalize-space()='" + journeyName + " Customer Journey']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader)).isDisplayed();
    }

    public void clickDeactivateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deactivateButton)).click();
    }
}