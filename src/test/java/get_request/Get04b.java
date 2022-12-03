package get_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get04b extends HerOkuAppBaseUrl {

    /*
      Given
          https://restful-booker.herokuapp.com/booking/8065
      When
          User send a GET request to the URL
      Then
          HTTP Status Code should be 200
      And
          Response content type is “application/json”
      And
          Response body should be like;

                {
                    "firstname": "John",
                    "lastname": "Smith",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Breakfast"
                }
   */
    @Test
    public void get04(){
        //Set the url
        spec.pathParams("first","booking","second",8918);

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion -hard Assertion
           JsonPath jsonPath = response.jsonPath();
//
//        assertEquals(200,response.statusCode());
//        assertEquals("application/json; charset=utf-8",response.contentType());
//        assertEquals("John",jsonPath.getString("firstname"));
//        assertEquals("Smith",jsonPath.getString("lastname"));
//        assertEquals(111,jsonPath.getInt("totalprice"));
//        assertEquals(true,jsonPath.getBoolean("depositpaid"));
//        assertEquals("2018-01-01",jsonPath.getString("bookingdates.checkin"));
//        assertEquals( "2019-01-01",jsonPath.getString("bookingdates.checkout"));
//        assertEquals("Breakfast",jsonPath.getString("additionalneeds"));



        //do assertion-soft assertion

        //1st -create softassert object

        SoftAssert softAssert=new SoftAssert();

        //2nd do assertion

        softAssert.assertEquals(response.statusCode(),200);
        softAssert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        softAssert.assertEquals(jsonPath.getString("firstname"),"John");
        softAssert.assertEquals(jsonPath.getString("lastname"),"Smith");
        softAssert.assertEquals(jsonPath.getInt("totalprice"),111);
        softAssert.assertEquals(jsonPath.getBoolean("depositpaid"),true);
        softAssert.assertEquals(jsonPath.getString("additionalneeds"),"Breakfast");

        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2018-01-01");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"),"2019-01-01");

        //3rd: AssertAll
        softAssert.assertAll();



    }
}
