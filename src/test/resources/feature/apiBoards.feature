@BoardsAPI
Feature: Validation for Trello APIs on Boards functionality

  Background:
    Given the Trello API running on base URI
    And a valid API key and token for authentication

  @createNewBoard
  Scenario Outline: Successfully create a board in a workspace
    Given I have a request for create board API with param "<queryParam>" "<Value>"
    When I send a "POST" request to "CREATEBOARD"
    Then The response status code should be 200
    And The response should contain a board ID
    Examples:
      | queryParam | Value      |
      | name       | testBoard1 |

  @Neg-createBoard
  Scenario Outline: Attempt to create a board without a name
    Given I have a request for create board API with param "<queryParam>" "<Value>"
    When I send a "POST" request to "CREATEBOARD"
    Then The response status code should be 400
    And The response body should contain an error message "invalid value for name"
    Examples:
      | queryParam | Value |
      |            |       |


  @updateBoard
  Scenario Outline: Update a board with valid id
    Given I have a request for create board API with param "<queryParam>" "<Value>"
    When I send a "POST" request to "CREATEBOARD"
    Then The response status code should be 200
    And The response should contain a board ID
    Given I have a request to update a board with param "<queryParam1>" "<Value1>"
    When I send a "PUT" request to "UPDATEBOARD"
    Then The response status code should be 200

    Examples:
      | queryParam | Value       | queryParam1 | Value1      |
      | name       | toBeDeleted | name        | toBeDeleted |

  @Neg-updateBoard
  Scenario Outline: Update a board with invalid id
    Given I have a request to update a board with invalid param "<pathParam>" "<Value>"
    When I send a "PUT" request to "UPDATEBOARD"
    Then The response status code should be 400
    And The response should contain an error message "invalid id"
    Examples:
      | pathParam | Value          |
      | id        | incorrectvalue |

  @getBoard
  Scenario Outline: Get a board from workspace
    Given I have a request for get the board API with param "<pathParam>" "<value>"
    When I send a "GET" request to "GETBOARD"
    Then The response status code should be 200
    And The response should contain a board ID
    Examples:
      | pathParam | value    |
      | id        | 46mROlOT |

  @Neg-getBoard
  Scenario Outline: Get a board with invalid id
    Given I have a request for get the board API with param "<pathParam>" "<value>"
    When I send a "GET" request to "GETBOARD"
    Then The response status code should be 400
    And The response should contain an error message "invalid id"
    Examples:
      | pathParam | value          |
      | id        | incorrectvalue |


  @deleteBoardCompleteSet
  Scenario Outline: Create a new board then Delete the same board and verify the board is not found
    Given I have a request to delete a board "<queryParam>" "<value>"
    When I send a "DELETE" request to "DELETEBOARD"
    Then The response status code should be 200
    And I verify that the board is deleted with message "The requested resource was not found."
    Examples:
      | queryParam | value       |
      | name       | toBeDeleted |

  @deleteInvalidBoard
  Scenario Outline: Delete the board with invalid id
    Given I have a request to delete a board with invalid param "<pathParam>" "<value>"
    When I send a "DELETE" request to "DELETEBOARD"
    Then The response status code should be 400
    And The response should contain an error message "invalid id"
    Examples:
      | pathParam | value   |
      | id        | B20kbYH |