package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import com.aventstack.extentreports.Status;

import base.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SegmentsPage;

public class SegmentsSteps extends TestBase {

    private SegmentsPage segmentsPage;

    public SegmentsSteps() {
        super();
        this.segmentsPage = new SegmentsPage(getDriver());
    }

    @Given("the user navigates to the segments page")
    public void the_user_navigates_to_the_segments_page() {
        try {
            segmentsPage.navigateToSegments();
            Hooks._Scenario.log(Status.PASS, "User navigated to the Segments page.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to navigate to Segments page: " + e.getMessage());
            throw e;
        }
    }

    @When("the user creates a new segment named {string}")
    public void the_user_creates_a_new_segment_named(String segmentName) {
        try {
            segmentsPage.clickNewSegmentButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'New Segment' button.");
            segmentsPage.enterSegmentName(segmentName);
            Hooks._Scenario.log(Status.PASS, "Entered segment name: " + segmentName);
            segmentsPage.clickCreateButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'Create' button.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to create segment: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user should be redirected to the edit page for {string}")
    public void the_user_should_be_redirected_to_the_edit_page_for(String segmentName) {
        try {
            assertTrue(segmentsPage.isRedirectedToEditPage(segmentName), "User was not redirected to the edit page.");
            Hooks._Scenario.log(Status.PASS, "Successfully redirected to the edit page for segment: " + segmentName);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify redirection to the edit page: " + e.getMessage());
            throw e;
        }
    }

    @When("the user adds a new rule named {string} with the following condition:")
public void the_user_adds_a_new_rule_named_with_the_following_condition(String ruleName, DataTable dataTable) throws InterruptedException  {
    try {
        Map<String, String> condition = dataTable.asMaps().get(0);
        String conditionType = condition.get("condition type");
        String attribute = condition.get("attribute");
        String operator = condition.get("operator");
        String value = condition.get("value");

        segmentsPage.clickCreateRuleButton();
        Hooks._Scenario.log(Status.PASS, "Clicked the 'Create a Rule' button.");
        
        segmentsPage.enterRuleName(ruleName);
        Hooks._Scenario.log(Status.PASS, "Entered rule name: " + ruleName);

        segmentsPage.clickSubmitRuleButton();
        Hooks._Scenario.log(Status.PASS, "Clicked the 'Submit' button.");
        
        segmentsPage.clickAddConditionButton();
        Hooks._Scenario.log(Status.PASS, "Clicked the 'Add Condition' button.");
        
        segmentsPage.selectConditionType(conditionType);
        Hooks._Scenario.log(Status.PASS, "Selected condition type: " + conditionType);
        
        segmentsPage.selectConditionAttribute(attribute);
        Hooks._Scenario.log(Status.PASS, "Selected attribute: " + attribute);
        
        segmentsPage.selectConditionOperator(operator);
        Hooks._Scenario.log(Status.PASS, "Selected operator: " + operator);
        
        segmentsPage.selectConditionValue(value);
        Hooks._Scenario.log(Status.PASS, "Selected value: " + value);
        
        segmentsPage.clickSubmitRuleButton();
        Hooks._Scenario.log(Status.PASS, "Clicked the 'Submit' button.");

    } catch (Exception e) {
        Hooks._Scenario.log(Status.FAIL, "Failed to add the new rule: " + e.getMessage());
        throw e;
    }
}

    @Then("the user should see the new rule condition on the page")
    public void the_user_should_see_the_new_rule_condition_on_the_page() {
        try {
            assertTrue(segmentsPage.isRuleConditionVisible(), "The rule condition was not visible.");
            Hooks._Scenario.log(Status.PASS, "Successfully verified the new rule condition.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify the new rule condition: " + e.getMessage());
            throw e;
        }
    }

    @When("the user publishes the segment")
    public void the_user_publishes_the_segment() {
        try {
            segmentsPage.clickPublishSegmentButton();
            Hooks._Scenario.log(Status.PASS, "Clicked the 'Publish Segment' button.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to publish the segment: " + e.getMessage());
            throw e;
        }
    }

    // @Then("the user should be on the {string} page")
    // public void the_user_should_be_on_the_page(String pageTitle) {
    //     try {
    //         assertTrue(segmentsPage.isOnViewSegmentPage(pageTitle), "User was not on the expected page: " + pageTitle);
    //         Hooks._Scenario.log(Status.PASS, "Successfully verified user is on the '" + pageTitle + "' page.");
    //     } catch (Exception e) {
    //         Hooks._Scenario.log(Status.FAIL, "Failed to verify page redirection: " + e.getMessage());
    //         throw e;
    //     }
    // }
}