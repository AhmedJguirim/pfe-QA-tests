package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.Status;

import base.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CustomObjectPage;

public class CustomObjectSteps extends TestBase {

    private CustomObjectPage customObjectPage;

    public CustomObjectSteps() {
        super();
        this.customObjectPage = new CustomObjectPage(getDriver());
    }

    @Given("the user navigates to the custom objects page")
    public void the_user_navigates_to_the_custom_objects_page() {
        try {
            customObjectPage.navigateToCustomObjects();
            Hooks._Scenario.log(Status.PASS, "User navigated to the Custom Objects page.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to navigate to Custom Objects page: " + e.getMessage());
            throw e;
        }
    }

    @When("the user creates a new custom object named {string}")
    public void the_user_creates_a_new_custom_object_named(String objectName) throws InterruptedException {
        try {
            customObjectPage.clickCreateCustomObject();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'Create Custom Object' button.");
            customObjectPage.enterCustomObjectName(objectName);
            Hooks._Scenario.log(Status.PASS, "Entered custom object name: " + objectName);
            customObjectPage.clickCreate();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'Create' button.");
            Thread.sleep(1000); // Wait for page to load
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to create custom object: " + e.getMessage());
            throw e;
        }
    }

    @When("the user adds the following custom fields:")
    public void the_user_adds_the_following_custom_fields(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String name = row.get("name");
            String type = row.get("type");
            boolean isUnique = Boolean.parseBoolean(row.get("unique"));
            String options = row.get("options");

            try {
                customObjectPage.clickAddCustomField();
                Hooks._Scenario.log(Status.PASS, "Clicked 'Add Custom Field' for field: " + name);
                customObjectPage.enterFieldName(name);
                customObjectPage.selectFieldType(type);
                if (isUnique) {
                    customObjectPage.setUnique();
                }
                if (type.equals("select")) {
                    customObjectPage.addSelectOptions(options);
                }

                customObjectPage.scrollDown();
                customObjectPage.clickCreateField();
                Hooks._Scenario.log(Status.PASS, "Clicked 'Create' for field: " + name);
                Thread.sleep(1000); // Wait for page to load
            } catch (Exception e) {
                Hooks._Scenario.log(Status.FAIL, "Failed to add custom field " + name + ": " + e.getMessage());
                throw e;
            }
        }
    }

    @Then("the user should see {int} custom fields in the list")
    public void the_user_should_see_custom_fields_in_the_list(Integer expectedCount) throws InterruptedException {
       try {
             Thread.sleep(2000); // Wait for list to update
             customObjectPage.scrollDown();
            int actualCount = customObjectPage.getCustomFieldCount();
            assertEquals(expectedCount, actualCount, "The number of custom fields does not match the expected count.");
            Hooks._Scenario.log(Status.PASS, "Successfully verified " + actualCount + " custom fields.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify the number of custom fields: " + e.getMessage());
            throw e;
        }
    
    }
}