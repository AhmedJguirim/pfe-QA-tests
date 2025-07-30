package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.Status;

import base.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SegmentsPage;

public class ManageSegmentSteps extends TestBase {

    private SegmentsPage segmentsPage;

    public ManageSegmentSteps() {
        super();
        this.segmentsPage = new SegmentsPage(getDriver());
    }

    @When("the user searches for the segment {string}")
    public void the_user_searches_for_the_segment(String segmentName) throws InterruptedException {
        try {
            segmentsPage.searchSegment(segmentName);
            Hooks._Scenario.log(Status.PASS, "Searched for segment: " + segmentName);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to search for segment: " + e.getMessage());
            throw e;
        }
    }

    @When("the user clicks on the rules link for the segment")
    public void the_user_clicks_on_the_rules_link_for_the_segment() {
        try {
            segmentsPage.clickRulesLink();
            Hooks._Scenario.log(Status.PASS, "Clicked on the 'Rules' link.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to click on the 'Rules' link: " + e.getMessage());
            throw e;
        }
    }

    @When("the user renames the segment to {string}")
    public void the_user_renames_the_segment_to(String newName) {
        try {
            segmentsPage.clickRenameButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'Rename' button.");
            segmentsPage.enterNewSegmentName(newName);
            Hooks._Scenario.log(Status.PASS, "Entered new segment name: " + newName);
            segmentsPage.clickRenameSubmitButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'Submit' button to rename.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to rename the segment: " + e.getMessage());
            throw e;
        }
    }

    @When("the user deletes the existing rule")
    public void the_user_deletes_the_existing_rule() {
        try {
            segmentsPage.deleteRule();
            Hooks._Scenario.log(Status.PASS, "Successfully deleted the existing rule.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to delete the existing rule: " + e.getMessage());
            throw e;
        }
    }

    @When("the user adds a new rule named {string} with the following conditions:")
    public void the_user_adds_a_new_rule_named_with_the_following_conditions(String ruleName, DataTable dataTable) throws InterruptedException  {
        try {
            segmentsPage.clickCreateRuleButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'Create a Rule' button.");
            
            segmentsPage.enterRuleName(ruleName);
            Hooks._Scenario.log(Status.PASS, "Entered rule name: " + ruleName);

            segmentsPage.clickSubmitRuleButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'Submit' button.");
            
            List<Map<String, String>> conditions = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> condition : conditions) {
                segmentsPage.clickAddConditionButton();
                Hooks._Scenario.log(Status.PASS, "Clicked the 'Add Condition' button.");
                
                String conditionType = condition.get("condition type");
                segmentsPage.selectConditionType(conditionType);
                Hooks._Scenario.log(Status.PASS, "Selected condition type: " + conditionType);
                
                if (conditionType.equals("Custom Item")) {
                    segmentsPage.selectConditionAttribute(condition.get("attribute"));
                    Hooks._Scenario.log(Status.PASS, "Selected attribute: " + condition.get("attribute"));
                    segmentsPage.selectConditionOperator(condition.get("operator"));
                    Hooks._Scenario.log(Status.PASS, "Selected operator: " + condition.get("operator"));
                    segmentsPage.enterDateValue(condition.get("value"));
                    Hooks._Scenario.log(Status.PASS, "Entered value: " + condition.get("value"));
                } else if (conditionType.equals("Tags")) {
                    segmentsPage.selectTagOperator(condition.get("operator"));
                    Hooks._Scenario.log(Status.PASS, "Selected operator: " + condition.get("operator"));
                    segmentsPage.enterTagValue(condition.get("value"));
                    Hooks._Scenario.log(Status.PASS, "Entered value: " + condition.get("value"));
                }
                
                segmentsPage.clickSubmitRuleButton();
                Hooks._Scenario.log(Status.PASS, "Clicked the 'Submit' button.");
            }
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to add the new rule with conditions: " + e.getMessage());
            throw e;
        }
    }

    @And("the user saves the changes")
    public void the_user_saves_the_changes() {
        try {
            segmentsPage.clickSaveChangesButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'Save Changes' button.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to save changes: " + e.getMessage());
            throw e;
        }
    }

   @Then("the user should see {int} condition cards")
    public void the_user_should_see_condition_cards(Integer expectedCount) {
        try {
            int actualCount = segmentsPage.getConditionCardCount();
            assertEquals(expectedCount, actualCount, "The number of condition cards does not match the expected count.");
            Hooks._Scenario.log(Status.PASS, "Successfully verified that there are " + actualCount + " condition cards.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify the number of condition cards: " + e.getMessage());
            throw e;
        }
    }
}