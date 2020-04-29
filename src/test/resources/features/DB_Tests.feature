Feature: DB tests

  #run command: mvn clean install -PTestNGTests -Dcucumber.filter.tags="@dbtest" -Dregion=qa
  #make sure in TestNG_Tests.xml, mention testapp parameter value as db (anything other than web)
  @dbtest
  Scenario: DB Tests sample
    Given I create DB connection
    Then I execute DB query and print results
    Then I validate that db result has count of 5