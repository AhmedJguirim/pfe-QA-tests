
package stepDefinitions;

import java.util.List;
import com.aventstack.extentreports.Status;
import base.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import pages.ContactCustomFieldsPage;
import pages.ContactsPage;
import pages.CustomObjectPage;
import pages.JourneysPage;
import pages.SegmentsPage;
import pages.TagsPage;

public class CleanupSteps extends TestBase {

    private JourneysPage journeysPage;
    private SegmentsPage segmentsPage;
    private CustomObjectPage customObjectPage;
    private ContactCustomFieldsPage contactCustomFieldsPage;
    private ContactsPage contactsPage;
    private TagsPage tagsPage;

    public CleanupSteps() {
        super();
        this.journeysPage = new JourneysPage(getDriver());
        this.segmentsPage = new SegmentsPage(getDriver());
        this.customObjectPage = new CustomObjectPage(getDriver());
        this.contactCustomFieldsPage = new ContactCustomFieldsPage(getDriver());
        this.contactsPage = new ContactsPage(getDriver());
        this.tagsPage = new TagsPage(getDriver());
    }

    @When("the user deletes the following journeys:")
    public void the_user_deletes_the_following_journeys(DataTable dataTable) throws InterruptedException {
        List<String> journeys = dataTable.asList(String.class);
        for (int i = 1; i < journeys.size(); i++) {
            try {
                journeysPage.deleteJourney(journeys.get(i));
                Hooks._Scenario.log(Status.PASS, "Deleted journey: " + journeys.get(i));
            } catch (Exception e) {
                Hooks._Scenario.log(Status.FAIL, "Failed to delete journey " + journeys.get(i) + ": " + e.getMessage());
            }
        }
    }

    @When("the user deletes the following segments:")
    public void the_user_deletes_the_following_segments(DataTable dataTable) throws InterruptedException {
        List<String> segments = dataTable.asList(String.class);
        for (int i = 1; i < segments.size(); i++) {
            try {
                segmentsPage.deleteSegment(segments.get(i));
                Hooks._Scenario.log(Status.PASS, "Deleted segment: " + segments.get(i));
            } catch (Exception e) {
                Hooks._Scenario.log(Status.FAIL, "Failed to delete segment " + segments.get(i) + ": " + e.getMessage());
            }
        }
    }

    @When("the user deletes the custom object {string}")
    public void the_user_deletes_the_custom_object(String objectName) throws InterruptedException {
        try {
            customObjectPage.deleteCustomObject(objectName);
            Hooks._Scenario.log(Status.PASS, "Deleted custom object: " + objectName);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to delete custom object: " + e.getMessage());
        }
    }

    @When("the user deletes the contact custom field {string}")
    public void the_user_deletes_the_contact_custom_field(String fieldName) throws InterruptedException {
        try {
            contactCustomFieldsPage.deleteCustomField(fieldName);
            Hooks._Scenario.log(Status.PASS, "Deleted contact custom field: " + fieldName);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to delete contact custom field: " + e.getMessage());
        }
    }

    @When("the user deletes the following tags:")
    public void the_user_deletes_the_following_tags(DataTable dataTable) throws InterruptedException {
        tagsPage.navigateToTags();
        List<String> tags = dataTable.asList(String.class);
        for (int i = 1; i < tags.size(); i++) {
            try {
                tagsPage.deleteTag(tags.get(i));
                Hooks._Scenario.log(Status.PASS, "Deleted tag: " + tags.get(i));
            } catch (Exception e) {
                Hooks._Scenario.log(Status.FAIL, "Failed to delete tag " + tags.get(i) + ": " + e.getMessage());
            }
        }
    }
}