package get_request;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Get02b extends PetStoreBaseUrl {
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

        spec.pathParams("first","pet","second","findByStatus").queryParam("status","available");

        //send the expected data


        //send the request and get the response

        Response response=given().spec(spec).when().get("/{first}/{second}");

        //print all "available" pets on console
        response.prettyPrint();

        //do assertion

        response.then().assertThat().statusCode(200);
    }



}
