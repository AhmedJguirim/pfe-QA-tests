Feature: Cleanup After Tests

  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page

  @Cleanup
  Scenario: Delete all created entities to restore the application state
    When the user deletes the following journeys:
      | name |
      | QA1  |
      | QA2  |
      | QA3  |
    And the user deletes the following segments:
      | name            |
      | qa test segment |
    And the user deletes the custom object "Booking test"
    And the user deletes the contact custom field "Test Number of Visits"
    And the user deletes the following tags:
      | name      |
      | qatag1    |
      | qatag2    |
      | qatag3    |
      | Test Lead |
    And the user deletes the following contacts:
      | email                  |
      | journey.test@gmail.com |
      | qatester@gmail.com     |