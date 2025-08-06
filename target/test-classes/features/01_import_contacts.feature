Feature: Import contacts

  As a user, I want to import new contacts from a CSV file
  so that I can easily add them to the system.

  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page

  @ImportContacts
  Scenario: Successfully import new contacts from a CSV file
    Given the user is on the contacts page
    When the user notes the current number of contacts
    And the user imports new contacts from the file "C:\Users\AORUS\Documents\trash\tast\new_contacts_with_custom_fields.csv"
    Then the total number of contacts should be increased by 5