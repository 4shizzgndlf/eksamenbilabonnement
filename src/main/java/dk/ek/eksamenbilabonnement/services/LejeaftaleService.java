package dk.ek.eksamenbilabonnement.services;

import dk.ek.eksamenbilabonnement.models.Booking;
import dk.ek.eksamenbilabonnement.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LejeaftaleService {

    private final BookingRepository bookingRepo;

    public LejeaftaleService(BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public List<Booking> getActiveBookings() {
        return bookingRepo.findActiveBookings();
    }

    public List<Booking> getFinishedBookings() {
        return bookingRepo.findFinishedBookings();
    }

    public void createBooking(Booking booking) {
        bookingRepo.createBooking(booking);
    }

    public void updateBooking(Booking booking) {
        bookingRepo.updateBooking(booking);
    }
}