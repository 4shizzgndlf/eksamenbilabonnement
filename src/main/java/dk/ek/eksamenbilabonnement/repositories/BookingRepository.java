package dk.ek.eksamenbilabonnement.repositories;

import dk.ek.eksamenbilabonnement.models.Booking;
import java.util.List;
import java.util.Map;

public interface BookingRepository {

    List<Booking> findActiveBookings();

    List<Booking> findFinishedBookings();

    void createBooking(Booking booking);

    void updateBooking(Booking booking);

    void updateCarStatus(int carId, String status);

    int findCarIdByBookingId(int bookingId);

    int countAllBookings();

    List<Integer> findRentedCarIds();

    Map<String, Integer> countBookingsPerUser();

    double getAverageRentalDays();
}