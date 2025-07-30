
package stepDefinitions;

import com.aventstack.extentreports.Status;
import base.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.CustomItemPage;

public class CustomItemSteps extends TestBase {

    private CustomItemPage customItemPage;

    public CustomItemSteps() {
        super();
        this.customItemPage = new CustomItemPage(getDriver());
    }

    @Given("the user navigates to the custom items page")
    public void the_user_navigates_to_the_custom_items_page() {
        try {
            customItemPage.navigateToCustomItems();
            Hooks._Scenario.log(Status.PASS, "User navigated to the Custom Items page.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to navigate to Custom Items page: " + e.getMessage());
            throw e;
        }
    }

    @When("the user clicks on the new custom item button")
    public void the_user_clicks_on_the_new_custom_item_button() {
        try {
            customItemPage.clickNewCustomItemButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'New Custom Item' button.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to click the 'New Custom Item' button: " + e.getMessage());
            throw e;
        }
    }

    @When("the user selects the {string} custom object")
    public void the_user_selects_the_custom_object(String objectName) {
        try {
            customItemPage.selectCustomObject(objectName);
            Hooks._Scenario.log(Status.PASS, "Selected custom object: " + objectName);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to select custom object: " + e.getMessage());
            throw e;
        }
    }

    @When("the user selects the contact {string}")
    public void the_user_selects_the_contact(String email) throws InterruptedException {
        try {
            customItemPage.selectContact(email);
            Hooks._Scenario.log(Status.PASS, "Selected contact: " + email);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to select contact: " + e.getMessage());
            throw e;
        }
    }

    @When("the user enters {string} as the identifier")
    public void the_user_enters_as_the_identifier(String identifier) {
        try {
            customItemPage.enterIdentifier(identifier);
            Hooks._Scenario.log(Status.PASS, "Entered identifier: " + identifier);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to enter identifier: " + e.getMessage());
            throw e;
        }
    }

    @When("the user enters {string} as the reservation day")
    public void the_user_enters_as_the_reservation_day(String date) {
        try {
            customItemPage.enterDate(date);
            Hooks._Scenario.log(Status.PASS, "Entered date: " + date);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to enter date: " + e.getMessage());
            throw e;
        }
    }

    @When("the user selects {string} as the room type")
    public void the_user_selects_as_the_room_type(String roomType) {
        try {
            customItemPage.selectRoomType(roomType);
            Hooks._Scenario.log(Status.PASS, "Selected room type: " + roomType);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to select room type: " + e.getMessage());
            throw e;
        }
    }

    @When("the user clicks the create button for the custom item")
    public void the_user_clicks_the_create_button_for_the_custom_item() {
        try {
            customItemPage.clickCreateButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'Create' button.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to click the 'Create' button: " + e.getMessage());
            throw e;
        }
    }
}