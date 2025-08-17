
package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.aventstack.extentreports.Status;
import base.TestBase;
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
            Thread.sleep(2000);
            journeysPage.configureComplexJourneyNodes();
            Thread.sleep(2000);
            journeysPage.configureContactAddedTrigger();
            Thread.sleep(2000);
            journeysPage.publishAndDeactivateJourney();
            Hooks._Scenario.log(Status.PASS, "Successfully created journey: " + journeyName);
            Thread.sleep(2000);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to create journey " + journeyName + ": " + e.getMessage());
            throw e;
        }
    }

    
    @When("the user creates the second journey named {string} triggered by the tag {string}")
    public void the_user_creates_the_second_journey_named_triggered_by_the_tag(String journeyName, String triggerTag) throws InterruptedException {
        try {
            journeysPage.createJourney(journeyName);
            Thread.sleep(2000);
            journeysPage.addTagNodeToJourney("qatag2");
            Thread.sleep(2000);
            journeysPage.configureTagAddedTrigger(triggerTag);
            Thread.sleep(2000);
            journeysPage.publishAndDeactivateJourney();
            Hooks._Scenario.log(Status.PASS, "Successfully created journey: " + journeyName);
            Thread.sleep(2000);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to create journey " + journeyName + ": " + e.getMessage());
            throw e;
        }
    }

    
    @When("the user creates the third journey named {string} triggered by the tag {string} with the audience {string}")
    public void the_user_creates_the_third_journey_named_triggered_by_the_tag_with_the_audience(String journeyName, String triggerTag, String segment) throws InterruptedException {
        try {
            journeysPage.createJourney(journeyName);
            Thread.sleep(2000);
            journeysPage.addTagNodeToJourney("qatag3");
            Thread.sleep(2000);
            journeysPage.configureTagAddedTriggerAndSegmentAudience(triggerTag,segment);
            Thread.sleep(2000);
            journeysPage.publishAndDeactivateJourney();
            Hooks._Scenario.log(Status.PASS, "Successfully created journey: " + journeyName);
            Thread.sleep(2000);
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
}