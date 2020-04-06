Feature: Vaidate Elements in Practice Page

  Scenario: Validate Radio buttons, Checkboxes and selections
    Given I navigate to "https://learn.letskodeit.com/" url
    When I click on Practice
    Then I select BMW radio button
    And I select Honda from cars dropdown