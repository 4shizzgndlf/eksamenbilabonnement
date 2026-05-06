package dk.ek.eksamenbilabonnement.repositories;

import dk.ek.eksamenbilabonnement.models.Booking;
import java.util.List;

public interface BookingRepository {

    List<Booking> findActiveBookings();

    List<Booking> findFinishedBookings();

    void createBooking(Booking booking);

    void updateBooking(Booking booking);

    void updateCarStatus(int carId, String status);

    int findCarIdByBookingId(int bookingId);
}