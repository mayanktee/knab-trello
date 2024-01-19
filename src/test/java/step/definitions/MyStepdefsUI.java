package step.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonAppLaunch;
import testBase.BaseTest;

import java.io.IOException;

public class MyStepdefsUI extends BaseTest {

    CommonAppLaunch com = new CommonAppLaunch();

    @Given("Launch the Trello application")
    public void launchTheTrelloApplication() throws IOException {
        com.fnLaunchApp();
    }

    @And("User is on the {string} home page")
    public void userIsOnTheHomePage(String title) {
        com.fnValidateLoginPageTitle(title);
    }

    @When("User navigate to the Login page")
    public void userNavigateToTheLoginPage() {
        com.fnGoToLoginPage();
    }
    @And("User enter the login and password details")
    public void userEnterTheLoginAndPasswordDetails() throws IOException, InterruptedException {
        com.fnUserEnterLoginID();
        com.fnUserEnterPassword();
    }

    @Then("User should be able to login into trello application page")
    public void userShouldBeAbleToLoginIntoTrelloApplicationPage() throws IOException {
        com.fnTrelloDashBoardLanding();
    }


    @Given("Launch the Trello application and Login into Application")
    public void launchTheTrelloApplicationAndLoginIntoApplication() throws IOException, InterruptedException {
        com.fnLaunchApp();
        com.fnGoToLoginPage();
        com.fnUserEnterLoginID();
        com.fnUserEnterPassword();
    }

    @And("User is landing on trello dashboard page")
    public void userIsLandingOnTrelloDashboardPage() throws IOException {
        com.fnTrelloDashBoardLanding();
    }

    @When("User click on create board button")
    public void userClickOnCreateBoardButton() {
        com.fnClickCreateBoard();
    }

    @And("User enter the name {string} for the board")
    public void userEnterTheNameForTheBoard(String name) {
        com.fnEnterBoardName(name);
    }


    @Then("User should be able to create a board")
    public void userShouldBeAbleToCreateABoard() {
        com.fnSubmitCreateBoard();

    }

    @And("User should verify that the created board {string} displayed on dashboard page")
    public void userShouldVerifyThatTheCreatedBoardDisplayedOnDashboardPage(String expectedName) {
        com.fnVerifyBoardIsCreated(expectedName);
    }

    @When("User navigate to trello board page")
    public void userNavigateToTrelloBoardPage() {
        com.fnClickOnBoardLinkLeftPane();
        com.fnClickOnFirstBoard();
    }

    @Then("Select one of the created board and validate the boards component displayed correctly")
    public void selectOneOfTheCreatedBoardAndValidateTheBoardsComponentDisplayedCorrectly() {
        com.fnValidateBoardsComponentDisplayed();
    }

    @Then("User changed the board name")
    public void userChangedTheBoardName() {
        com.fnChangeBoardName();
    }

    @Then("User delete the board on workspace")
    public void userDeleteTheBoardOnWorkspace() {
        com.fnDeleteTheBoard();
    }
}
