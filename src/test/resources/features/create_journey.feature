Feature: Journey Creation

  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page

  @CreateJourney
  Scenario: Successfully create and configure a new journey
    Given the user creates a new journey named "Test Journey"
    When the user configures the journey with an update contact node and a tag node
    Then the user should see the configured nodes in the journey editor
    And the user sets the journey trigger to "When a Contact Gets Added"
    And the user publishes the journey
    And the user deactivates the journey for cleanup