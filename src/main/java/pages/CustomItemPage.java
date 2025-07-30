
package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomItemPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private final By customItemsSidebarLink = By.xpath("//span[normalize-space()='Custom Items']");
    private final By newCustomItemButton = By.cssSelector("a[href='http://127.0.0.1:8000/admin/custom-items/create']");
    private final By customObjectSelect = By.id("data.custom_object_id");
    private final By contactSelectDiv = By.cssSelector("div.choices__inner");
    private final By contactSearchInput = By.cssSelector("input.choices__input--cloned");
    private final By identifierInput = By.cssSelector("input[wire\\:model^='data.data.'][type='text']");
    private final By dateInput = By.cssSelector("input[wire\\:model^='data.data.'][type='date']");
    private final By roomTypeSelect = By.cssSelector("select[id^='field_select_']");
    private final By createButton = By.xpath("//button[@type='submit' and .//span[normalize-space()='Create']]");

    
    private final By filterButton = By.cssSelector("button[title='Filter']");
    private final By filterCustomObjectSelect = By.id("tableFilters.custom_object_id.value");
    private final By pageHeader = By.className("fi-header-heading");
    private final By editLink = By.xpath("//a[.//span[normalize-space()='Edit']]");
    private final By saveChangesButton = By.xpath("//button[.//span[normalize-space()='Save changes']]");

    public CustomItemPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void navigateToCustomItems() {
        wait.until(ExpectedConditions.elementToBeClickable(customItemsSidebarLink)).click();
    }

    public void clickNewCustomItemButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newCustomItemButton)).click();
    }

    public void selectCustomObject(String objectName) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(customObjectSelect)));
        select.selectByVisibleText(objectName);
    }

    public void selectContact(String email) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(contactSelectDiv)).click();
        Thread.sleep(1000);
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(contactSearchInput));
        searchInput.sendKeys(email);
        Thread.sleep(1000); // Wait for search results
        searchInput.sendKeys(Keys.ENTER);
    }

    public void enterIdentifier(String identifier) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(identifierInput)).sendKeys(identifier);
    }

    public void enterDate(String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateInput)).sendKeys(date);
    }

    public void selectRoomType(String roomType) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(roomTypeSelect)));
        select.selectByValue(roomType);
    }

    public void clickCreateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }

    
    public void clickFilterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();
    }
    
    
    public void filterByCustomObject(String objectName) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(filterCustomObjectSelect)));
        select.selectByVisibleText(objectName);
    }
    
    
    public void closeFilterMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(pageHeader)).click();
    }
    
    
    public void clickEditLink() {
        wait.until(ExpectedConditions.elementToBeClickable(editLink)).click();
    }
    
    
    public void updateDate(String newDate) {
        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(dateInput));
        dateField.clear();
        dateField.sendKeys(newDate);
    }

    
    public void clickSaveChangesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveChangesButton)).click();
    }
}