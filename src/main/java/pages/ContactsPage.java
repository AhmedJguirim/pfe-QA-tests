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

    private final By contactsSidebarLink = By.xpath("//span[normalize-space()='Contacts']");
    private final By paginationOverview = By.cssSelector("span.fi-pagination-overview");
    private final By importContactsButton = By.cssSelector("button[wire\\:click=\"mountAction('importContacts')\"]");
    private final By fileUploadInput = By.cssSelector("input[type='file']");
    private final By uploadCompleteStatus = By.xpath("//span[@class='filepond--file-status-main' and text()='Upload complete']");
    private final By verifyImportButton = By.cssSelector("button[wire\\:click=\"mountAction('verify')\"]");
    private final By finalImportButton = By.cssSelector("button[wire\\:click=\"mountAction('import')\"]");

    private final By searchInput = By.cssSelector("input[wire\\:model\\.live\\.debounce\\.500ms='tableSearch']");

    // This smart XPath finds the delete button even with a dynamic ID
    private final By deleteButtonForVisibleContact = By.xpath("//button[.//span[normalize-space()='Delete']]");
    // This finds the confirmation button in the modal, assuming its text is 'Confirm'
    private final By confirmDeleteButton = By.xpath("//button/span[normalize-space()='Confirm']");


    public ContactsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    public void navigateToContacts() {
        wait.until(ExpectedConditions.elementToBeClickable(contactsSidebarLink)).click();
    }

    public int getContactsTotal() {
        WebElement paginationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(paginationOverview));
        String text = paginationElement.getText();
        Pattern pattern = Pattern.compile("(\\d+)(?!.*\\d)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }
    
    public void clickImportContactsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(importContactsButton)).click();
    }

    public void uploadContactsFile(String filePath) {
        WebElement uploadElement = wait.until(ExpectedConditions.presenceOfElementLocated(fileUploadInput));
        uploadElement.sendKeys(filePath);
    }

    public void waitForUploadComplete() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(uploadCompleteStatus));
    }

    public void clickVerifyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(verifyImportButton)).click();
    }
    
    public void clickFinalImportButton() {
        wait.until(ExpectedConditions.elementToBeClickable(finalImportButton)).click();
    }
    

    /**
     * Searches for a contact by typing their email into the search bar.
     * @param email The email to search for.
     */
    public void searchForContact(String email) throws InterruptedException {
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        searchField.clear();
        searchField.sendKeys(email);
        // Wait for the livewire debounce (500ms) + a buffer to update results
        Thread.sleep(700); 
    }

    /**
     * Clicks the 'Delete' button for the contact currently visible after a search.
     */
    public void clickDeleteButtonForVisibleContact() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButtonForVisibleContact)).click();
    }

    /**
     * Clicks the 'Confirm' button in the delete confirmation modal.
     */
    public void clickConfirmDeleteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
    }
}