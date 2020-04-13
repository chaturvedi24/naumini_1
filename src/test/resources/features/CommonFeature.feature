Feature: validate using common step definitions

  @regression @commonhomepage
  Scenario: for home page
    Given I navigate to "https://learn.letskodeit.com/" url
    Given I am on the HomePage page
    Then I validate enrollNow is displayed
    Then I wait for 5000 milliseconds
    Then I validate homeImg src is "https://process.fs.teachablecdn.com"

  @regression @commonpracticepage
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

  @regression @commonsignuppage
  Scenario: Validate sign up page
    Given I navigate to "https://learn.letskodeit.com/" url
    Given I am on the HomePage page
    Then I click on enrollNow
    Then I am on the SignUpPage page
    Then I wait for 5000 milliseconds
    Given I enter "some@email.com" in userEmail
    Given I enter "testing user" in userName
    Given I enter "testPwd" in userPwd
    Given I enter "testPwd" in confirmPwd
    Then I click on agreeMarketing
    Then I click on agreeTerms
    Then I wait for 5000 milliseconds
    Then I validate signUp is enabled

  @regression @coursesearchpage
  Scenario: Validate course search page
    Given I navigate to "https://learn.letskodeit.com/" url
    Given I am on the HomePage page
    When I click on practiceBtn
    Then I wait for 5000 milliseconds
    Then I am on the PracticePage page
    Then I switch to iframeExample iframe
    Then I am on the CourseSearchPage page
    Then I click on category
    Then I click on softWareTesting
    Then I wait for 3000 milliseconds
    Then I validate courses has 6 elements and print src
    Then I click on author
    Then I click on letsKodeIt
    Then I validate courses has 11 elements and print src
    Then I enter "python" in searchCourses
    Then I click on search
    Then I wait for 3000 milliseconds
    Then I validate courses has 5 elements and print src