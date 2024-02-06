package TestMain.GetRequests;

import BaseURL.BaseUrlRestfulBooker;
import TestData.BookingDates;
import TestData.ReservationGeneralInfo;
import TestData.ResponseCodes;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class GetBookingById extends BaseUrlRestfulBooker {

    /*
    TTP/1.1 200 OK

  {
    "firstname": "Sally",
    "lastname": "Ericsson",
    "totalprice": 305,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2021-11-27",
        "checkout": "2022-11-28"
    }


     */

    BookingDates bookingDates ;
    ReservationGeneralInfo bookingExpected ;



    @Description("Check booking with booking id")
    @Test
    public void getReservationById () {
        specBaseUrl.pathParams("pp1","booking","pp2","1");
        bookingDates = new BookingDates("2018-08-17","2019-01-12");
        bookingExpected = new ReservationGeneralInfo("Eric","Jones",914,true,bookingDates,"Breakfast");
        System.out.println(bookingExpected);
        Response response = given().spec(specBaseUrl).when().get("{pp1}/{pp2}");
        response.prettyPrint();
        JsonPath responseJP = response.jsonPath();
        assertEquals("Response is not matching",ResponseCodes.successCode,response.statusCode());
        assertEquals("Names not matching",bookingExpected.getFirstname(),responseJP.getString("firstname"));
        assertEquals("Last Names not matching",bookingExpected.getLastname(),responseJP.getString("lastname"));
        assertEquals("Deposits not matching",bookingExpected.isDepositpaid(),responseJP.getBoolean("depositpaid"));
        assertEquals("Total price not matching",bookingExpected.getTotalprice(),responseJP.getInt("totalprice"));
        assertEquals("Checkin is not matching",bookingExpected.getBookingdates().getCheckin(),responseJP.getString("bookingdates.checkin"));
        assertEquals("Checkout is not matching",bookingExpected.getBookingdates().getCheckout(),responseJP.getString("bookingdates.checkout"));
        assertEquals("Needs is not matching",bookingExpected.getAdditionalneeds(),responseJP.getString("additionalneeds"));



    }
}
