Feature: Create Mock configuration

  Background:
    * url 'http://localhost:8081'

  Scenario Outline: Register new mock configuration successfully
    Given path '/dynamic-mock/manager/create'
    And request { path: '<path>', response: '<response>' }
    When method post
    Then status 200
    And match $.code == '#notnull'

    Examples:
      | path     | response                |
      | /claro   | {"response": "ok"}      |
      | /payment | {"response": "payment"} |
