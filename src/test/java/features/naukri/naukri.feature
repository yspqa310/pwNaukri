@naukri
Feature:update naukri profile using multiple functionalities

  @resume
  Scenario: update resume
    Given user is logs into naukri
    And user updates resume
    Then user log outs

  @delete
  Scenario: delete resume
    Given user is logs into naukri
    And user delete existing resume
    Then user log outs

  @skill
  Scenario:update one skill
    Given user is logs into naukri
    And user updates skill set
    Then user log outs


