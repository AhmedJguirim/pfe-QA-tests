package pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SegmentsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private final By segmentsSidebarLink = By.xpath("//span[normalize-space()='Segments']");
    private final By newSegmentButton = By.cssSelector("button[wire\\:click=\"mountAction('create')\"]");
    private final By segmentNameInput = By.cssSelector("input[wire\\:model='mountedActionsData.0.name']");
    private final By createButton = By.xpath("//button[@type='submit' and .//span[normalize-space()='Create']]");
    private final By editPageHeader(String segmentName) {
        return By.xpath("//h1[normalize-space()=\"Edit '" + segmentName + "' Segment\"]");
    }

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

    private final By searchInput = By.cssSelector("input[wire\\:model\\.live\\.debounce\\.500ms='tableSearch']");
    private final By rulesLink = By.xpath("//a[.//span[normalize-space()='Rules']]");
    private final By renameButton = By.cssSelector("button[wire\\:click=\"mountAction('renameSegment')\"]");
    private final By renameInput = By.id("mountedActionsData.0.name");
    private final By renameSubmitButton = By.xpath("//button[@type='submit' and .//span[normalize-space()='Submit']]");
    private final By deleteRuleButton = By.cssSelector("button[wire\\:click='deleteRule']");
    private final By confirmDeleteButton = By.xpath("//button[.//span[normalize-space()='Yes, delete rule']]");
    private final By tagOperatorSelect = By.id("mountedActionsData.0.operator");
    private final By tagValueInput = By.cssSelector("input.choices__input--cloned");
    private final By saveChangesButton = By.cssSelector("button[wire\\:click=\"mountAction('saveChanges')\"]");
    private final By conditionCards = By.cssSelector("div[wire\\:key^='condition-'] .fi-in-text-item > div.text-lg");

    private final By dateValueInput = By.cssSelector("input[type='date']");


    private final By viewLink = By.xpath("//a[.//span[normalize-space()='View']]");
    private final By emptySegmentMessage = By.xpath("//h4[normalize-space()='No contacts']");
    private final By paginationOverview = By.cssSelector("span.fi-pagination-overview");


    public SegmentsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
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
        return wait.until(ExpectedConditions.visibilityOfElementLocated(editPageHeader(segmentName))).isDisplayed();
    }


    public void clickCreateRuleButton() throws InterruptedException {
        Thread.sleep(700);
        wait.until(ExpectedConditions.elementToBeClickable(createRuleButton)).click();
    }

    public void enterRuleName(String name) {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(ruleNameInput));
        nameInput.sendKeys(name);
    }

    public void clickAddConditionButton()  throws InterruptedException{
                Thread.sleep(700);
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

    public void clickPublishSegmentButton() {
        wait.until(ExpectedConditions.elementToBeClickable(publishSegmentButton)).click();
    }

    public boolean isOnViewSegmentPage(String pageTitle) {
        By viewPageHeader = By.xpath("//h1[normalize-space()='" + pageTitle + "']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(viewPageHeader)).isDisplayed();
    }
    
    public void searchSegment(String segmentName) throws InterruptedException {
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        searchField.clear();
        searchField.sendKeys(segmentName);
        Thread.sleep(700); // Wait for livewire debounce
    }

    public void clickRulesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(rulesLink)).click();
    }

    public void clickRenameButton() {
        wait.until(ExpectedConditions.elementToBeClickable(renameButton)).click();
    }

    public void enterNewSegmentName(String newName) {
        WebElement renameField = wait.until(ExpectedConditions.visibilityOfElementLocated(renameInput));
        renameField.clear();
        renameField.sendKeys(newName);
    }

    public void clickRenameSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(renameSubmitButton)).click();
    }

    public void deleteRule() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteRuleButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
    }

    public void selectTagOperator(String operator) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(tagOperatorSelect)));
        select.selectByVisibleText(operator);
    }

    public void enterTagValue(String tag) {
        WebElement tagInput = wait.until(ExpectedConditions.elementToBeClickable(tagValueInput));
        tagInput.sendKeys(tag);
        tagInput.sendKeys(Keys.ENTER);
    }

    public void enterDateValue(String date) {
        WebElement dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(dateValueInput));
        dateInput.sendKeys(date);
    }
    public void clickSaveChangesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveChangesButton)).click();
    }

        public int getConditionCardCount() {
        List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(conditionCards));
        return cards.size();
    }

    
    public void clickViewLink() {
        wait.until(ExpectedConditions.elementToBeClickable(viewLink)).click();
    }

    public void scrollDown() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    
    public boolean isSegmentEmptyMessageVisible() {
        scrollDown();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emptySegmentMessage)).isDisplayed();
    }

    
    public String getPaginationOverviewText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paginationOverview)).getText();
    }


}