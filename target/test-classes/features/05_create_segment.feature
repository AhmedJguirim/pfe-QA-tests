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
    When the user creates a new segment named "Test Segment"
    Then the user should be redirected to the edit page for "Test Segment"
    When the user adds a new rule named "main rule" with the following conditions:
      | condition type   | attribute | operator | value    |
      | Base Attribute   | birthday  | month is | February |
    Then the user should see the new rule condition on the page
    When the user publishes the segment
    Then the user should be on the "View Test Segment" page