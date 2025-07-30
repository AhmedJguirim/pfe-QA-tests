Feature: Custom Item Management

  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page

  @AddCustomItem
  Scenario: Successfully add a custom item to a contact
    Given the user navigates to the custom items page
    When the user clicks on the new custom item button
    And the user selects the "Booking test" custom object
    And the user selects the contact "qatester@gmail.com"
    And the user enters "12345" as the identifier
    And the user enters "11/11/2025" as the reservation day
    And the user selects "single" as the room type
    And the user clicks the create button
    Then the user should be on the "View custom item" page