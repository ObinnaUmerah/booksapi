package definitions;

import com.books.project.LibraryApplication;
import com.books.project.model.Author;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LibraryApplication.class)
public class SpringBootCucumberTestDefinitions {

    private static final String BASE_URL = "http://localhost:";

    @LocalServerPort
    String port;

    private static Response response;

    private static Author author = new Author("Paul Bunyon", "A very wise man");

    @When("An author publishes a book")
    public void authorAddsABookToReadingList() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Jack Wayne");
        requestBody.put("description", "An author from the 16th century");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/authors/");
    }

    @Then("Check if the book is published")
    public void checkIfTheBookIsPublished() {

        Assert.assertEquals(201, response.getStatusCode());
    }


    @Given("There is an author")
    public void thereIsAnAuthor() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body(request.toString()).get(BASE_URL + port + "/api/authors/1");

    }


    @When("The author wants to revise his book")
    public void theAuthorWantsToReviseHisBook() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Dr. Seuss");
        requestBody.put("description","A well-known children books author");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/authors/1");
    }



    @Then("Release new copies of that book")
    public void releaseNewCopiesOfThatBook() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("The author now wants to delete the original copy")
    public void theAuthorNowWantsToDeleteTheOriginalCopy() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body(request.toString()).delete(BASE_URL + port + "/api/authors/1");
    }

    @Then("The book is no longer available for purchase")
    public void theBookIsNoLongerAvailableForPurchase() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("An author writes a book")
    public void anAuthorWritesABook() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        request.header("Content-Type", "application/json");
        requestBody.put("name", "A Carnivorous Carnival");
        requestBody.put("description", "A Great Book");
        requestBody.put("isbn", "390280");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/authors/1/books");
    }

    @Then("The book is written")
    public void theBookIsWritten() {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("The user wants to retrieve an author's book")
    public void theUserWantsToRetrieveAnAuthorSBook() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body(request.toString()).get(BASE_URL + port + "/api/authors/1/books/1");
    }


    @Then("The user has access to the author's book")
    public void theUserHasAccessToTheAuthorSBook() {
        Assert.assertEquals(200, response.getStatusCode());
    }


    @When("The user wants to edit the author's book")
    public void theUserWantsToEditTheAuthorSBook() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        request.header("Content-Type", "application/json");
        requestBody.put("name", "Tne End");
        requestBody.put("description", "The last book in the series.");
        requestBody.put("isbn", "390280");
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/authors/1/books/1");

    }

    @Then("The author's book is edited")
    public void theAuthorSBookIsEdited() {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("The user removes the author's book from his library")
    public void theUserRemovesTheAuthorSBookFromHisLibrary() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body(request.toString()).delete(BASE_URL + port + "/api/authors/1/books/1");
    }

    @Then("The book is removed")
    public void theBookIsRemoved() {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(200, response.getStatusCode());
    }
}
