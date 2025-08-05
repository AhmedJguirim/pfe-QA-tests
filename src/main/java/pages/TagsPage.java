package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TagsPage {
private WebDriver driver;
private WebDriverWait wait;

private final By tagsSidebarLink = By.xpath("//span[normalize-space()='Tags']");
private final By newTagButton = By.cssSelector("a[href='http://127.0.0.1:8000/admin/tags/create']");
private final By tagNameInput = By.id("data.name");
private final By createButton = By.xpath("//button[.//span[normalize-space()='Create']]");
private final By searchInput = By.cssSelector("input[wire\\:model\\.live\\.debounce\\.500ms='tableSearch']");
private final By searchResult = By.className("fi-pagination-overview");

    public TagsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void navigateToTags(){
                
        wait.until(ExpectedConditions.elementToBeClickable(tagsSidebarLink)).click();
    }

    public void clickNewTagButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newTagButton)).click();
    }

    public void enterTagName(String tagName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagNameInput)).sendKeys(tagName);
    }

    public void clickCreateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }

    public void searchForTag(String tagName) throws InterruptedException {
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        searchField.clear();
        searchField.sendKeys(tagName);
        Thread.sleep(1000);
    }

    public String getSearchResult() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchResult)).getText();
    }
}
