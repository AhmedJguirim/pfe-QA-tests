Feature: Journey Creation

  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page

  @CreateJourney
  Scenario: Successfully create and configure three distinct journeys
    When the user creates the first journey named "QA1"
    And the user creates the second journey named "QA2" triggered by the tag "qatag1"
    And the user creates the third journey named "QA3" triggered by the tag "qatag2" with the audience "qa test segment"
    Then the user should see "QA1", "QA2", and "QA3" in the journeys list