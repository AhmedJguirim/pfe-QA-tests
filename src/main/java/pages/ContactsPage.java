package pages;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for the elements on the Contacts page
    private final By contactsSidebarLink = By.xpath("//span[normalize-space()='Contacts']");
    private final By paginationOverview = By.cssSelector("span.fi-pagination-overview");
    private final By importContactsButton = By.cssSelector("button[wire\\:click=\"mountAction('importContacts')\"]");
    private final By fileUploadInput = By.cssSelector("input[type='file']");
    private final By uploadCompleteStatus = By.xpath("//span[@class='filepond--file-status-main' and text()='Upload complete']");
    private final By verifyImportButton = By.cssSelector("button[wire\\:click=\"mountAction('verify')\"]");
    private final By finalImportButton = By.cssSelector("button[wire\\:click=\"mountAction('import')\"]");

    /**
     * Constructor for the ContactsPage.
     * @param driver The WebDriver instance.
     */
    public ContactsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time for file operations
    }

    /**
     * Clicks on the 'Contacts' link in the sidebar to navigate to the page.
     */
    public void navigateToContacts() {
        wait.until(ExpectedConditions.elementToBeClickable(contactsSidebarLink)).click();
    }

    /**
     * Gets the total number of contacts from the pagination text.
     * e.g., "Showing 1 to 6 of 6 results" -> returns 6
     * @return The total number of contacts as an integer.
     */
    public int getContactsTotal() {
        WebElement paginationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(paginationOverview));
        String text = paginationElement.getText(); // "Showing 1 to 6 of 6 results"
        
        // Using regex to reliably find the last number in the string
        Pattern pattern = Pattern.compile("(\\d+)(?!.*\\d)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        // Return 0 or throw an exception if the number couldn't be found
        return 0; 
    }
    
    /**
     * Clicks the main 'Import' button to open the import modal.
     */
    public void clickImportContactsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(importContactsButton)).click();
    }

    /**
     * Uploads a file using the hidden file input element.
     * @param filePath The absolute path to the file to be uploaded.
     */
    public void uploadContactsFile(String filePath) {
        // We find the hidden input element and send keys to it directly.
        // We use presenceOfElementLocated because the input might not be visible.
        WebElement uploadElement = wait.until(ExpectedConditions.presenceOfElementLocated(fileUploadInput));
        uploadElement.sendKeys(filePath);
    }

    /**
     * Waits for the "Upload complete" message to appear.
     */
    public void waitForUploadComplete() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(uploadCompleteStatus));
    }

    /**
     * Clicks the 'Verify' button after the file is uploaded.
     */
    public void clickVerifyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(verifyImportButton)).click();
    }
    
    /**
     * Clicks the final 'Import' button to confirm and start the import process.
     */
    public void clickFinalImportButton() {
        wait.until(ExpectedConditions.elementToBeClickable(finalImportButton)).click();
    }
}