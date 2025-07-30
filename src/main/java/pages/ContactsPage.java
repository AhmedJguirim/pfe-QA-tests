package pages;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

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

    private final By newContactButton = By.cssSelector("a[href='http://127.0.0.1:8000/admin/contacts/create']");
    private final By firstNameInput = By.id("data.first_name");
    private final By lastNameInput = By.id("data.last_name");
    private final By emailInput = By.id("data.email");
    private final By createButton = By.xpath("//button[@type='submit' and .//span[normalize-space()='Create']]");

    private final By editContactButton = By.xpath("//a[contains(@href, '/edit')]");
    private final By removeTagButton(String tagName) {
        return By.xpath("//div[contains(@class, 'choices__item') and text()='" + tagName + "']/button");
    }
    private final By saveChangesButton = By.xpath("//button[.//span[normalize-space()='Save changes']]");

    private final By tagInput = By.cssSelector("input.choices__input--cloned");


    public ContactsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
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

    public void clickNewContactButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newContactButton)).click();
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput)).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }

    public void clickCreateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }

    // public boolean isOnPage(String pageTitle) {
    //     By pageHeader = By.xpath("//h1[normalize-space()='" + pageTitle + "']");
    //     return wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader)).isDisplayed();
    // }

    public void scrollDown() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clickEditContactButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editContactButton)).click();
    }

    public void removeTag(String tagName) {
        wait.until(ExpectedConditions.elementToBeClickable(removeTagButton(tagName))).click();
    }

    public void clickSaveChangesButton() {
        scrollDown();
        wait.until(ExpectedConditions.elementToBeClickable(saveChangesButton)).click();
    }

    public void addTag(String tagName) throws InterruptedException {
        WebElement tagInputField = wait.until(ExpectedConditions.elementToBeClickable(tagInput));
        tagInputField.click();
        Thread.sleep(1000);
        tagInputField.sendKeys(tagName);
        Thread.sleep(1000);
        tagInputField.sendKeys(Keys.ENTER);
    }

    public boolean isTagVisible(String tagName) {
        By tag = By.xpath("//span[contains(@class, 'fi-badge')]//span[@class='truncate' and normalize-space()='" + tagName + "']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(tag)).isDisplayed();
    }
}