// ADD
package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Map;
import com.aventstack.extentreports.Status;
import base.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactsPage;
import pages.JourneysPage;

public class JourneysSteps extends TestBase {

    private JourneysPage journeysPage;
    private ContactsPage contactsPage;

    public JourneysSteps() {
        super();
        this.journeysPage = new JourneysPage(getDriver());
        this.contactsPage = new ContactsPage(getDriver());
    }

    @Given("the user navigates to the journeys page")
    public void the_user_navigates_to_the_journeys_page() {
        try {
            journeysPage.navigateToJourneys();
            Hooks._Scenario.log(Status.PASS, "User navigated to the Journeys page.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to navigate to Journeys page: " + e.getMessage());
            throw e;
        }
    }

    @When("the user activates the {string} journey")
    public void the_user_activates_the_journey(String journeyName) throws InterruptedException {
        try {
            journeysPage.navigateToJourneys();
            journeysPage.searchJourney(journeyName);
            journeysPage.clickViewLink();
            assertTrue(journeysPage.isJourneyPageVisible(journeyName), "Not on the correct journey page.");
            journeysPage.clickActivateButton();
            journeysPage.clickConfirmButton();
            Hooks._Scenario.log(Status.PASS, "Successfully activated the journey: " + journeyName);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to activate the journey: " + e.getMessage());
            throw e;
        }
    }

    @When("the user creates a new contact with the following details:")
    public void the_user_creates_a_new_contact_with_the_following_details(DataTable dataTable) {
        try {
            Map<String, String> contactDetails = dataTable.asMaps().get(0);
            contactsPage.navigateToContacts();
            contactsPage.clickNewContactButton();
            contactsPage.enterFirstName(contactDetails.get("firstName"));
            contactsPage.enterLastName(contactDetails.get("lastName"));
            contactsPage.enterEmail(contactDetails.get("email"));
            contactsPage.scrollDown();
            contactsPage.clickCreateButton();
            Hooks._Scenario.log(Status.PASS, "Successfully created a new contact.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to create a new contact: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user should see the tags {string}, {string}, and {string} for the contact {string}")
    public void the_user_should_see_the_tags_for_the_contact(String tag1, String tag2, String tag3, String email) throws InterruptedException {
        try {
            Thread.sleep(40000); // Wait for journeys to run
            getDriver().navigate().refresh();
            // contactsPage.navigateToContacts();
            // contactsPage.searchForContact(email);
            // contactsPage.clickEditContactButton();
            assertTrue(contactsPage.isTagVisible(tag1), "Tag '" + tag1 + "' is not visible.");
            assertTrue(contactsPage.isTagVisible(tag2), "Tag '" + tag2 + "' is not visible.");
            assertTrue(contactsPage.isTagVisible(tag3), "Tag '" + tag3 + "' is not visible.");
            Hooks._Scenario.log(Status.PASS, "Successfully verified the tags for the contact.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify the tags for the contact: " + e.getMessage());
            throw e;
        }
    }

    @When("the user deletes the contact {string}")
    public void the_user_deletes_the_contact(String email) throws InterruptedException {
        try {
            contactsPage.navigateToContacts();
            contactsPage.searchForContact(email);
            contactsPage.clickDeleteButtonForVisibleContact();
            contactsPage.clickConfirmDeleteButton();
            Hooks._Scenario.log(Status.PASS, "Successfully deleted the contact: " + email);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to delete the contact: " + e.getMessage());
            throw e;
        }
    }

    @When("the user deactivates the {string} journey")
    public void the_user_deactivates_the_journey(String journeyName) throws InterruptedException {
        try {
            journeysPage.navigateToJourneys();
            journeysPage.searchJourney(journeyName);
            journeysPage.clickViewLink();
            assertTrue(journeysPage.isJourneyPageVisible(journeyName), "Not on the correct journey page.");
            journeysPage.clickDeactivateButton();
            journeysPage.clickConfirmButton();
            Hooks._Scenario.log(Status.PASS, "Successfully deactivated the journey: " + journeyName);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to deactivate the journey: " + e.getMessage());
            throw e;
        }
    }
}