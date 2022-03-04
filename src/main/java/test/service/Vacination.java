package test.service;

import test.entity.Booking;
import test.entity.User;
import test.entity.VacinationCenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Vacination {

    private int bookingId;
    private HashMap<Integer, User> userHashMap;
    private HashMap<Integer, VacinationCenter> centerHashMap;

    private HashMap<String, List<VacinationCenter>> districtVacinationCenterMapping;

    public Vacination() {
        this.bookingId = 1;
        this.districtVacinationCenterMapping = new HashMap<>();
        this.centerHashMap = new HashMap<>();
        this.userHashMap = new HashMap<>();
    }

    public void addUser(int id, String name, String gender, int age, String state, String district) {
        if (userHashMap.containsKey(id)) {
            // already register
            return;
        }
        if (age < 18) {
            return;
        }
        User currentUser = new User(id, name, gender, age, state, district);
        this.userHashMap.put(id, currentUser);

        System.out.println("added user " + name);
    }

    public void addVacinationCenter(String state, String district, int centerId) {
        if (centerHashMap.containsKey(centerId)) {
            return;
        }
        // create center
        VacinationCenter center = new VacinationCenter(centerId, state, district);

        // add center to it's id
        this.centerHashMap.put(centerId, center);

        // add it to the state map
        if (!this.districtVacinationCenterMapping.containsKey(district)) {
            this.districtVacinationCenterMapping.put(district, new ArrayList<>());
        }
        this.districtVacinationCenterMapping.get(district).add(center);

        System.out.println("Added vacination center");
    }

    public void addCapacity(int centerId, int day, int capacity) {
        if (!centerHashMap.containsKey(centerId)) {
            return;
        }
        if (capacity < 0) {
            return;
        }
        VacinationCenter center = this.centerHashMap.get(centerId);
        center.addCapacity(day, capacity);
        System.out.println("added capacity");
    }

    public void listVacinationCenters(String district) {
        if (!districtVacinationCenterMapping.containsKey(district)) {
            return;
        }
        for (VacinationCenter center : districtVacinationCenterMapping.get(district)) {
            System.out.printf("Vacination details for center id %d is : \n", center.getId());
            for (int day : center.getCapacity().keySet()) {
                int totalCapacity = center.getCapacity().get(day);
                int booked = center.getCurrentCapacity().get(day);
                System.out.printf("Status for day %d : %d total capacity %d booked\n", day, totalCapacity, booked);
            }
        }
    }

    public int bookVacination(int centerId, int day, int userId) {
        if (!this.userHashMap.containsKey(userId)) {
            System.out.println("user does not exists");
            return -1;
        }
        if (!this.centerHashMap.containsKey(centerId)) {
            System.out.println("center does not exists");
            return -1;
        }

        VacinationCenter center = this.centerHashMap.get(centerId);
        User user = this.userHashMap.get(userId);

        if (!center.getDistrict().equalsIgnoreCase(user.getDistrict())) {
            System.out.println("district mismatch");
            return -1;
        }

        if (Objects.nonNull(user.getBookingId())) {
            System.out.println("user already booked");
            return -1;
        }

        if (center.getBookings().containsKey(userId)) {
            // already booked
            System.out.println("already booked");
            return -1;
        }

        if (!center.getCapacity().containsKey(day)) {
            System.out.println("capacity not set for center");
            return -1;
        }

        int limit = center.getCapacity().get(day);
        int currentLimit = center.getCurrentCapacity().get(day);

        if (limit > currentLimit) {

            Booking booking = new Booking(bookingId, userId, centerId, day);

            center.getCurrentCapacity().put(day, currentLimit + 1);

            user.setBookingId(bookingId);

            center.getBookings().put(userId, booking);

            this.bookingId++;
        } else {
            System.out.println("center full for day");
            return -1;
        }

        System.out.printf("Booking done for day %d for user %d at center %d\n", day, userId, centerId);

        return this.bookingId-1;
    }

    public void listAllBookings(int centerId, int day) {
        System.out.printf("all booking for %d center at %d\n", centerId, day);
        for (Booking currentBooking : this.centerHashMap.get(centerId).getBookings().values()) {
            if (day == currentBooking.getDay()) {
                System.out.println(currentBooking);
            }
        }
    }

    public void cancelBooking(int centerId, int bookingId, int userId) {
        if (!this.centerHashMap.containsKey(centerId)) {
            System.out.println("invalid center");
            return;
        }
        if (!this.userHashMap.containsKey(userId)) {
            System.out.println("invalid user");
            return;
        }
        VacinationCenter center = this.centerHashMap.get(centerId);
        if (!center.getBookings().containsKey(userId)) {
            System.out.println("Booking does not exists");
            return;
        }
        Booking booking = center.getBookings().get(userId);
        if (booking.getBookingId() != bookingId) {
            System.out.println("invalid booking id");
            return;
        }

        User user = this.userHashMap.get(userId);
        user.setBookingId(null);
        this.userHashMap.put(userId, user);

        center.getBookings().remove(userId);
        Integer currentCapacity = center.getCurrentCapacity().get(booking.getDay());
        center.getCurrentCapacity().put(booking.getDay(), currentCapacity-1);
        System.out.println("booking cancel");
    }
}
