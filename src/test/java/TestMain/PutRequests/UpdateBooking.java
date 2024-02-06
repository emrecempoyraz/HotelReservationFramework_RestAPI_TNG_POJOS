package TestMain.PutRequests;

import BaseURL.BaseUrlRestfulBooker;
import TestData.BookingDates;
import TestData.ReservationGeneralInfo;
import TestData.ResponseCodes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class UpdateBooking extends BaseUrlRestfulBooker {

    /*
    curl -X PUT \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Cookie: token=abc123' \
  -d '{
    "firstname" : "James",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}'
     */


    BookingDates bookingdates ;

    @Description("Updates booking information")
    @Test
    public void updateBookingInfo() {

        specBaseUrl.pathParams("pp1","booking","pp2","3");
        bookingdates = new BookingDates("2018-01-01","2019-01-01");
        ReservationGeneralInfo requestBody = new ReservationGeneralInfo("Hasan","Kalkan",500,false,bookingdates,"Room Service");
        System.out.println(requestBody);
        Response response= given().spec(specBaseUrl).contentType(ContentType.JSON).log().all()
                .when().body(requestBody).put("/{pp1}/{pp2}");
        response.prettyPrint();
        ReservationGeneralInfo responseBody = response.as(ReservationGeneralInfo.class);
        assertEquals(response.getStatusCode(),ResponseCodes.successCode);
        assertEquals(response.getContentType(), ResponseCodes.contentType);
        assertEquals(responseBody.getFirstname(),requestBody.getFirstname());
        assertEquals(responseBody.getLastname(),requestBody.getLastname());
        assertEquals(responseBody.getTotalprice(),requestBody.getTotalprice());
        assertEquals(responseBody.isDepositpaid(),requestBody.isDepositpaid());
        assertEquals(responseBody.getBookingdates().getCheckin(),requestBody.getBookingdates().getCheckin());
        assertEquals(responseBody.getBookingdates().getCheckout(),requestBody.getBookingdates().getCheckout());
        assertEquals(responseBody.getAdditionalneeds(),requestBody.getAdditionalneeds());
    }


}
