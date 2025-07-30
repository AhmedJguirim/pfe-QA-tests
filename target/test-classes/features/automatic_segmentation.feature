Feature: Automatic Segmentation

  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page

  @VerifySegmentAutomation
  Scenario: Verify automatic segmentation after updating a custom item
    Given the user navigates to the segments page
    When the user searches for the segment "Winter tourist 2025"
    And the user clicks on the view link for the segment
    Then the user should see that the segment is empty
    When the user creates a new custom item for "qatester@gmail.com" with the following details:
      | custom object | identifier | reservation day | room type |
      | Booking test  | 12345      | 11/11/2025      | single    |
    And the user navigates back to the "Winter tourist 2025" segment page
    Then the user should still see that the segment is empty
    When the user updates the custom item for "qatester@gmail.com" with a new date "12/12/2025"
    And the user navigates back to the "Winter tourist 2025" segment page
    Then the user should see 1 contact in the segment
    When the user removes the "New Lead" tag from the contact "qatester@gmail.com"
    And the user navigates back to the "Winter tourist 2025" segment page
    Then the user should see that the segment is empty again