package dk.ek.eksamenbilabonnement.tests;

import dk.ek.eksamenbilabonnement.models.Booking;
import dk.ek.eksamenbilabonnement.repositories.BookingRepositoryImpl;
import dk.ek.eksamenbilabonnement.services.LejeaftaleService;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingTest {
    static class FakeBookingRepository extends BookingRepositoryImpl {
        Booking updatedBooking;
        int updatedCarId;
        String updatedCarStatus;

        public void updateBooking(Booking booking) {
            updatedBooking = booking;
        }

        public void updateCarStatus(int carId, String status) {
            updatedCarId = carId;
            updatedCarStatus = status;
        }

        public List<Booking> findActiveBookings() {

            List<Booking> active = new ArrayList<>();

            active.add(new Booking(
                    1,
                    10,
                    1,
                    "Emil Kristensen",
                    Date.valueOf("2026-05-01"),
                    Date.valueOf("2026-06-01"),
                    4000,
                    "AKTIV"
            ));

            active.add(new Booking(
                    2,
                    11,
                    2,
                    "Lucas Simonsen",
                    Date.valueOf("2026-05-01"),
                    Date.valueOf("2026-06-01"),
                    6000,
                    "AKTIV"
            ));

            return active;
        }

        public List<Booking> findFinishedBookings() {

            List<Booking> finished = new ArrayList<>();

            finished.add(new Booking(
                    3,
                    12,
                    3,
                    "Mikkel Jørgensen",
                    Date.valueOf("2026-04-01"),
                    Date.valueOf("2026-05-01"),
                    5000,
                    "FÆRDIG"
            ));

            return finished;
        }
    }

    @Test
    void canUpdateBooking() {
        //Arrange
        FakeBookingRepository fakeRepo = new FakeBookingRepository();

        LejeaftaleService service = new LejeaftaleService(fakeRepo);

        Booking booking = new Booking(
                1,
                99,
                5,
                "Emil Kristensen",
                Date.valueOf("2026-05-01"),
                Date.valueOf("2026-06-01"),
                4500,
                "AKTIV"
        );

        //Act
        service.updateBooking(booking);

        //Assert
        assertEquals("Emil Kristensen", fakeRepo.updatedBooking.getCustomerName());
        assertEquals(99, fakeRepo.updatedBooking.getCarId());
        assertEquals("AKTIV", fakeRepo.updatedBooking.getStatus());
    }

    @Test
    void canMarkCarAsRentedWhenBookingIsActive() {
        // Arrange
        FakeBookingRepository fakeRepo = new FakeBookingRepository();

        LejeaftaleService service = new LejeaftaleService(fakeRepo);

        Booking booking = new Booking(
                1,
                99,
                5,
                "Emil",
                Date.valueOf("2026-05-01"),
                Date.valueOf("2026-06-01"),
                4500,
                "AKTIV"
        );

        // Act
        service.updateBooking(booking);

        // Assert
        assertEquals(99, fakeRepo.updatedCarId);
        assertEquals("UDLEJET", fakeRepo.updatedCarStatus);
    }

    @Test
    void canMarkCarAsAvailableWhenBookingIsFinished() {
        // Arrange
        FakeBookingRepository fakeRepo = new FakeBookingRepository();

        LejeaftaleService service = new LejeaftaleService(fakeRepo);

        Booking booking = new Booking(
                1,
                99,
                5,
                "Emil",
                Date.valueOf("2026-05-01"),
                Date.valueOf("2026-06-01"),
                4500,
                "FÆRDIG"
        );

        // Act
        service.updateBooking(booking);

        // Assert
        assertEquals(99, fakeRepo.updatedCarId);
        assertEquals("TILGÆNGELIG", fakeRepo.updatedCarStatus);
    }

    @Test
    void canCalculateAverageMonthlyPrice() {
        // Arrange
        FakeBookingRepository fakeRepo = new FakeBookingRepository();

        LejeaftaleService service = new LejeaftaleService(fakeRepo);

        // Act
        double average = service.getAverageMonthlyPrice();

        // Assert
        assertEquals(5000, average);
    }
}
