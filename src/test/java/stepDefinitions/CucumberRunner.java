package stepDefinitions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "stepDefinitions", 
    // tags = "@CreateContact or @VerifySegmentAutomation or @SetupJourneyAudience or @CreateJourney or @Journeys",
    tags = "@CreateJourney or @Journeys",
    // tags = "not @Cleanup",
    // tags = "@Cleanup",
    plugin = { "pretty",
                    "html:target/cucumber-reports"
})
public class CucumberRunner {

}
