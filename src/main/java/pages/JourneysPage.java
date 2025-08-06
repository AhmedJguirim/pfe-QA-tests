// ADD
package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JourneysPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private final By journeysSidebarLink = By.xpath("//span[normalize-space()='Journeys']");
    private final By searchInput = By.cssSelector("input[wire\\:model\\.live\\.debounce\\.500ms='tableSearch']");
    private final By viewLink = By.xpath("//a[.//span[normalize-space()='View']]");
    private final By activateButton = By.cssSelector("button[wire\\:click=\"mountAction('activate')\"]");
    private final By confirmButton = By.xpath("//button[.//span[normalize-space()='Confirm']]");
    private final By deactivateButton = By.cssSelector("button[wire\\:click=\"mountAction('deactivate')\"]");

    // ADD
    private final By newJourneyButton = By.xpath("//a[contains(@href, '/admin/journeys/create')]");
    private final By journeyNameInput = By.id("data.name");
    private final By createJourneyButton = By.xpath("//button[.//span[normalize-space()='Create']]");
    private final By editJourneyButton = By.xpath("//a[contains(@href, '/edit')]");
    private final By zoomOutButton = By.id("zoom-out");
    private final By addConnectionButton = By.className("add-connection-button");
    private final By updateContactCard = By.id("update-contact");
    private final By submitNodeButton = By.xpath("//button[normalize-space()='Submit']");
    private final By contactFieldSelect = By.xpath("//select[@id='mountedActionsData.0.contact_field']/parent::div");
    private final By contactFieldInput = By.xpath("//input[@aria-label='Select an option']");
    private final By valueInput = By.cssSelector("input[id^='mountedActionsData.0.value-']");
    private final By submitActionModalButton = By.xpath("//button[@type='submit' and .//span[normalize-space()='Submit']]");
    private final By updateContactNode = By.xpath("//div[contains(text(), \"Update contact 'Test Number of Visits'.\")]");
    private final By addTagCard = By.id("add-tag");
    private final By tagInputField = By.cssSelector("input.choices__input--cloned");
    private final By addTagNode = By.xpath("//div[contains(text(), 'Add 1 tags.')]");
    private final By settingsButton = By.xpath("//button[.//span[normalize-space()='Settings']]");
    private final By triggerSelect = By.xpath("//select[.//option[normalize-space()='When a Contact Gets Added']]");
private final By submitSettingsButton = By.xpath("//button[@type='submit' and .//span[normalize-space()='Submit']]");
    private final By publishButton = By.cssSelector("button[wire\\:click=\"mountAction('publish')\"]");

    private final By editAddTagNodeHeader = By.xpath("//h2[normalize-space()='Edit Add Tag Node']");

     private final By modalScrollableArea = By.xpath("//div[contains(@class, 'fi-modal-window')]/ancestor::div[contains(@class, 'overflow-y-auto')]");

     private final By triggerTagSelect = By.xpath("//select[contains(@id, 'tag_added_id')]");
    private final By useSegmentsRadio = By.xpath("//input[@value='segments']");

    private final By segmentsSelectContainer = By.xpath("//select[contains(@id, 'segments')]/parent::div");
    private final By segmentsSelectInput = By.xpath("//div[@data-field-wrapper][.//span[normalize-space()='Select Segments']]//input[contains(@class, 'choices__input--cloned')]");

    
    public JourneysPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ADD
    public void createJourney(String journeyName) throws InterruptedException {
        navigateToJourneys();
        clickNewJourneyButton();
        enterJourneyName(journeyName);
        clickCreateJourneyButton();
        clickEditJourneyButton();
    }
    
    // ADD
    public void addTagNodeToJourney(String tagName) throws InterruptedException {
        Thread.sleep(5000); // Wait for editor to load
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addConnectionButton)).get(0).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(addTagCard)).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(submitNodeButton)).click();
        Thread.sleep(2000);

        WebElement tagInput = wait.until(ExpectedConditions.visibilityOfElementLocated(tagInputField));
        tagInput.click();
        Thread.sleep(2000);
        tagInput.sendKeys(tagName);
        Thread.sleep(2000);
        tagInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(editAddTagNodeHeader)).click();
        wait.until(ExpectedConditions.elementToBeClickable(submitActionModalButton)).click();
    }
    
    // ADD
    public void configureContactAddedTrigger() throws InterruptedException {
        clickSettingsButton();
        selectTrigger("When a Contact Gets Added");
        clickSubmitSettingsButton();
    }

    // ADD
    public void configureTagAddedTrigger(String triggerTagName) throws InterruptedException {
        clickSettingsButton();
        selectTrigger("When a Tag Gets Added to a Contact");
        Thread.sleep(1000);
        Select triggerTag = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(triggerTagSelect)));
        triggerTag.selectByVisibleText(triggerTagName);
        scrollModalToBottom();
        clickSubmitSettingsButton();
    }

    
    // meant for third journey
    public void configureTagAddedTriggerAndSegmentAudience(String triggerTagName, String segmentName) throws InterruptedException {
        clickSettingsButton();
        selectTrigger("When a Tag Gets Added to a Contact");
        Thread.sleep(1000);
        Select triggerTag = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(triggerTagSelect)));
        triggerTag.selectByVisibleText(triggerTagName);
        scrollModalToBottom();

        wait.until(ExpectedConditions.elementToBeClickable(useSegmentsRadio)).click();
        Thread.sleep(5000);
         wait.until(ExpectedConditions.elementToBeClickable(segmentsSelectContainer)).click();
        WebElement segmentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(segmentsSelectInput));
        Thread.sleep(5000);
        segmentInput.sendKeys(segmentName);
        Thread.sleep(1000);
        segmentInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        clickSubmitSettingsButton();
    }
    
    // ADD
    public void publishAndDeactivateJourney() throws InterruptedException {
        clickPublishButton();
        Thread.sleep(1000);
        deactivateAndConfirm();
        // clickConfirmButton();
    }
    
    // ADD
    public boolean isJourneyInList(String journeyName) throws InterruptedException {
        searchJourney(journeyName);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[normalize-space()='" + journeyName + "']"))).isDisplayed();
    }


    public void configureComplexJourneyNodes() throws InterruptedException {
        Thread.sleep(5000);
        for (int i = 0; i < 3; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(zoomOutButton)).click();
            Thread.sleep(500);
        }

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addConnectionButton)).get(0).click();
        wait.until(ExpectedConditions.elementToBeClickable(updateContactCard)).click();
        wait.until(ExpectedConditions.elementToBeClickable(submitNodeButton)).click();

        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(contactFieldSelect)).click();
        Thread.sleep(1000);
        WebElement contactField = wait.until(ExpectedConditions.visibilityOfElementLocated(contactFieldInput));
        contactField.sendKeys("Test Number of Visits");
        Thread.sleep(1000);
        contactField.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(valueInput)).sendKeys("3");
        wait.until(ExpectedConditions.elementToBeClickable(submitActionModalButton)).click();

        Thread.sleep(3000);

        for (int i = 0; i < 3; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(zoomOutButton)).click();
            Thread.sleep(500);
        }

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addConnectionButton)).get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(addTagCard)).click();
        wait.until(ExpectedConditions.elementToBeClickable(submitNodeButton)).click();
        
        Thread.sleep(2000);

        WebElement tagInput = wait.until(ExpectedConditions.visibilityOfElementLocated(tagInputField));
        Thread.sleep(2000);
        tagInput.sendKeys("qatag1");
        Thread.sleep(2000);
        tagInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(editAddTagNodeHeader)).click();

        wait.until(ExpectedConditions.elementToBeClickable(submitActionModalButton)).click();
    }

    public void navigateToJourneys() {
        wait.until(ExpectedConditions.elementToBeClickable(journeysSidebarLink)).click();
        this.js = (JavascriptExecutor) driver;
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

    // ADD
    public void clickNewJourneyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newJourneyButton)).click();
    }

    // ADD
    public void enterJourneyName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(journeyNameInput)).sendKeys(name);
    }

    // ADD
    public void clickCreateJourneyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createJourneyButton)).click();
    }

    // ADD
    public void clickEditJourneyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editJourneyButton)).click();
    }

    // ADD
    public void configureJourneyNodes() throws InterruptedException {
        Thread.sleep(5000);
        for (int i = 0; i < 3; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(zoomOutButton)).click();
            Thread.sleep(500);
        }

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addConnectionButton)).get(0).click();
        wait.until(ExpectedConditions.elementToBeClickable(updateContactCard)).click();
        wait.until(ExpectedConditions.elementToBeClickable(submitNodeButton)).click();

        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(contactFieldSelect)).click();
        Thread.sleep(1000);
        WebElement contactField = wait.until(ExpectedConditions.visibilityOfElementLocated(contactFieldInput));
        contactField.sendKeys("Test Number of Visits");
        Thread.sleep(1000);
        contactField.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(valueInput)).sendKeys("3");
        wait.until(ExpectedConditions.elementToBeClickable(submitActionModalButton)).click();

        Thread.sleep(3000);

        for (int i = 0; i < 3; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(zoomOutButton)).click();
            Thread.sleep(500);
        }

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addConnectionButton)).get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(addTagCard)).click();
        wait.until(ExpectedConditions.elementToBeClickable(submitNodeButton)).click();
        
        Thread.sleep(2000);

        WebElement tagInput = wait.until(ExpectedConditions.visibilityOfElementLocated(tagInputField));
        Thread.sleep(2000);
        tagInput.sendKeys("qatag1");
        Thread.sleep(2000);
        tagInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(editAddTagNodeHeader)).click();

        wait.until(ExpectedConditions.elementToBeClickable(submitActionModalButton)).click();
    }

    // ADD
    public boolean isUpdateContactNodeVisible() throws InterruptedException {
        Thread.sleep(3000);
        for (int i = 0; i < 3; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(zoomOutButton)).click();
            Thread.sleep(500);
        }
        return wait.until(ExpectedConditions.visibilityOfElementLocated(updateContactNode)).isDisplayed();
    }

    // ADD
    public boolean isAddTagNodeVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addTagNode)).isDisplayed();
    }

    // ADD
    public void clickSettingsButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(settingsButton)).click();
        Thread.sleep(1000);
    }

    // ADD
    public void selectTrigger(String triggerType) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(triggerSelect)));
        select.selectByVisibleText(triggerType);
                scrollModalToBottom();
        
    }

    // ADD
    public void clickSubmitSettingsButton() {

        wait.until(ExpectedConditions.elementToBeClickable(submitSettingsButton)).click();
    }

    // ADD
    public void clickPublishButton() {
        wait.until(ExpectedConditions.elementToBeClickable(publishButton)).click();
    }
    
    // ADD
    public void deactivateAndConfirm() throws InterruptedException {
        Thread.sleep(10000);
        driver.navigate().refresh();
        clickDeactivateButton();
        clickConfirmButton();
    }
        // ADD
    public void scrollModalToBottom() {
        WebElement modalScroller = wait.until(ExpectedConditions.visibilityOfElementLocated(modalScrollableArea));
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", modalScroller);
    }
}