package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetTestWithPathVariable {

    private static final Logger LOGGER = LogManager.getLogger(GetTestWithPathVariable.class);

    @Test
    public void getSingleUser() {
        LOGGER.info("---------API Test: Read(Get) Single User with Path Variable");

        //Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Get the RequestSpecification of the request that you want to send to the server
        RequestSpecification httpRequest = RestAssured.given();

        //Make a request to the server and this will return the response
        String id = "2";
        Response response = httpRequest.request(Method.GET, id);              //Method method URI uri
        LOGGER.debug(response.getBody().asPrettyString());

        //Validate the response status code
        Assert.assertEquals(response.getStatusCode(), 200);

        //get the json path object instance from the response
        JsonPath jsonPath = response.jsonPath();

        //Validating the specified email exist min the response body
        String expectedEmailID = "janet.weaver@reqres.in";
        String actualEmailId = jsonPath.getString("data.email");
        Assert.assertEquals(actualEmailId, expectedEmailID);

        LOGGER.info("-----------End Test: Read(Get) Single User with Path Variable ");
    }

    @Test
    public void attemptToGetUserWithInvalidId() {
        LOGGER.info("-------- API Test: Attempt to retrieve User with invalid id");

        //Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Get the RequestSpecification of the request that you want to send to the server
        RequestSpecification httpRequest = RestAssured.given();

        //Make a request to the server and this will return the response
        String InvalidId = "23";
        Response response = httpRequest.request(Method.GET, InvalidId);
        LOGGER.debug(response.getBody().asPrettyString());

        //Validate the response status code
        Assert.assertEquals(response.getStatusCode(), 404);

        //Validating the response has empty body
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get().toString(), "{}");


        LOGGER.info("-----------End Test: Attempt to retrieve User with invalid id");


    }
}
