Feature: Rest tests

  @rest @gettest
  Scenario: get test
    Given I store parameters in getPath map
      | city | Dallas |
    Given I verify getService for "weatherapp" has status 200 on "get.url" with header params "", path params "getPath", query params "", form params "", auth "", body ""
    Then I verify rest response data for weatherapp
      | City               | Dallas     |
      | WeatherDescription | clear sky  |
      | Humidity           | 37 Percent |

  @rest @posttest201
    #make sure you are sending different data so data gets inserted and returns 201
  Scenario: post test
    Given I store parameters in postPath map
      | action | register |
    Given I store parameters in postBody map
      | FirstName | testfir3 |
      | LastName  | testlast3 |
      | UserName  | fwuser3   |
      | Password  | pass23     |
      | Email     | so@ma1.com |
    Given I verify postService for "postapp" has status 201 on "post.url" with header params "", path params "postPath", query params "", form params "", auth "", body "postBody"
    Then I verify rest response data for postapp
      | SuccessCode | OPERATION_SUCCESS                |
      | Message     | Operation completed successfully |

  @rest @posttest200
    #because we are sending same data it will send 200. Response validations will be different for 200 and 201
  Scenario: post test
    Given I store parameters in postPath map
      | action | register |
    Given I store parameters in postBody map
      | FirstName | testfir1  |
      | LastName  | testlast2 |
      | UserName  | fwuser2   |
      | Password  | pass2     |
      | Email     | so@ma.com |
    Given I verify postService for "postapp" has status 200 on "post.url" with header params "", path params "postPath", query params "", form params "", auth "", body "postBody"
    Then I verify rest response data for postapp
      | FaultId | User already exists       |
      | fault   | FAULT_USER_ALREADY_EXISTS |

  @rest @puttest
  Scenario: put test
    Given I store parameters in putPath map
      | action | update |
      | empid  | 12345  |
    Given I store parameters in putBody map
      | name   | testfir  |
      | salary | testlast |
      | age    | fwuser1  |
    Given I verify putService for "putapp" has status 200 on "putdelete.url" with header params "", path params "putPath", query params "", form params "", auth "", body "putBody"
    Then I verify rest response data for putapp
      | status | success |
      | data   | null    |

  @rest @deletetest
  Scenario: delete test
    Given I store parameters in deletePath map
      | action | delete |
      | empid  | 12345  |
    Given I verify deleteService for "deleteapp" has status 200 on "putdelete.url" with header params "", path params "deletePath", query params "", form params "", auth "", body ""
    Then I verify rest response data for deleteapp
      | status  | failed                           |
      | message | Error! Not able to delete record |

  @rest @authtest
  Scenario: auth test
    Given I store parameters in authPath map
      | action | CheckForAuthentication |
    Given I store parameters in auth map
      | username | ToolsQA      |
      | password | TestPassword |
    Given I verify getService for "authapp" has status 200 on "auth.url" with header params "", path params "authPath", query params "", form params "", auth "auth", body ""
    Then I verify rest response data for authapp
      | FaultId             | OPERATION_SUCCESS                |
      | Fault               | Operation completed successfully |
      | Username:Password   | ToolsQA:TestPassword             |
      | Authentication Type | Basic                            |

  @rest @authtestfail
    #just don't send auth map and it fails authentication
  Scenario: auth test fail
    Given I store parameters in authPath map
      | action | CheckForAuthentication |
    Given I verify getService for "authapp" has status 401 on "auth.url" with header params "", path params "authPath", query params "", form params "", auth "", body ""
    Then I verify rest response data for authapp
      | StatusID | FAULT_USER_INVALID_USER_PASSWORD               |
      | Status   | Invalid or expired Authentication key provided |
