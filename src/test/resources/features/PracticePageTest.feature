Feature: Validate Elements in Practice Page

  @homepage
  Scenario: Validate home page landing
    Given I navigate to "https://learn.letskodeit.com/" url
    Given I am on home page
    Then I validate enrollNow is displayed
   # And I validate home image source is "https://process.fs.teachablecdn.com/ADNupMnWyR7kCWRvm76Laz/resize=height:60/https://www.filepicker.io/api/file/WSundQ6BT6u2OZe2kcNA"
    And I validate home image source is "https://process.fs.teachablecdn.com"

  @practicepage
  Scenario: Validate Radio buttons, Checkboxes and selections
    Given I navigate to "https://learn.letskodeit.com/" url
    Then I am on home page
    When I click on Practice
    And I wait for 5000 milliseconds
    Then I am on practice page
    Then I select BMW radio button
    And I select honda from cars dropdown

    @signuppage
    Scenario: Validate sign up page
      Given I navigate to "https://learn.letskodeit.com/" url
      Then I am on home page
      Then I click on enrollNow
      Then I am on signup page
      Then I wait for 5000 milliseconds
      Given I enter user email as "some@email.com"
      Then I enter user name as "test user"
      Then I enter user password as "somepass"
      Then I agree terms and marketing
      Then I verify sign up is "enabled"
      

