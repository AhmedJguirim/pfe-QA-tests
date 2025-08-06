Feature: Delete imported contacts

  As a user i want to delete contacts

  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page
    
  @DeleteContacts
  Scenario: Delete imported contacts
    Given the user is on the contacts page
    When the user deletes the following contacts:
      | email                      |
      | natalie.cruz@example.com   |
      | omar.benali@example.com    |
      | priya.mehta@example.com    |
      | quentin.dupre@example.com  |
      | rania.zaki@example.com     |
    Then the total number of contacts should be decreased by 5