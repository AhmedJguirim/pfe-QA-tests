
package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import com.aventstack.extentreports.Status;

import base.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactsPage;
import pages.CustomItemPage;
import pages.SegmentsPage;

public class SegmentAutomationSteps extends TestBase {

    private SegmentsPage segmentsPage;
    private CustomItemPage customItemPage;
    private ContactsPage contactsPage;

    public SegmentAutomationSteps() {
        super();
        this.segmentsPage = new SegmentsPage(getDriver());
        this.customItemPage = new CustomItemPage(getDriver());
        this.contactsPage = new ContactsPage(getDriver());
    }

    @When("the user clicks on the view link for the segment")
    public void the_user_clicks_on_the_view_link_for_the_segment() throws InterruptedException {
        try {
            Thread.sleep(1000);
            segmentsPage.clickViewLink();
            Hooks._Scenario.log(Status.PASS, "Clicked on the 'View' link for the segment.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to click on the 'View' link: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user should see that the segment is empty")
    public void the_user_should_see_that_the_segment_is_empty() {
        try {
            assertTrue(segmentsPage.isSegmentEmptyMessageVisible(), "The segment was not empty.");
            Hooks._Scenario.log(Status.PASS, "Successfully verified that the segment is empty.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify that the segment is empty: " + e.getMessage());
            throw e;
        }
    }

    @When("the user creates a new custom item for {string} with the following details:")
    public void the_user_creates_a_new_custom_item_for_with_the_following_details(String email, DataTable dataTable) throws InterruptedException {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        try {
            customItemPage.navigateToCustomItems();
            customItemPage.clickNewCustomItemButton();
            customItemPage.selectCustomObject(data.get("custom object"));
            customItemPage.selectContact(email);
            customItemPage.enterIdentifier(data.get("identifier"));
            customItemPage.enterDate(data.get("reservation day"));
            customItemPage.selectRoomType(data.get("room type"));
            customItemPage.clickCreateButton();
            Hooks._Scenario.log(Status.PASS, "Successfully created a new custom item.");
            Thread.sleep(1000);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to create the custom item: " + e.getMessage());
            throw e;
        }
    }

    @When("the user navigates back to the {string} segment page")
    public void the_user_navigates_back_to_the_segment_page(String segmentName) throws InterruptedException {
        try {
            segmentsPage.navigateToSegments();
            segmentsPage.searchSegment(segmentName);
            Thread.sleep(1000);
            segmentsPage.clickViewLink();
            Hooks._Scenario.log(Status.PASS, "Navigated back to the segment page: " + segmentName);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to navigate back to the segment page: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user should still see that the segment is empty")
    public void the_user_should_still_see_that_the_segment_is_empty() {
        try {
            assertTrue(segmentsPage.isSegmentEmptyMessageVisible(), "The segment was not empty.");
            Hooks._Scenario.log(Status.PASS, "Successfully verified that the segment is still empty.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify that the segment is still empty: " + e.getMessage());
            throw e;
        }
    }

    @When("the user updates the custom item for {string} with a new date {string}")
    public void the_user_updates_the_custom_item_for_with_a_new_date(String email, String newDate) throws InterruptedException {
        try {
            customItemPage.navigateToCustomItems();
            customItemPage.clickFilterButton();
            customItemPage.filterByCustomObject("Booking test");
            customItemPage.closeFilterMenu();
            Thread.sleep(1000);
            customItemPage.clickEditLink();
            customItemPage.updateDate(newDate);
            customItemPage.clickSaveChangesButton();
            Hooks._Scenario.log(Status.PASS, "Successfully updated the custom item.");
            Thread.sleep(5000);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to update the custom item: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user should see {int} contact in the segment")
    public void the_user_should_see_contact_in_the_segment(Integer count) throws InterruptedException {
        try {
            String expectedText = "Showing " + count + " result";
            assertTrue(segmentsPage.getPaginationOverviewText().contains(expectedText), "The contact count did not match the expected value.");
            Hooks._Scenario.log(Status.PASS, "Successfully verified that there is " + count + " contact in the segment.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify the contact count in the segment: " + e.getMessage());
            throw e;
        }
    }

    
    @When("the user removes the {string} tag from the contact {string}")
    public void the_user_removes_the_tag_from_the_contact(String tagName, String email) throws InterruptedException {
        try {
            contactsPage.navigateToContacts();
            contactsPage.searchForContact(email);
            Thread.sleep(1000);
            contactsPage.clickEditContactButton();
            contactsPage.removeTag(tagName);
            contactsPage.clickSaveChangesButton();
            Hooks._Scenario.log(Status.PASS, "Successfully removed the tag '" + tagName + "' from the contact.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to remove the tag from the contact: " + e.getMessage());
            throw e;
        }
    }

    
    @Then("the user should see that the segment is empty again")
    public void the_user_should_see_that_the_segment_is_empty_again() throws InterruptedException  {
        Thread.sleep(5000);
        try {
            assertTrue(segmentsPage.isSegmentEmptyMessageVisible(), "The segment was not empty.");
            Hooks._Scenario.log(Status.PASS, "Successfully verified that the segment is empty again.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify that the segment is empty again: " + e.getMessage());
            throw e;
        }
    }

    @When("the user deletes the custom item for {string}")
    public void the_user_deletes_the_custom_item_for(String email) throws InterruptedException {
        try {
            customItemPage.navigateToCustomItems();
            customItemPage.clickFilterButton();
            customItemPage.filterByCustomObject("Booking test");
            customItemPage.closeFilterMenu();
            Thread.sleep(1000);
            customItemPage.clickDeleteButton();
            customItemPage.clickConfirmDeleteButton();
            Thread.sleep(1000);
            Hooks._Scenario.log(Status.PASS, "Successfully deleted the custom item for: " + email);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to delete the custom item: " + e.getMessage());
            throw e;
        }
    }

    // ADD
    @When("the user adds the {string} tag back to the contact {string}")
    public void the_user_adds_the_tag_back_to_the_contact(String tagName, String email) throws InterruptedException {
        try {
            contactsPage.navigateToContacts();
            contactsPage.searchForContact(email);
            Thread.sleep(1000);
            contactsPage.clickEditContactButton();
            contactsPage.addTag(tagName);
            contactsPage.clickSaveChangesButton();
            Hooks._Scenario.log(Status.PASS, "Successfully added the tag '" + tagName + "' back to the contact.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to add the tag back to the contact: " + e.getMessage());
            throw e;
        }
    }
}