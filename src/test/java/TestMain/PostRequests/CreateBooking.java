package TestMain.PostRequests;

import BaseURL.BaseUrlRestfulBooker;
import TestData.BookingDates;
import TestData.ReservationGeneralInfo;
import TestData.ResponseBody;
import TestData.ResponseCodes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateBooking extends BaseUrlRestfulBooker {

    /*
    https://restful-booker.herokuapp.com/booking \
  -H 'Content-Type: application/json' \
  -d '{
    "firstname" : "Jim",
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




    @Description("Create new booking with post request")
    @Test
    public void createBooking () {
        specBaseUrl.pathParam("pp1","booking");
        BookingDates bookingDates = new BookingDates("2018-01-01","2019-01-01");
        ReservationGeneralInfo bookingRequestBody = new ReservationGeneralInfo("Ahmet","Söylemez",600,true,bookingDates,"wi-fi");
        Response response= given().spec(specBaseUrl).contentType(ContentType.JSON).log().all()
                .when().body(bookingRequestBody)
                .post("/{pp1}");
        response.prettyPrint();
        ResponseBody expectedResponse = response.as(ResponseBody.class);

        assertEquals(response.getStatusCode(),ResponseCodes.successCode);
        assertEquals(response.getContentType(),ResponseCodes.contentType);
        assertEquals(expectedResponse.getBooking().getFirstname(),bookingRequestBody.getFirstname());
        assertEquals(expectedResponse.getBooking().getLastname(),bookingRequestBody.getLastname());
        assertEquals(expectedResponse.getBooking().getTotalprice(),bookingRequestBody.getTotalprice());
        assertEquals(expectedResponse.getBooking().isDepositpaid(),bookingRequestBody.isDepositpaid());
        assertEquals(expectedResponse.getBooking().getBookingdates().getCheckin(),bookingRequestBody.getBookingdates().getCheckin());
        assertEquals(expectedResponse.getBooking().getBookingdates().getCheckout(),bookingRequestBody.getBookingdates().getCheckout());
        assertEquals(expectedResponse.getBooking().getAdditionalneeds(),bookingRequestBody.getAdditionalneeds());

    }



}
