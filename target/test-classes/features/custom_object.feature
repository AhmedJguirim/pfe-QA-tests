Feature: Custom Object Management

  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page

  Scenario: Successfully create a new custom object with custom fields
    Given the user navigates to the custom objects page
    When the user creates a new custom object named "Booking test"
    And the user adds the following custom fields:
      | name            | type   | unique | options                   |
      | identifier      | string | true   |                           |
      | Reservation day | date   | false  |                           |
      | Room type       | select | false  | single:Single,suite:Suite |
    Then the user should see 3 custom fields in the list