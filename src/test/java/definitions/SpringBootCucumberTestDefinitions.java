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

    private static Author author = new Author("Paul", "Bunyon");


    /**
     * Tests the post request for the authors table
     * @throws JSONException Throws exception if there's a JSON serialization.
     */
    @When("A user creates an author")
    public void createAuthor() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Jack Wayne");
        requestBody.put("description", "An author from the 16th century");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/authors/");
    }

    /**
     * Tests the response from the post request
     */
    @Then("The author is created")
    public void createAuthorTest() {

        Assert.assertEquals(201, response.getStatusCode());
    }


    /**
     * Test the get request for the authors' table
     */
    @Given("There is an author")
    public void getAuthor() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body(request.toString()).get(BASE_URL + port + "/api/authors/1");

    }

    /**
     * Tests the put request for the authors table
     * @throws JSONException Throws exception if there's a JSON serialization.
     */
    @When("The user updates the author")
    public void updateAuthor() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Dr. Seuss");
        requestBody.put("description","A well-known children books author");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/authors/1");
    }


    /**
     * Tests the response from the put request
     */

    @Then("The author is updated")
    public void authorUpdateTest() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    /**
     * Tests the delete request for the authors table
     * @throws JSONException Throws exception if there's a JSON serialization.
     */
    @When("The user deletes the author")
    public void deleteAuthor() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body(request.toString()).delete(BASE_URL + port + "/api/authors/1");
    }

    /**
     * Tests the response from the delete request
     */
    @Then("The author is deleted")
    public void deleteAuthorTest() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    /**
     * Tests the post request for the books table
     * @throws JSONException Throws exception if there's a JSON serialization.
     */
    @When("The user creates a book for a single author")
    public void createBook() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        request.header("Content-Type", "application/json");
        requestBody.put("name", "A Carnivorous Carnival");
        requestBody.put("description", "A Great Book");
        requestBody.put("isbn", "390280");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/authors/1/books");
    }

    /**
     * Tests the response from the get request
     */
    @Then("The book is created")
    public void createBookTest() {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(201, response.getStatusCode());
    }

    /**
     * Tests the get request for the books table
     * @throws JSONException Throws exception if there's a JSON serialization.
     */
    @When("The user gets a book belonging to a single author")
    public void getBook() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body(request.toString()).get(BASE_URL + port + "/api/authors/1/books/1");
    }


    /**
     * Tests the response from the get request
     */
    @Then("The book is retrieved")
    public void getBookTest() {
        Assert.assertEquals(200, response.getStatusCode());
    }


    /**
     * Tests the put request for the books table
     * @throws JSONException Throws exception if there's a JSON serialization.
     */
    @When("The user wants to update a book belonging to a single author")
    public void updateBook() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        request.header("Content-Type", "application/json");
        requestBody.put("name", "Tne End");
        requestBody.put("description", "The last book in the series.");
        requestBody.put("isbn", "390280");
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/authors/1/books/1");

    }

    /**
     * Tests the response from the get request
     */
    @Then("The book is updated")
    public void updateBookTest() {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(200, response.getStatusCode());
    }

    /**
     * Tests the delete request for the books table
     * @throws JSONException Throws exception if there's a JSON serialization.
     */
    @When("The user deletes a book belonging to a single author")
    public void theUserRemovesTheAuthorSBookFromHisLibrary() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body(request.toString()).delete(BASE_URL + port + "/api/authors/1/books/1");
    }

    /**
     * Tests the response from the delete request
     */
    @Then("The book is deleted")
    public void theBookIsRemoved() {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(200, response.getStatusCode());
    }
}
