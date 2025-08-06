# ADD
Feature: Create Contact Custom Field

  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page

  @CreateContactCustomField
  Scenario: Successfully create a new contact custom field
    Given the user navigates to the contact custom fields page
    When the user creates a new contact custom field named "Test Number of Visits" of type "number"
    Then the user should be on the "View Test Number of Visits" page