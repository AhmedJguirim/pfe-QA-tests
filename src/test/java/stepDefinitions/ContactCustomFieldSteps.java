// ADD
package stepDefinitions;

import com.aventstack.extentreports.Status;
import base.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.ContactCustomFieldsPage;

public class ContactCustomFieldSteps extends TestBase {

    private ContactCustomFieldsPage contactCustomFieldsPage;

    public ContactCustomFieldSteps() {
        super();
        this.contactCustomFieldsPage = new ContactCustomFieldsPage(getDriver());
    }

    @Given("the user navigates to the contact custom fields page")
    public void the_user_navigates_to_the_contact_custom_fields_page() {
        try {
            contactCustomFieldsPage.navigateToContactCustomFields();
            Hooks._Scenario.log(Status.PASS, "User navigated to the Contact Custom Fields page.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to navigate to Contact Custom Fields page: " + e.getMessage());
            throw e;
        }
    }

    @When("the user creates a new contact custom field named {string} of type {string}")
    public void the_user_creates_a_new_contact_custom_field_named_of_type(String name, String type) {
        try {
            contactCustomFieldsPage.clickNewCustomFieldButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'New Custom Field' button.");

            contactCustomFieldsPage.selectFieldType(type);
            Hooks._Scenario.log(Status.PASS, "Selected type: " + type);
            
            contactCustomFieldsPage.enterName(name);
            Hooks._Scenario.log(Status.PASS, "Entered name: " + name);
            
            contactCustomFieldsPage.clickCreateButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'Create' button.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to create contact custom field: " + e.getMessage());
            throw e;
        }
    }
}