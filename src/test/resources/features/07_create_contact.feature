Feature: Create Contact

  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page

  @CreateContact
  Scenario: Successfully create a new contact
    Given the user is on the contacts page
    When the user clicks on the new contact button
    And the user enters "qa" as the first name
    And the user enters "tester" as the last name
    And the user enters "qatester@gmail.com" as the email
    And the user clicks the create button
    Then the user should be on the "View qa" page
    When the user adds the "Test Lead" tag back to the contact "qatester@gmail.com"
    Then the user should be on the "Edit qa" page