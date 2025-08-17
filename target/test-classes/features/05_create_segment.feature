Feature: Create Segment

  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page

  @CreateSegment
  Scenario: Successfully create a new segment with a rule
    Given the user navigates to the segments page
    When the user creates a new segment named "Winter tourist 2025"
    Then the user should be redirected to the edit page for "Winter tourist 2025"
    When the user adds a new rule named "main" with the following conditions:
      | condition type | attribute                      | operator        | value        |
      | Custom Item    | Booking test - Reservation day | is after        | 11-18-2025   |
      | Tags           |                                | has any of      | New lead     |
    And the user publishes the segment
    Then the user should be on the "View Winter tourist 2025" page