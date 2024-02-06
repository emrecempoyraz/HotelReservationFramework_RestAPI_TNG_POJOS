package TestData;

public class ResponseBody {

    private int bookingID ;
    private ReservationGeneralInfo booking ;

    public ResponseBody() {
    }

    public ResponseBody(int bookingID, ReservationGeneralInfo booking) {
        this.bookingID = bookingID;
        this.booking = booking;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public ReservationGeneralInfo getBooking() {
        return booking;
    }

    public void setBooking(ReservationGeneralInfo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "bookingID=" + bookingID +
                ", booking=" + booking +
                '}';
    }
}
