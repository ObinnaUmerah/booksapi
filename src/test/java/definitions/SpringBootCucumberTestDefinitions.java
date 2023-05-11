package definitions;

import com.books.project.LibraryApplication;
import com.books.project.model.Author;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
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
    public void theAuthorWantsToReviseHisBook() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body(request.toString()).get(BASE_URL + port + "/api/authors/1");
        response = request.body(request.toString()).put(BASE_URL + port + "/api/authors/1");
    }


    @Then("Release new copies of that book")
    public void releaseNewCopiesOfThatBook() {
        Assert.assertEquals(200, response.getStatusCode());
    }
}
