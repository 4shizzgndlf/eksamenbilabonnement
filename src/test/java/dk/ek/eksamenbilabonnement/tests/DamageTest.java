package dk.ek.eksamenbilabonnement.tests;

import dk.ek.eksamenbilabonnement.repositories.*;
import dk.ek.eksamenbilabonnement.services.SkadeService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DamageTest {

    // Simple fake repository (no Mockito)
    static class FakeDamageRepository extends DamageRepositoryImpl {
        boolean damageCreated = false;
        List<String> damages = new ArrayList<>();

        public void createDamage(int bookingId, String description, double price) {
            damageCreated = true;
            damages.add(description);
        }

        public Map<String, Integer> countDamagesPerCar() {

            Map<String, Integer> damages = new HashMap<>();

            damages.put("Mercedes", 2);
            damages.put("Ferrari", 5);
            damages.put("Lamborghini", 1);

            return damages;
        }
    }

    static class FakeBookingRepository extends BookingRepositoryImpl {
        public int findCarIdByBookingId(int bookingId) {
            return 99;
        }
    }

    static class FakeCarRepository extends CarRepositoryImpl {
        String updatedStatus = "";
        int updatedCarId = 0;

        public void updateCarStatus(int carId, String status) {
            updatedStatus = status;
            updatedCarId = carId;
        }
    }

    @Test
    void canCreateDamageAndMarkCarAsDamaged() {
        //Arrange
        FakeDamageRepository damageRepo = new FakeDamageRepository();
        FakeBookingRepository bookingRepo = new FakeBookingRepository();
        FakeCarRepository carRepo = new FakeCarRepository();

        SkadeService skadeService = new SkadeService(bookingRepo, carRepo, damageRepo);

        //Act
        skadeService.createDamageAndMarkCarAsDamaged(
                1,
                "Broken mirror",
                2500
        );

        //Assert
        assertEquals("SKADET", carRepo.updatedStatus);
        assertEquals(1, damageRepo.damages.size());
        assertEquals("Broken mirror", damageRepo.damages.get(0));
    }

    @Test
    void canGetCarId() {
        //Arrange
        FakeDamageRepository damageRepo = new FakeDamageRepository();
        FakeBookingRepository bookingRepo = new FakeBookingRepository();
        FakeCarRepository carRepo = new FakeCarRepository();

        SkadeService skadeService = new SkadeService(bookingRepo, carRepo, damageRepo);

        //Act
        skadeService.createDamageAndMarkCarAsDamaged(
                1,
                "Broken mirror",
                2500
        );

        //Assert
        assertEquals(99, carRepo.updatedCarId);
    }

    @Test
    void canGetDamagesPerCar() {
        // Arrange
        FakeDamageRepository damageRepo = new FakeDamageRepository();
        FakeBookingRepository bookingRepo = new FakeBookingRepository();
        FakeCarRepository carRepo = new FakeCarRepository();

        SkadeService skadeService =
                new SkadeService(bookingRepo, carRepo, damageRepo);

        // Act
        Map<String, Integer> result = skadeService.getDamagesPerCar();

        // Assert
        assertEquals(3, result.size());
        assertEquals(2, result.get("Mercedes"));
        assertEquals(5, result.get("Ferrari"));
        assertEquals(1, result.get("Lamborghini"));
    }
}
