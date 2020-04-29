Feature: validate using common step definitions

  @regression @commonhomepage
  Scenario: for home page
    Given I am on the HomePage page
    Then I validate enrollNow is displayed
    Then I wait for homeImg to be visible
    Then I validate homeImg src is "https://process.fs.teachablecdn.com"

  @regression @commonpracticepage
  Scenario: for practice page
    Given I am on the HomePage page
    When I click on practiceBtn
    Then I am on the PracticePage page
    Then I wait for bmwRadio to be clickable
    Then I click on bmwRadio
    Then I select honda from dropDn dropdown
    Then I select apple from multiSel multiselect
    Then I select peach from multiSel multiselect
    Then I wait for 10000 milliseconds

  @regression @hoverExample @single
  Scenario: for practice page hover
    Given I am on the HomePage page
    When I click on practiceBtn
    Then I am on the PracticePage page
    Then I wait for mouseHover to be visible
    Then I hover on mouseHover and then validate text for hover1 to match with "Top"
    Then I wait for 5000 milliseconds

  @regression @waitexample
  Scenario: for practice page wait
    Given I am on the HomePage page
    When I click on practiceBtn
    Then I am on the PracticePage page
    Then I wait for userName to be visible

  @regression @switchtochildexample
  Scenario: for practice page switch to child window
    Given I am on the HomePage page
    When I click on practiceBtn
    Then I am on the PracticePage page
    Then I wait for openWindow to be clickable
    Then I click on openWindow
    Then I wait for 5000 milliseconds
    And I switch to child window
    Then I am on the CourseSearchPage page
    Then I validate courses has 13 elements and print src

  @regression @commonsignuppage
  Scenario: Validate sign up page
    Given I am on the HomePage page
    Then I click on enrollNow
    Then I am on the SignUpPage page
    Then I wait for userEmail to be visible
    Given I enter "some@email.com" in userEmail
    Given I enter "testing user" in userName
    Given I enter "testPwd" in userPwd
    Given I enter "testPwd" in confirmPwd
    Then I click on agreeMarketing
    Then I click on agreeTerms
    Then I wait for signUp to be visible
    Then I validate signUp is enabled

  @regression @coursesearchpage
  Scenario: Validate course search page iframe sample
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
    Then I wait for 5000 milliseconds
    Then I validate courses has 3 elements and print src
    Then I switch out of iframe
    Then I am on the PracticePage page
    And I enter "test name" in enterNm
    Then I wait for 5000 milliseconds

  @regression @contextexample
  Scenario: for course search page cache
    Given I am on the HomePage page
    Then I store courseHeader text in "coursekey"
    When I click on practiceBtn
    Then I am on the PracticePage page
    Then I wait for iframeExample to be visible
    Then I switch to iframeExample iframe
    Then I am on the CourseSearchPage page
    Then I click on courseHomeImg
    Then I wait for courseHeader to be visible
    Then I validate courseHeader text with "coursekey"

  @regression @getcontextvalue
  Scenario: Validate sign up page get from context
    Given I am on the HomePage page
    Then I click on enrollNow
    Then I am on the SignUpPage page
    Then I wait for userName to be visible
    Given I enter "userName" in userName
    Then I wait for userName to be visible
    Given I enter "testuser" in userName
    Then I wait for userName to be visible
    Given I enter "actual user" in userName
    Then I wait for 10000 milliseconds