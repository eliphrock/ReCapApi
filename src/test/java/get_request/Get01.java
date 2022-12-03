package get_request;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Get01 {

    /*
  Given
      https://petstore.swagger.io/v2/pet/12
  When
      User sends a GET Request to the url
  Then
      HTTP Status Code should be 200
  And
      Content Type should be JSON
  And
      Status Line should be HTTP/1.1 200 OK
*/
    public static void main(String[] args) {
        //set the url

        String url="https://petstore.swagger.io/v2/pet/12";

        //send the request and get the request

        Response response=given().when().get(url);
        response.prettyPrint();

        //do assertion

        //HTTP Status code should be 200
        if(response.statusCode()==200){
            System.out.println("Test Passed: Status Code is 200");
        }else{
            System.out.println("Test Failed:Status Code" + " " + response.statusCode());
        }

        //content type should be JSON
        if(response.contentType().equals("application/json")){
            System.out.println("Test Passed : Content type is application/json");
        }else{
            System.out.println("Test Failed:Content Type is " + " " + response.contentType());
        }

        //Status Line should be HTTP/1.1 200 OK

        if(response.statusLine().equals("HTTP/1.1 200 OK")){
            System.out.println("Test Passed :Status Line is HTTP/1.1 200 OK");
        }else{
            System.out.println("Test Failed: Status Line is "+ " "+ response.statusLine());
        }
    }
}
