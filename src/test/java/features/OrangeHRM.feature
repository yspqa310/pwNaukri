Feature: verify OrangeHRM login with playwright

  @flipkart
  Scenario Outline: verify OrangeHRM login functionality
    Given user is on the Login page
    And user login with "<username>" and "<password>"
    Then user click on logout
    Examples:
      | username | password |
      | Admin    | admin123 |

    @window
    Scenario Outline: verify OrangeHRM login functionality
      Given user is on the Login page
      And window open close