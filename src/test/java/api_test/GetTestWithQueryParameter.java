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

import java.util.List;

public class GetTestWithQueryParameter {

    private static final Logger LOGGER = LogManager.getLogger(GetTestWithQueryParameter.class);

    @Test
    public void getUserWithQueryParameter() {
        LOGGER.info("---------API Test: Read(Get) All Users with Query Parameter");

        //Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Get the RequestSpecification of the request that you want to send to the server
        RequestSpecification httpRequest = RestAssured.given();

        //Make a request to the server and this will return the response
        Response response = httpRequest.queryParam("page", "2").request(Method.GET);              //import io.restassured.response.Response;
        LOGGER.debug(response.getBody().asPrettyString());

        //Validate the response status code
        Assert.assertEquals(response.getStatusCode(), 200);

        //get the json path object instance from the response
        JsonPath jsonPath = response.jsonPath();
        List<String> list = jsonPath.get("data.email");

        //Validating the specified email exist min the response body
        String emailID = "michael.lawson@reqres.in";
        boolean emailExist = list.contains(emailID);
        Assert.assertTrue(emailExist, emailID + "does not exist");

        LOGGER.info("-----------End Test: Read(Get) All Users with Query Parameter");
    }
}
