package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SegmentsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // --- Locators for Segment Creation ---
    private final By segmentsSidebarLink = By.xpath("//span[normalize-space()='Segments']");
    private final By newSegmentButton = By.cssSelector("button[wire\\:click=\"mountAction('create')\"]");
    private final By segmentNameInput = By.cssSelector("input[wire\\:model='mountedActionsData.0.name']");
    private final By createButton = By.xpath("//button[@type='submit' and .//span[normalize-space()='Create']]");
    private final By editPageHeader(String segmentName) {
        return By.xpath("//h1[normalize-space()=\"Edit '" + segmentName + "' Segment\"]");
    }

    // --- Locators for Rule Creation ---
    private final By createRuleButton = By.cssSelector("button[wire\\:click=\"mountAction('createRule')\"]");
 private final By ruleNameInput = By.cssSelector("input[wire\\:model\\.live='mountedActionsData.0.name']");
    private final By addConditionButton = By.cssSelector("button[wire\\:click='addCondition']");
    private final By conditionTypeSelect = By.id("mountedActionsData.0.condition_type");
    private final By conditionAttributeSelect = By.id("mountedActionsData.0.field");
private final By conditionOperatorSelect = By.cssSelector("select[wire\\:model\\.live='mountedActionsData.0.operator']");
    private final By conditionValueSelect = By.cssSelector("select[wire\\:model\\.live='mountedActionsData.0.value1']");
    private final By submitRuleButton = By.xpath("//button[@type='submit' and .//span[normalize-space()='Submit']]");
 private final By createdRuleConditionText = By.xpath(
        "//div[contains(@class, 'font-medium') and normalize-space()=\"If the contact 'birthday' month is '02'\"]"
    );

     private final By publishSegmentButton = By.cssSelector("button[wire\\:click=\"mountAction('publish')\"]");


    public SegmentsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // --- Methods for Segment Creation ---

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
        return wait.until(ExpectedConditions.visibilityOfElementLocated(editPageHeader(segmentName))).isDisplayed();
    }

    // --- Methods for Rule Creation ---

    public void clickCreateRuleButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createRuleButton)).click();
    }

    public void enterRuleName(String name) {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(ruleNameInput));
        nameInput.sendKeys(name);
    }

    public void clickAddConditionButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addConditionButton)).click();
    }
    
    public void selectConditionType(String type) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(conditionTypeSelect)));
        select.selectByVisibleText(type);
    }

    public void selectConditionAttribute(String attribute) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(conditionAttributeSelect)));
        select.selectByValue(attribute);
    }

    public void selectConditionOperator(String operator) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(conditionOperatorSelect)));
        select.selectByValue(operator);
    }
    
    public void selectConditionValue(String value) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(conditionValueSelect)));
        select.selectByVisibleText(value);
    }

    public void clickSubmitRuleButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitRuleButton)).click();
    }
    
    public boolean isRuleConditionVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(createdRuleConditionText)).isDisplayed();
    }

     // NEW: Method to click the publish button
    public void clickPublishSegmentButton() {
        wait.until(ExpectedConditions.elementToBeClickable(publishSegmentButton)).click();
    }

    // NEW: Method to verify the final page header
    public boolean isOnViewSegmentPage(String pageTitle) {
        By viewPageHeader = By.xpath("//h1[normalize-space()='" + pageTitle + "']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(viewPageHeader)).isDisplayed();
    }
}