// ADD
package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactCustomFieldsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators based on the HTML you provided
    private final By contactCustomFieldsSidebarLink = By.xpath("//span[normalize-space()='Contact Custom Fields']");
    private final By newCustomFieldButton = By.cssSelector("button[wire\\:click=\"mountAction('create')\"]");
    private final By nameInput = By.cssSelector("input[id='mountedActionsData.0.name']");
    private final By fieldTypeSelect = By.cssSelector("select[id='mountedActionsData.0.field_type']");
    private final By createButton = By.xpath("//button[@type='submit' and .//span[normalize-space()='Create']]");

    

    public ContactCustomFieldsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Navigates to the "Contact Custom Fields" page via the sidebar link.
     */
    public void navigateToContactCustomFields() {
        wait.until(ExpectedConditions.elementToBeClickable(contactCustomFieldsSidebarLink)).click();
    }

    /**
     * Clicks the "New custom field" button to open the creation form.
     */
    public void clickNewCustomFieldButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newCustomFieldButton)).click();
    }

    /**
     * Enters the name for the new custom field.
     * @param name The name of the field.
     */
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
    }

    /**
     * Selects the type for the new custom field (e.g., 'number', 'string').
     * @param type The value of the option to select.
     */
    public void selectFieldType(String type) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(fieldTypeSelect)));
        select.selectByValue(type);
    }

    /**
     * Clicks the final "Create" button to submit the form.
     */
    public void clickCreateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }
}