package test;

import test.service.Vacination;

public class Driver {
    public static void main(String[] args) {
        new Driver().run();
    }

    void run() {
        Vacination covidVacination = new Vacination();

        String state = "karnataka";
        String district = "bangalore";

        // add user
        covidVacination.addUser(1, "harry", "male", 35, state, district);
        covidVacination.addUser(2, "peter", "male", 44, state, district);
        covidVacination.addUser(3, "john", "male", 35, state, district);

        covidVacination.addVacinationCenter(state, district, 1);
        covidVacination.addVacinationCenter(state, district, 2);

        covidVacination.addCapacity(1, 1, 1);
        covidVacination.addCapacity(2, 3, 10);
        covidVacination.addCapacity(1, 2, 10);

        int bookingId = covidVacination.bookVacination(1, 1, 1);

        covidVacination.bookVacination(1, 1, 2);
        covidVacination.bookVacination(1, 2, 2);

        covidVacination.bookVacination(2, 3, 3);

        covidVacination.listVacinationCenters(district);


        covidVacination.listAllBookings(1, 1);

        covidVacination.cancelBooking(1, bookingId, 1);

        covidVacination.bookVacination(1, 2, 1);

        covidVacination.cancelBooking(1, 323, 1);
    }
}
