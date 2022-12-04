package delete_request;

import base_urls.DummyRestBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import pojos.DummyApiDeletePojo;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Delete01 extends DummyRestBaseUrl {

    /*
        URL: https://dummy.restapiexample.com/api/v1/delete/2
       HTTP Request Method: DELETE Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
               ii) "status" is "success"
              iii) "data" is "2"
               iv) "message" is "Successfully! Record has been deleted"
     */
/*
Given
    https://dummy.restapiexample.com/api/v1/delete/2
When
    User sends Delete Request
Then
    Status code is 200
And
    "status" is "success"
And
    "data" is "2"
And
    "message" is "Successfully! Record has been deleted"
 */

@Test

    public void delete01() throws IOException {

    //set the url
    spec.pathParams("first","delete","second",2);

    //set the expecte data
    DummyApiDeletePojo expectedData=new DummyApiDeletePojo("success","2","Successfully! Record has been deleted");
    System.out.println("expecteddata="+expectedData);

    //send the request and get the response

    Response response=given().spec(spec).when().delete("/{first}/{second}");
    response.prettyPrint();

    //do assertion
    DummyApiDeletePojo actualData=new ObjectMapper().readValue(response.asString(),DummyApiDeletePojo.class);
    assertEquals(200,response.statusCode());
    assertEquals(expectedData.getStatus(),actualData.getStatus());
    assertEquals(expectedData.getData(),actualData.getData());
    assertEquals(expectedData.getMessage(),actualData.getMessage());
}

}
