package TestMain.GetRequests;

import BaseURL.BaseUrlRestfulBooker;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllBookings extends BaseUrlRestfulBooker {

    @Description("Get all bookings from data base")
    @Test
    public void getBookings () {

        specBaseUrl.pathParam("pp1","booking") ;
        Response  response = given().spec(specBaseUrl).when().get("{pp1}");
        response.prettyPrint();
    }
}
