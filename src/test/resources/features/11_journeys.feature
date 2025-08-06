Feature: Journeys Automation

  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page

  @Journeys
  Scenario: Verify that journeys are working correctly
    Given the user navigates to the journeys page
    When the user activates the "QA1" journey
    And the user activates the "QA2" journey
    And the user activates the "QA3" journey
    And the user creates a new contact with the following details:
      | firstName | lastName | email                  |
      | Test      | User     | journey.test@gmail.com |
    Then the user should see the tags "qatag1", "qatag2", and "qatag3" for the contact "journey.test@gmail.com"
    When the user deactivates the "QA1" journey
    And the user deactivates the "QA2" journey
    And the user deactivates the "QA3" journey