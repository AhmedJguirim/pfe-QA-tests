package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.aventstack.extentreports.Status;
import base.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactsPage;

public class ContactsSteps extends TestBase {

    private ContactsPage contactsPage;
    private int initialContactCount; // Variable to store the count between steps

    public ContactsSteps() {
        super();
        this.contactsPage = new ContactsPage(super.getDriver());
    }

    @Given("the user navigates to the contacts page")
    public void the_user_navigates_to_the_contacts_page() {
        try {
            contactsPage.navigateToContacts();
            Hooks._Scenario.log(Status.PASS, "User navigated to the Contacts page.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to navigate to Contacts page: " + e.getMessage());
            throw e;
        }
    }

    @When("the user notes the current number of contacts")
    // UPDATED: Added 'throws InterruptedException' to handle Thread.sleep()
    public void the_user_notes_the_current_number_of_contacts() throws InterruptedException {
        try {
            Thread.sleep(1000); 
            this.initialContactCount = contactsPage.getContactsTotal();
            Hooks._Scenario.log(Status.INFO, "Initial number of contacts is: " + this.initialContactCount);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to get initial contact count: " + e.getMessage());
            throw e;
        }
    }

    @When("the user imports new contacts from the file {string}")
    public void the_user_imports_new_contacts_from_the_file(String filePath) {
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
    // UPDATED: Added 'throws InterruptedException' to handle Thread.sleep()
    public void the_total_number_of_contacts_should_be_increased_by(Integer increaseAmount) throws InterruptedException {
        try {
            Hooks._Scenario.log(Status.INFO, "Waiting for the contacts list to refresh...");
            Thread.sleep(3000); 

            contactsPage.navigateToContacts(); 
            
            Thread.sleep(1000);
            int newContactCount = contactsPage.getContactsTotal();
            Hooks._Scenario.log(Status.INFO, "New number of contacts is: " + newContactCount);

            int expectedCount = this.initialContactCount + increaseAmount;
            assertEquals(expectedCount, newContactCount, "The contact count did not increase as expected.");
            Hooks._Scenario.log(Status.PASS, "Assertion successful. Contact count increased from " + initialContactCount + " to " + newContactCount);

        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify the new contact count: " + e.getMessage());
            throw e;
        }
    }
}