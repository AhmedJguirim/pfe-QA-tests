package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.aventstack.extentreports.Status;

import base.TestBase;
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
}