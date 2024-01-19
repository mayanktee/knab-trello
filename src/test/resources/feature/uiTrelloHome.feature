@TrelloUI
Feature: Trello front-end application functionality validation

  @trelloLogin @executeAfterStep
  Scenario: Verify that user should be able to land on Trello Home page
    Given Launch the Trello application
    And User is on the "Manage Your Team’s Projects From Anywhere | Trello" home page
    When User navigate to the Login page
    And User enter the login and password details
    Then User should be able to login into trello application page


  @trelloCreateBoard @executeAfterStep
  Scenario: Verify that user is able to create a board on trello workspace
    Given Launch the Trello application and Login into Application
    And User is landing on trello dashboard page
    When User click on create board button
    And User enter the name "My First Board" for the board
    Then User should be able to create a board
    And User should verify that the created board "My First Board" displayed on dashboard page

  @VerifyBoardDetails @executeAfterStep
  Scenario: Verify the details of created board on trello workspace
    Given Launch the Trello application and Login into Application
    And User is landing on trello dashboard page
    When User navigate to trello board page
    Then Select one of the created board and validate the boards component displayed correctly


  @ChangeBoardName @executeAfterStep
  Scenario: Verify that the user is able to change the board name
    Given Launch the Trello application and Login into Application
    And User is landing on trello dashboard page
    When User navigate to trello board page
    Then User changed the board name

  @DeleteTheBoard @executeAfterStep
  Scenario: Verify that the user is able to delete the board
    Given Launch the Trello application and Login into Application
    And User is landing on trello dashboard page
    When User navigate to trello board page
    Then User delete the board on workspace






