package pages;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomObjectPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // Locators
    private final By customObjectsSidebarLink = By.xpath("//span[normalize-space()='Custom Objects']");
    private final By createCustomObjectButton = By.cssSelector("button[wire\\:click=\"mountAction('create')\"]");
    private final By customObjectNameInput = By.cssSelector("input[wire\\:model='mountedActionsData.0.name']");
    private final By createButton = By.xpath("//button[@type='submit' and .//span[normalize-space()='Create']]");
    private final By addCustomFieldButton = By.cssSelector("button[wire\\:click=\"mountTableAction('create')\"]");
    private final By fieldNameInput = By.id("mountedTableActionsData.0.name");
    private final By fieldTypeSelect = By.id("mountedTableActionsData.0.field_type");
    private final By uniqueToggle = By.id("mountedTableActionsData.0.unique");
    private final By createAndCreateAnotherButton = By.xpath("//button[.//span[normalize-space()='Create & create another']]");
    private final By createFieldButton = By.xpath("//button[.//span[normalize-space()='Create']]");
    private final By addOptionButton = By.cssSelector("button[wire\\:click=\"mountFormComponentAction('mountedTableActionsData.0.params.options', 'add')\"]");
    private final By paginationOverview = By.cssSelector("span.fi-pagination-overview");

    public CustomObjectPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }

    public void navigateToCustomObjects() {
        wait.until(ExpectedConditions.elementToBeClickable(customObjectsSidebarLink)).click();
    }

    public void clickCreateCustomObject() {
        wait.until(ExpectedConditions.elementToBeClickable(createCustomObjectButton)).click();
    }

    public void enterCustomObjectName(String name) {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(customObjectNameInput));
        nameInput.sendKeys(name);
    }

    public void clickCreate() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }

    public void clickAddCustomField() {
        wait.until(ExpectedConditions.elementToBeClickable(addCustomFieldButton)).click();
    }

    public void enterFieldName(String name) {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldNameInput));
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void selectFieldType(String type) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(fieldTypeSelect)));
        select.selectByValue(type);
    }

    public void setUnique() {
        wait.until(ExpectedConditions.elementToBeClickable(uniqueToggle)).click();
    }

    public void clickCreateAndCreateAnother() {
        wait.until(ExpectedConditions.elementToBeClickable(createAndCreateAnotherButton)).click();
    }
    
    public void clickCreateField() {
        wait.until(ExpectedConditions.elementToBeClickable(createFieldButton)).click();
    }

    public void addOption() {
        wait.until(ExpectedConditions.elementToBeClickable(addOptionButton)).click();
    }
    
    public void addSelectOptions(String options) {
        String[] pairs = options.split(",");

        // The form starts with one option row. Add more rows if needed.
        for (int i = 1; i < pairs.length; i++) {
            addOption();
            // Wait for the new fields to be added to the DOM
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Find all inputs for options based on the `wire:model` attribute
        List<WebElement> allOptionInputs = driver.findElements(By.cssSelector("input[wire\\:model^='mountedTableActionsData.0.params.options.']"));

        for (int i = 0; i < pairs.length; i++) {
            String[] option = pairs[i].split(":");
            String value = option[0];
            String label = option[1];

            // The inputs appear in pairs: value, then label.
            // For the i-th option, the inputs are at index 2*i and 2*i + 1
            WebElement valueInput = allOptionInputs.get(i * 2);
            WebElement labelInput = allOptionInputs.get(i * 2 + 1);

            valueInput.clear();
            valueInput.sendKeys(value);
            
            labelInput.clear();
            labelInput.sendKeys(label);
        }
    }
    
    public int getCustomFieldCount() {
        WebElement paginationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(paginationOverview));
        String text = paginationElement.getText();
        Pattern pattern = Pattern.compile("(\\d+)(?!.*\\d)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }

    public void scrollDown() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}