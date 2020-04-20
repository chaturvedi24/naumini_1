Feature: Customized test steps

  @regression @exceldata
  Scenario: validate with excel
    Given I navigate to "kodeit.url" url
    Given I am on the HomePage page
    Then I click on enrollNow
    Then I am on the SignUpPage page
    Given I read excel file "InputData" and store
    Then I validate signup page fields using "InputData"

    #below scenario added as example on how to add customized element actions to page object classes itself
  @regression @custompracticeclick
  Scenario: for home page
    Given I navigate to "kodeit.url" url
    Given I am on the HomePage page
    Then I wait for practiceBtn to be clickable
    Then I click practice btn from "Home Page"
    Then I wait for 5000 milliseconds