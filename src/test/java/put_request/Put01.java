package put_request;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import pojos.PetStoreUserResponsePojo;
import pojos.PetStoreuserPojo;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Put01 extends PetStoreBaseUrl {

    /*
       Given
         1) https://petstore.swagger.io/v2/user/JohnDoe
         2)  {
                "id": 11234,
                "username": "JohnnyFoe",
                "firstName": "John",
                "lastName": "Doe",
                "email": "j@d.com",
                "password": "1234",
                "phone": "1234",
                "userStatus": 3
              }
      When
          I send POST Request to the Url
      Then
          Status code is 200
      And
          response body is like {
                              {
                                  "code": 200,
                                  "type": "unknown",
                                  "message": "11234"
                              }
   */

    @Test
    public void put01() throws IOException {

        //set the url

        spec.pathParams("first","user","second","JohnDoe");

        //set the expected data
        PetStoreuserPojo expectedData=new PetStoreuserPojo(11234,"JohnnyFoe","John","Doe","j@d.com","1234","1234",3);
        System.out.println("expectedData="+expectedData);


        //send the request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        //do asserttion
        PetStoreUserResponsePojo actualData=new ObjectMapper().readValue(response.asString(), PetStoreUserResponsePojo.class);
        System.out.println("actualData="+actualData);

        //do assertion

        assertEquals(200,response.statusCode());
        assertEquals(200,actualData.getCode());
        assertEquals("unknown",actualData.getType());
        assertEquals("11234",actualData.getMessage());


    }


}
