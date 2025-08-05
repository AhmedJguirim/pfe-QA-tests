// ADD
package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.aventstack.extentreports.Status;
import base.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.JourneysPage;

public class CreateJourneySteps extends TestBase {

    private JourneysPage journeysPage;

    public CreateJourneySteps() {
        super();
        this.journeysPage = new JourneysPage(getDriver());
    }

    @Given("the user creates a new journey named {string}")
    public void the_user_creates_a_new_journey_named(String journeyName) throws InterruptedException {
        try {
            journeysPage.navigateToJourneys();
            journeysPage.clickNewJourneyButton();
            journeysPage.enterJourneyName(journeyName);
            journeysPage.clickCreateJourneyButton();
            journeysPage.clickEditJourneyButton();
            Hooks._Scenario.log(Status.PASS, "Successfully created and navigated to the edit page for journey: " + journeyName);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to create the journey: " + e.getMessage());
            throw e;
        }
    }

    @When("the user configures the journey with an update contact node and a tag node")
    public void the_user_configures_the_journey_with_an_update_contact_node_and_a_tag_node() throws InterruptedException {
        try {
            journeysPage.configureJourneyNodes();
            Hooks._Scenario.log(Status.PASS, "Successfully configured the journey nodes.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to configure journey nodes: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user should see the configured nodes in the journey editor")
    public void the_user_should_see_the_configured_nodes_in_the_journey_editor() throws InterruptedException {
        try {
            assertTrue(journeysPage.isUpdateContactNodeVisible(), "Update contact node is not visible.");
            assertTrue(journeysPage.isAddTagNodeVisible(), "Add tag node is not visible.");
            Hooks._Scenario.log(Status.PASS, "Successfully verified the configured nodes.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify configured nodes: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user sets the journey trigger to {string}")
    public void the_user_sets_the_journey_trigger_to(String triggerType) throws InterruptedException {
        try {
            journeysPage.clickSettingsButton();
            journeysPage.selectTrigger(triggerType);
            journeysPage.clickSubmitSettingsButton();
            Hooks._Scenario.log(Status.PASS, "Successfully set the journey trigger.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to set the journey trigger: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user publishes the journey")
    public void the_user_publishes_the_journey() {
        try {
            journeysPage.clickPublishButton();
            Hooks._Scenario.log(Status.PASS, "Successfully published the journey.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to publish the journey: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user deactivates the journey for cleanup")
    public void the_user_deactivates_the_journey_for_cleanup() throws InterruptedException {
        try {
            journeysPage.deactivateAndConfirm();
            Hooks._Scenario.log(Status.PASS, "Successfully deactivated the journey for cleanup.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to deactivate the journey: " + e.getMessage());
            throw e;
        }
    }
}