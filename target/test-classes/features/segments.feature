Feature: Segment Management

  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page

  Scenario: Successfully create a new segment
    Given the user navigates to the segments page
    When the user creates a new segment named "Test Segment"
    Then the user should be redirected to the edit page for "Test Segment"