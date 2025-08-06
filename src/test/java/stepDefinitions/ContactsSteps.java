package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.aventstack.extentreports.Status;
import base.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactsPage;

public class ContactsSteps extends TestBase {

    private ContactsPage contactsPage;
    private static int initialContactCount; 

    public ContactsSteps() {
        super();
        this.contactsPage = new ContactsPage(super.getDriver());
    }

    // This is a new step to avoid ambiguity with the other 'Given' step
    @Given("the user is on the contacts page")
    public void the_user_is_on_the_contacts_page() {
        try {
            contactsPage.navigateToContacts();
            Hooks._Scenario.log(Status.PASS, "User is on the Contacts page.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to navigate to Contacts page: " + e.getMessage());
            throw e;
        }
    }

    @When("the user notes the current number of contacts")
    public void the_user_notes_the_current_number_of_contacts() throws InterruptedException {
        try {
            Thread.sleep(1000); 
            initialContactCount = contactsPage.getContactsTotal(); // Assign to the static variable
            Hooks._Scenario.log(Status.INFO, "Initial number of contacts is: " + initialContactCount);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to get initial contact count: " + e.getMessage());
            throw e;
        }
    }

    @When("the user imports new contacts from the file {string}")
    public void the_user_imports_new_contacts_from_the_file(String filePath) throws InterruptedException {
        try {
            contactsPage.clickImportContactsButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'Import Contacts' button.");
            
            contactsPage.uploadContactsFile(filePath);
            Hooks._Scenario.log(Status.PASS, "Provided the file path for upload: " + filePath);

            contactsPage.waitForUploadComplete();
            Hooks._Scenario.log(Status.PASS, "File upload complete message appeared.");

            contactsPage.clickVerifyButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'Verify' button.");
            
            contactsPage.clickFinalImportButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the final 'Import' button to start the process.");

        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed during the import process: " + e.getMessage());
            throw e;
        }
    }

    @Then("the total number of contacts should be increased by {int}")
    public void the_total_number_of_contacts_should_be_increased_by(Integer increaseAmount) throws InterruptedException {
        try {
            Hooks._Scenario.log(Status.INFO, "Waiting for the contacts list to refresh...");
            Thread.sleep(3000); 

            contactsPage.navigateToContacts(); 
            
            Thread.sleep(1000);
            int newContactCount = contactsPage.getContactsTotal();
            Hooks._Scenario.log(Status.INFO, "New number of contacts is: " + newContactCount);

            int expectedCount = initialContactCount + increaseAmount;
            assertEquals(expectedCount, newContactCount, "The contact count did not increase as expected.");
            Hooks._Scenario.log(Status.PASS, "Assertion successful. Contact count increased from " + initialContactCount + " to " + newContactCount);

        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify the new contact count: " + e.getMessage());
            throw e;
        }
    }

    @When("the user deletes the following contacts:")
    public void the_user_deletes_the_following_contacts(DataTable dataTable) throws InterruptedException {
        List<String> emails = dataTable.asList(String.class);
        initialContactCount = contactsPage.getContactsTotal();
        // We skip the header row of the data table which is 'email'
        for (int i = 1; i < emails.size(); i++) {
            String email = emails.get(i);
            try {
                Hooks._Scenario.log(Status.INFO, "Deleting contact: " + email);
                contactsPage.searchForContact(email);
                contactsPage.clickDeleteButtonForVisibleContact();
                contactsPage.clickConfirmDeleteButton();
                // Wait for the UI to refresh after deletion
                Thread.sleep(1000); 
                Hooks._Scenario.log(Status.PASS, "Successfully deleted contact: " + email);
            } catch (Exception e) {
                Hooks._Scenario.log(Status.FAIL, "Failed to delete contact " + email + ": " + e.getMessage());
                throw e;
            }
        }
    }

    @Then("the total number of contacts should be decreased by {int}")
    public void the_total_number_of_contacts_should_return_to_the_initial_count(Integer decreaseAmount) throws InterruptedException {
        try {
            // Navigate back to the main contacts list to ensure the count is accurate
            contactsPage.navigateToContacts();
            Thread.sleep(1000);
            
            int finalCount = contactsPage.getContactsTotal();
            Hooks._Scenario.log(Status.INFO, "Final contact count is: " + finalCount);

            int expectedCount = initialContactCount - decreaseAmount;
            assertEquals(expectedCount, finalCount, "The final contact count does not match the expected count after deletion.");
            Hooks._Scenario.log(Status.PASS, "Assertion successful. Contact count returned to " + expectedCount);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify the final contact count: " + e.getMessage());
            throw e;
        }
    }
}