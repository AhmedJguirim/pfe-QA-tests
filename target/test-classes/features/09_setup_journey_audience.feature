Feature: Setup Journey audience
  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page

  @SetupJourneyAudience
  Scenario: Successfully manage an existing segment
    Given the user navigates to the segments page
    When the user searches for the segment "Winter tourist 2025"
    And the user clicks on the rules link for the segment
    And the user renames the segment to "qa test segment"
    And the user deletes the existing rule
    And the user adds a new rule named "main" with the following conditions:
      | condition type          | attribute                      | operator        | value        |
      | Contact Custom Field    | Test Number of Visits          | is greater than | 2            |
      | Tags                    |                                | has any of      | qatag1       |
    And the user saves the changes
    Then the user should see 2 condition cards