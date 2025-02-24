Feature: This feature file will keep updated your profile on naukri

  @update
  Scenario: Update naukri profile through playWright
    Given user enter username and password and click login
#    And user updates Resume
    Then user updates skill set
