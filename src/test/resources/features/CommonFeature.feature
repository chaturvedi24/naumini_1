Feature: validate using common step definitions

  @commonhomepage
  Scenario: for home page
    Given I navigate to "https://learn.letskodeit.com/" url
    Given I am on the HomePage page
    Then I validate enrollNow is displayed
    Then I wait for 5000 milliseconds
    Then I validate homeImg src is "https://process.fs.teachablecdn.com"

  @commonpracticepage
  Scenario: for practice page
    Given I navigate to "https://learn.letskodeit.com/" url
    Given I am on the HomePage page
    When I click on practiceBtn
    Then I am on the PracticePage page
    Then I wait for 5000 milliseconds
    Then I click on bmwRadio
    Then I select honda from dropDn dropdown
    Then I select apple from multiSel multiselect
    Then I select peach from multiSel multiselect
    Then I wait for 10000 milliseconds
