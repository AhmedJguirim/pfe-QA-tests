package stepDefinitions;

import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.Status;

import base.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.TagsPage;

public class TagsSteps extends TestBase {
    private TagsPage tagsPage;

    public TagsSteps() {
        super();
        this.tagsPage = new TagsPage(getDriver());
    }

    @Given("the user navigates to the tags page")
    public void the_user_navigates_to_the_tags_page() throws InterruptedException {
        try {
            tagsPage.navigateToTags();
            Hooks._Scenario.log(Status.PASS, "Navigated to the tags page.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to navigate to the tags page: " + e.getMessage());
            throw e;
        }
    }

    @When("the user creates a new tag named {string}")
    public void the_user_creates_a_new_tag_named(String tagName) throws InterruptedException{
        try {
            tagsPage.clickNewTagButton();
            Hooks._Scenario.log(Status.PASS, "Clicked on the 'New Tag' button.");
            tagsPage.enterTagName(tagName);
            Hooks._Scenario.log(Status.PASS, "Entered tag name: " + tagName);
            tagsPage.clickCreateButton();
            Thread.sleep(4000);
            Hooks._Scenario.log(Status.PASS, "Clicked on the 'Create' button.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to create new tag: " + e.getMessage());
            throw e;
        }
    }

    @When("the user creates the following tags:")
    public void the_user_creates_the_following_tags(DataTable dataTable)  throws InterruptedException {
        List<Map<String, String>> tags = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> tag : tags) {
            String tagName = tag.get("tagName");
            try {
                Thread.sleep(2000);
                tagsPage.navigateToTags();
                Hooks._Scenario.log(Status.PASS, "Navigated to the tags page.");
                Thread.sleep(1000);
                tagsPage.clickNewTagButton();
                Hooks._Scenario.log(Status.PASS, "Clicked on the 'New Tag' button.");
                Thread.sleep(1000);
                tagsPage.enterTagName(tagName);
                Hooks._Scenario.log(Status.PASS, "Entered tag name: " + tagName);
                Thread.sleep(2000);
                tagsPage.clickCreateButton();
                Hooks._Scenario.log(Status.PASS, "Clicked on the 'Create' button.");
                Thread.sleep(4000);
            } catch (Exception e) {
                Hooks._Scenario.log(Status.FAIL, "Failed to create new tag: " + e.getMessage());
                throw e;
            }
        }
    }
}