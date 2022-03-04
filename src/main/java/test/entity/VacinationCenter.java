package test.entity;

import java.util.HashMap;

public class VacinationCenter {
    private int id;
    private String state;
    private String district;
    private HashMap<Integer, Integer> capacity;     // day --> capacity
    private HashMap<Integer, Integer> currentCapacity;

    // userId, booking)
    private HashMap<Integer, Booking> bookings;

    public VacinationCenter(int id, String state, String district) {
        this.id = id;
        this.state = state;
        this.district = district;
        this.capacity = new HashMap<>();
        this.currentCapacity = new HashMap<>();
        this.bookings = new HashMap<>();
    }

    public HashMap<Integer, Booking> getBookings() {
        return bookings;
    }

    public void addCapacity(int day, int capacity) {
        this.capacity.put(day, capacity);
        this.currentCapacity.put(day, 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public HashMap<Integer, Integer> getCapacity() {
        return capacity;
    }

    public void setCapacity(HashMap<Integer, Integer> capacity) {
        this.capacity = capacity;
    }

    public HashMap<Integer, Integer> getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(HashMap<Integer, Integer> currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
}
