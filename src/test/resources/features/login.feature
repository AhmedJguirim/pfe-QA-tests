Feature: Login Functionality

  @ValidCredentials
  Scenario Outline: Successful login with username
    Given the user is on the login page
    When the user enters a username as "<username>"
    And the user enters a password as "<password>"
    And clicks on the login button
    Then the user should be redirected to the admin page

    Examples:
      | username  | password             |
      | jguirimahmed111@gmail.com | Admin12300 |

  @InvalidCredentials
  Scenario Outline: Failed login with username
    Given the user is on the login page
    When the user enters a username as "<username>"
    And the user enters a password as "<password>"
    And clicks on the login button
    Then the user should see an error message

    Examples:
      | username    | password  |
      | invaliduser@gmail.com | wrongpass |
