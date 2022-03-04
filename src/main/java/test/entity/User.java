package test.entity;

public class User {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String state;
    private String district;
    private Integer bookingId;

    public User(int id, String name, String gender, int age, String state, String district) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.state = state;
        this.district = district;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getDistrict() {
        return district;
    }

    public Integer getBookingId() {
        return bookingId;
    }
}
