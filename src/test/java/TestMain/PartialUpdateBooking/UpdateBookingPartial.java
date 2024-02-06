package TestMain.PartialUpdateBooking;

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

public class UpdateBookingPartial extends BaseUrlRestfulBooker {

    @Description("Updates partial informations of booking")
    @Test
    public void partialUpdateBooking () {
        specBaseUrl.pathParams("pp1","booking","pp2","1");
        ReservationGeneralInfo requestBody = new ReservationGeneralInfo();
        requestBody.setFirstname("Will");
        requestBody.setLastname("Jackson");
        Response response = given().spec(specBaseUrl).contentType(ContentType.JSON).when().body(requestBody).patch("{pp1}/{pp2}");
        response.prettyPrint();
        ReservationGeneralInfo expectedResponse = response.as(ReservationGeneralInfo.class);
        assertEquals(response.getStatusCode(), ResponseCodes.successCode);
        assertEquals(expectedResponse.getFirstname(),requestBody.getFirstname());
        assertEquals(expectedResponse.getLastname(),requestBody.getLastname());
    }

    @Description("Updates booking checkin and chekout information")
    @Test
    public void updateReservationDates () {
        specBaseUrl.pathParams("pp1","booking","pp2","2");
        ReservationGeneralInfo reservationGeneralInfo = new ReservationGeneralInfo();
        BookingDates requestBookingDates = new BookingDates("2015-01-01", "2028-01-01");
        reservationGeneralInfo.setBookingdates(requestBookingDates);
        System.out.println(requestBookingDates);
        Response response = given().spec(specBaseUrl).contentType(ContentType.JSON).log().all().when().body(reservationGeneralInfo).patch("{pp1}/{pp2}");
        response.prettyPrint();
        ReservationGeneralInfo expectedDatesResponse = response.as(ReservationGeneralInfo.class);
        System.out.println(expectedDatesResponse.getFirstname());
        assertEquals(expectedDatesResponse.getBookingdates().getCheckin(),requestBookingDates.getCheckin());
        assertEquals(expectedDatesResponse.getBookingdates().getCheckout(),requestBookingDates.getCheckout());
    }
}
