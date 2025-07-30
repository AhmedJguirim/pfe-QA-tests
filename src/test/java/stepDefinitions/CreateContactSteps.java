package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.aventstack.extentreports.Status;

import base.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactsPage;

public class CreateContactSteps extends TestBase {

    private ContactsPage contactsPage;

    public CreateContactSteps() {
        super();
        this.contactsPage = new ContactsPage(getDriver());
    }

    @When("the user clicks on the new contact button")
    public void the_user_clicks_on_the_new_contact_button() {
        try {
            contactsPage.clickNewContactButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'New Contact' button.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to click the 'New Contact' button: " + e.getMessage());
            throw e;
        }
    }

    @When("the user enters {string} as the first name")
    public void the_user_enters_as_the_first_name(String firstName) {
        try {
            contactsPage.enterFirstName(firstName);
            Hooks._Scenario.log(Status.PASS, "Entered first name: " + firstName);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to enter first name: " + e.getMessage());
            throw e;
        }
    }

    @When("the user enters {string} as the last name")
    public void the_user_enters_as_the_last_name(String lastName) {
        try {
            contactsPage.enterLastName(lastName);
            Hooks._Scenario.log(Status.PASS, "Entered last name: " + lastName);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to enter last name: " + e.getMessage());
            throw e;
        }
    }

    @When("the user enters {string} as the email")
    public void the_user_enters_as_the_email(String email) {
        try {
            contactsPage.enterEmail(email);
            Hooks._Scenario.log(Status.PASS, "Entered email: " + email);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to enter email: " + e.getMessage());
            throw e;
        }
    }

    @When("the user clicks the create button")
    public void the_user_clicks_the_create_button() {
        try {
            contactsPage.scrollDown();
            contactsPage.clickCreateButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'Create' button.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to click the 'Create' button: " + e.getMessage());
            throw e;
        }
    }
    
    // @Then("the user should be on the {string} page")
    // public void the_user_should_be_on_the_page(String pageTitle) {
    //     try {
    //         assertTrue(contactsPage.isOnPage(pageTitle), "User was not on the expected page: " + pageTitle);
    //         Hooks._Scenario.log(Status.PASS, "Successfully verified user is on the '" + pageTitle + "' page.");
    //     } catch (Exception e) {
    //         Hooks._Scenario.log(Status.FAIL, "Failed to verify page redirection: " + e.getMessage());
    //         throw e;
    //     }
    // }
}