Feature: Tag Management
  Background: User is logged in
    Given the user is on the login page
    When the user enters a username as "jguirimahmed111@gmail.com"
    And the user enters a password as "Admin12300"
    And clicks on the login button
    Then the user should be redirected to the admin page

  @Tags
  Scenario Outline: Successfully create new tags
    Given the user navigates to the tags page
    When the user creates a new tag named "<tagName>"
    Then the user should be on the "View <tagName>" page
    Examples:
      | tagName  |
      | qatag1   |
      | qatag2   |
      | qatag3   |