package post_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


import base_urls.MedunnaBaseUrl;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import pojos.MedunnaPojo;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.MedunnaAuthentication.generateToken;

public class Post03 extends MedunnaBaseUrl {

    /*
         Given
           1) https://medunna.com/api/rooms
           2)   {
                    "createdBy": "john_doe",
                    "createdDate": "2022-12-04T13:40:13.537985Z",
                    "roomNumber": 227610112,
                    "roomType": "TWIN",
                    "status": true,
                    "price": 111,
                    "description": "My Room"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                               {
                                    "createdBy": "john_doe",
                                    "createdDate": "2022-12-04T13:40:13.537985Z",
                                    "id": 369858,
                                    "roomNumber": 227610112,
                                    "roomType": "TWIN",
                                    "status": true,
                                    "price": 111,
                                    "description": "My Room"
                                }
     */

    @Test
    public void post03() throws IOException {
        //set the url

        spec.pathParam("first","rooms");

        //set the expected data
        MedunnaPojo expectedData=new MedunnaPojo("john_doe","2022-12-04T13:40:13.537985Z",227610112,"TWIN",true,111,"My Room");
        System.out.println("expectedData="+ expectedData);

        //send the request and get the response

        Response response = given().spec(spec).header("Authorization","Bearer "+generateToken()).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do assertion
        MedunnaPojo actualData = new ObjectMapper().readValue(response.asString(),MedunnaPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getCreatedBy(),actualData.getCreatedBy());
        assertEquals(expectedData.getCreatedDate().substring(0,10),actualData.getCreatedDate().substring(0,10));
        assertEquals(expectedData.getRoomNumber(),actualData.getRoomNumber());
        assertEquals(expectedData.getRoomType(),actualData.getRoomType());
        assertEquals(expectedData.isStatus(),actualData.isStatus());
        assertEquals(expectedData.getPrice(),actualData.getPrice());
        assertEquals(expectedData.getDescription(),actualData.getDescription());


    }
}