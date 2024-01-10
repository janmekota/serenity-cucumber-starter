Feature: Polling with SerenityRest

  Scenario: Get the response on the first try - with SerenityRest (TEST SHOULD PASS)
    Then the response contains 'two' after '0' error responses - with SerenityRest

  Scenario: Get the response on the re-try - with SerenityRest (TEST SHOULD PASS)
    Then the response contains 'two' after '5' error responses - with SerenityRest

  Scenario: Timeout occurs - with SerenityRest (TEST SHOULD END WITH ERROR)
    Then the response contains 'two' after '15' error responses - with SerenityRest