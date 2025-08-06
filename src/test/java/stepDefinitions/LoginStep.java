package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.aventstack.extentreports.Status;

import base.TestBase;
import dev.failsafe.internal.util.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginStep extends TestBase {
    private LoginPage loginPage;

    public LoginStep() {
        super();
        this.loginPage = new LoginPage(super.getDriver());
    }

    // @Test
    // public void testLoginSuccess() {

    // }

    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        try {
            loginPage.openLoginPage();
            Hooks._Scenario.log(Status.PASS, "The user is on the login page");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to open login page");
            Hooks._Scenario.log(Status.FAIL, "" + e.getMessage());
            throw e;
        }
    }

    @When("the user enters a username as {string}")
    public void userEntersUsername(String username) {
        try {
            loginPage.enterUsername(username);
            Hooks._Scenario.log(Status.PASS, "The user enters a username as " + username);
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to enter username");
            Hooks._Scenario.log(Status.FAIL, "" + e.getMessage());
            throw e;
        }
    }

    @And("the user enters a password as {string}")
    public void userEntersPassword(String password) {
        try {
            loginPage.enterPassword(password);
            Hooks._Scenario.log(Status.PASS, "The user enters a password");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "The user enters a password");
            Hooks._Scenario.log(Status.FAIL, "" + e.getMessage());
            throw e;
        }
    }

    @And("clicks on the login button")
    public void userClicksLoginButton(){

        try {
            loginPage.submitLogin();
            Hooks._Scenario.log(Status.PASS, "The user clicks on the login button");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to click on the login button");
            Hooks._Scenario.log(Status.FAIL, "" + e.getMessage());
            throw e;
        }
    }

    @Then("the user should be redirected to the admin page")
    public void userShouldBeRedirectedToAdminPage() {
        try {
            boolean isSuccess = loginPage.checkSuccessUrl();
            assertTrue(isSuccess, "User was not redirected to the admin page.");
            Hooks._Scenario.log(Status.PASS, "User successfully redirected to the admin page.");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to verify successful login: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user should see an error message")
    public void userShouldSeeErrorMessage() {
        try {
            String errorMessage = loginPage.getErrorMessage();
            String expectedMessage = "These credentials do not match our records.";
            assertTrue(errorMessage.contains(expectedMessage), "Error message was not correct. Found: " + errorMessage);
            Hooks._Scenario.log(Status.PASS, "The user correctly saw the error message: '" + errorMessage + "'");
        } catch (Exception e) {
            Hooks._Scenario.log(Status.FAIL, "Failed to see an error message: " + e.getMessage());
            throw e;
        }
    }
}
