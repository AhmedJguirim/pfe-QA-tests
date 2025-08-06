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

    @When("the user creates the first journey named {string}")
    public void the_user_creates_the_first_journey_named(String journeyName) throws InterruptedException {
        try {
            journeysPage.createJourney(journeyName);
            journeysPage.configureComplexJourneyNodes();
            journeysPage.configureContactAddedTrigger();
            journeysPage.publishAndDeactivateJourney();
            Hooks._Scenario.log(Status.PASS, "Successfully created journey: " + journeyName);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to create journey " + journeyName + ": " + e.getMessage());
            throw e;
        }
    }

    
    @When("the user creates the second journey named {string} triggered by the tag {string}")
    public void the_user_creates_the_second_journey_named_triggered_by_the_tag(String journeyName, String triggerTag) throws InterruptedException {
        try {
            journeysPage.createJourney(journeyName);
            journeysPage.addTagNodeToJourney("qatag2");
            journeysPage.configureTagAddedTrigger(triggerTag);
            journeysPage.publishAndDeactivateJourney();
            Hooks._Scenario.log(Status.PASS, "Successfully created journey: " + journeyName);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to create journey " + journeyName + ": " + e.getMessage());
            throw e;
        }
    }

    //UPDATE
    @When("the user creates the third journey named {string} triggered by the tag {string} with the audience {string}")
    public void the_user_creates_the_third_journey_named_triggered_by_the_tag_with_the_audience(String journeyName, String triggerTag, String segment) throws InterruptedException {
        try {
            journeysPage.createJourney(journeyName);
            journeysPage.addTagNodeToJourney("qatag3");
            journeysPage.configureTagAddedTriggerAndSegmentAudience(triggerTag,segment);
            journeysPage.publishAndDeactivateJourney();
            Hooks._Scenario.log(Status.PASS, "Successfully created journey: " + journeyName);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to create journey " + journeyName + ": " + e.getMessage());
            throw e;
        }
    }

    @Then("the user should see {string}, {string}, and {string} in the journeys list")
    public void the_user_should_see_and_in_the_journeys_list(String journey1, String journey2, String journey3) throws InterruptedException {
        try {
            journeysPage.navigateToJourneys();
            assertTrue(journeysPage.isJourneyInList(journey1), "Journey " + journey1 + " not found.");
            assertTrue(journeysPage.isJourneyInList(journey2), "Journey " + journey2 + " not found.");
            assertTrue(journeysPage.isJourneyInList(journey3), "Journey " + journey3 + " not found.");
            Hooks._Scenario.log(Status.PASS, "Successfully verified all three journeys exist.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify journeys in the list: " + e.getMessage());
            throw e;
        }
    }
    // @When("the user configures the journey with an update contact node and a tag node")
    // public void the_user_configures_the_journey_with_an_update_contact_node_and_a_tag_node() throws InterruptedException {
    //     try {
    //         journeysPage.configureJourneyNodes();
    //         Hooks._Scenario.log(Status.PASS, "Successfully configured the journey nodes.");
    //     } catch (Exception e) {
    //         Hooks._Scenario.log(Status.FAIL, "Failed to configure journey nodes: " + e.getMessage());
    //         throw e;
    //     }
    // }

    // @Then("the user should see the configured nodes in the journey editor")
    // public void the_user_should_see_the_configured_nodes_in_the_journey_editor() throws InterruptedException {
    //     try {
    //         assertTrue(journeysPage.isUpdateContactNodeVisible(), "Update contact node is not visible.");
    //         assertTrue(journeysPage.isAddTagNodeVisible(), "Add tag node is not visible.");
    //         Hooks._Scenario.log(Status.PASS, "Successfully verified the configured nodes.");
    //     } catch (Exception e) {
    //         Hooks._Scenario.log(Status.FAIL, "Failed to verify configured nodes: " + e.getMessage());
    //         throw e;
    //     }
    // }

    // @Then("the user sets the journey trigger to {string}")
    // public void the_user_sets_the_journey_trigger_to(String triggerType) throws InterruptedException {
    //     try {
    //         journeysPage.clickSettingsButton();
    //         journeysPage.selectTrigger(triggerType);
    //         journeysPage.clickSubmitSettingsButton();
    //         Hooks._Scenario.log(Status.PASS, "Successfully set the journey trigger.");
    //     } catch (Exception e) {
    //         Hooks._Scenario.log(Status.FAIL, "Failed to set the journey trigger: " + e.getMessage());
    //         throw e;
    //     }
    // }

    // @Then("the user publishes the journey")
    // public void the_user_publishes_the_journey() {
    //     try {
    //         journeysPage.clickPublishButton();
    //         Hooks._Scenario.log(Status.PASS, "Successfully published the journey.");
    //     } catch (Exception e) {
    //         Hooks._Scenario.log(Status.FAIL, "Failed to publish the journey: " + e.getMessage());
    //         throw e;
    //     }
    // }

    // @Then("the user deactivates the journey for cleanup")
    // public void the_user_deactivates_the_journey_for_cleanup() throws InterruptedException {
    //     try {
    //         journeysPage.deactivateAndConfirm();
    //         Hooks._Scenario.log(Status.PASS, "Successfully deactivated the journey for cleanup.");
    //     } catch (Exception e) {
    //         Hooks._Scenario.log(Status.FAIL, "Failed to deactivate the journey: " + e.getMessage());
    //         throw e;
    //     }
    // }
}