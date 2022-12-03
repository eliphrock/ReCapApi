package get_request;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Get02 {
    //Print all "available" pets on console by using"https://petstore.swagger.io/" documentation.
/*
Given
    https://petstore.swagger.io/v2/pet/findByStatus?status=available

When
    User send the Get request to url

Then

    HTTP status code is 200
And
    Print all "available" pets on console

 */

    @Test

    public void get02(){
        //set the url

        String url="https://petstore.swagger.io/v2/pet/findByStatus?status=available";

        //send the expected data


        //send the request and get the response

        Response response=given().when().get(url);

        //print all "available" pets on console
        response.prettyPrint();

        //do assertion

        response.then().assertThat().statusCode(200);
    }



}
