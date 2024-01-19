package step.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.log4testng.Logger;
import testUtils.APIResources;
import testUtils.SpecBuilder;
import testUtils.Utils;


import java.io.IOException;

import static io.restassured.RestAssured.given;

public class MyStepdefsAPI {

    Logger log = Logger.getLogger(MyStepdefsAPI.class);
    private RequestSpecification requestSpec;
    SessionFilter sessionFilter = new SessionFilter();
    private Response response;
    APIResources apiResource;
    public static String apiKey;
    public static String token;
    public static String boardId;

    @Given("the Trello API running on base URI")
    public void theTrelloAPIRunningOnBaseURI() throws IOException {
        requestSpec =  given().log().all().spec(SpecBuilder.requestSpecification()).filter(sessionFilter);
    }

    @And("a valid API key and token for authentication")
    public void aValidAPIKeyAndTokenForAuthentication() throws IOException {
        apiKey = Utils.getGlobalValues("apiKey");
        token = Utils.getGlobalValues("token");

    }
    @Given("I have a request for create board API with param {string} {string}")
    public void iHaveARequestForCreateBoardAPIWithParam(String param, String value) {
        requestSpec.queryParam("key",apiKey).queryParam("token",token).queryParam(param,value).filter(sessionFilter);
    }

    @Then("The response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCodeExpected) {
        int statusCodeActual= response.statusCode();
        Assert.assertEquals(statusCodeActual,statusCodeExpected);
    }

    @And("The response should contain a board ID")
    public void theResponseShouldContainABoardID() {
        boardId = response.jsonPath().get("id");
        log.info("The board ID :"+boardId);
    }
    @And("The response body should contain an error message {string}")
    public void theResponseBodyShouldContainAnErrorMessage(String expectedMessage) {
        String errorMessage = response.jsonPath().get("message");
        Assert.assertTrue(errorMessage.contains(expectedMessage));
    }
    @And("The response should contain an error message {string}")
    public void theResponseShouldContainAnErrorMessage(String expectedMessage) {
        String errorResp = response.getBody().asString();
        Assert.assertTrue(errorResp.contains(expectedMessage));
    }

    @Given("I have a request to update a board with param {string} {string}")
    public void iHaveARequestToUpdateABoardWithParam(String name, String value) throws IOException {
        requestSpec = given().log().all().spec(SpecBuilder.requestSpecification()).pathParam("id",boardId).queryParam("key",apiKey).queryParam("token",token).queryParams(name,value).filter(sessionFilter);
    }


    @Given("I have a request for get the board API with param {string} {string}")
    public void iHaveARequestForGetTheBoardAPIWithParam(String param, String value) throws IOException {
        requestSpec = given().log().all().spec(SpecBuilder.requestSpecification()).pathParam(param,value).queryParam("key",apiKey).queryParam("token",token);
    }

    @When("I send a {string} request to {string}")
    public void iSendARequestTo(String reqType, String resource) {
        apiResource =  APIResources.valueOf(resource);
        try {
        switch (reqType){

            case "GET":
                response = requestSpec.when().get(apiResource.getResource());
                response.then().log().all();
                break;
            case "PUT":
                response = requestSpec.when().put(apiResource.getResource());
                response.then().log().all();
                break;
            case "POST":
                response = requestSpec.when().post(apiResource.getResource());
                response.then().log().all();
                break;
            case "DELETE":
                response = requestSpec.when().delete(apiResource.getResource());
                response.then().log().all();
                break;
        }
        }catch(Exception e){
            log.error(" The API failed due to exception : " + e);
        }
    }

    @Given("I have a request to update a board with invalid param {string} {string}")
    public void iHaveARequestToUpdateABoardWithInvalidParam(String id, String value) throws IOException {
        requestSpec = given().log().all().spec(SpecBuilder.requestSpecification()).pathParam(id,value).queryParam("key",apiKey).queryParam("token",token);
    }


    @Given("I have a request to delete a board {string} {string}")
    public void iHaveARequestToDeleteABoard(String param, String value) throws IOException {
        requestSpec = given().log().all().spec(SpecBuilder.requestSpecification()).pathParam("id",boardId).queryParam("key",apiKey).queryParam("token",token).queryParams(param,value);
    }

    @Given("I have a request to delete a board")
    public void iHaveARequestToDeleteABoard() throws IOException {
        requestSpec = given().log().all().spec(SpecBuilder.requestSpecification()).pathParam("id",boardId).queryParam("key",apiKey).queryParam("token",token);
    }

    @And("I verify that the board is deleted with message {string}")
    public void iVerifyThatTheBoardIsDeletedWithMessage(String expectedMessage) throws IOException {
        requestSpec = given().log().all().spec(SpecBuilder.requestSpecification()).pathParam("id",boardId).queryParam("key",apiKey).queryParam("token",token);
        apiResource =  APIResources.valueOf("GETBOARD");
        response = requestSpec.when().get(apiResource.getResource());
        String actualMessage = response.getBody().asString();
        response.then().log().all();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Given("I have a request to delete a board with invalid param {string} {string}")
    public void iHaveARequestToDeleteABoardWithInvalidParam(String param, String value) throws IOException {
        requestSpec = given().log().all().spec(SpecBuilder.requestSpecification()).pathParam(param,value).queryParam("key",apiKey).queryParam("token",token);
    }

}
