package test.entity;

public class Booking {
    private int bookingId;
    private int userId;
    private int centerId;
    private int day;

    public Booking(int bookingId, int userId, int centerId, int day) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.centerId = centerId;
        this.day = day;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCenterId() {
        return centerId;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", centerId=" + centerId +
                ", day=" + day +
                '}';
    }
}
