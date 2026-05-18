package dk.ek.eksamenbilabonnement.services;

import dk.ek.eksamenbilabonnement.models.Booking;
import dk.ek.eksamenbilabonnement.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LejeaftaleService {

    private final BookingRepository bookingRepo;

    public LejeaftaleService(BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public int getAllBookingsCount() {
        return bookingRepo.countAllBookings();
    }

    public List<Booking> getActiveBookings() {
        return bookingRepo.findActiveBookings();
    }

    public int getActiveBookingsCount() {
        return bookingRepo.findActiveBookings().size();
    }

    public List<Booking> getFinishedBookings() {
        return bookingRepo.findFinishedBookings();
    }

    public int getFinishedBookingsCount() {
        return bookingRepo.findFinishedBookings().size();
    }

    public void createBooking(Booking booking) {
        bookingRepo.createBooking(booking);

        // When booking is created → car becomes rented
        bookingRepo.updateCarStatus(booking.getCarId(), "UDLEJET");
    }

    public void updateBooking(Booking booking) {
        bookingRepo.updateBooking(booking);

        String status = booking.getStatus().trim().toUpperCase();

        if ("AKTIV".equals(status)) {
            bookingRepo.updateCarStatus(booking.getCarId(), "UDLEJET");
        }

        if ("FÆRDIG".equals(status)) {
            bookingRepo.updateCarStatus(booking.getCarId(), "TILGÆNGELIG");
        }
    }

    public List<Integer> getRentedCarIds() {
        return bookingRepo.findRentedCarIds();
    }

    public Map<String, Integer> getBookingsPerUser() {
        return bookingRepo.countBookingsPerUser();
    }

    public double getAverageMonthlyPrice() {

        List<Booking> active = bookingRepo.findActiveBookings();
        List<Booking> finished = bookingRepo.findFinishedBookings();

        double total = 0;
        int count = 0;

        for (Booking b : active) {
            total += b.getMonthlyPrice();
            count++;
        }

        for (Booking b : finished) {
            total += b.getMonthlyPrice();
            count++;
        }

        if (count == 0) {
            return 0;
        }

        return total / count;
    }

    public double getAverageRentalDays() {
        return bookingRepo.getAverageRentalDays();
    }

    public double getCalculatedMonthlyPrice(Booking b) {
        int startYear = b.getStartDate().getYear();
        int startMonth = b.getStartDate().getMonth();
        int endYear = b.getEndDate().getYear();
        int endMonth = b.getEndDate().getMonth();

        int monthDiff = (endYear-startYear) * 12 + (endMonth-startMonth);

        if (monthDiff==0) {
            monthDiff += 1;
        }

        return monthDiff * b.getMonthlyPrice();
    }
}