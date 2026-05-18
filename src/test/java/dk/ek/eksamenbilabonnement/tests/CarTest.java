package dk.ek.eksamenbilabonnement.tests;

import dk.ek.eksamenbilabonnement.models.Car;
import dk.ek.eksamenbilabonnement.repositories.CarRepositoryImpl;
import dk.ek.eksamenbilabonnement.services.BilerService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    static class FakeCarRepository extends CarRepositoryImpl {

        @Override
        public List<Car> findByStatus(String status) {

            List<Car> cars = new ArrayList<>();

            if (status.equals("TILGÆNGELIG")) {

                Car car1 = new Car();
                car1.setBrand("Mercedes");
                car1.setModel("C63");

                Car car2 = new Car();
                car2.setBrand("Ferrari");
                car2.setModel("488");

                cars.add(car1);
                cars.add(car2);
            }

            return cars;
        }
    }

    @Test
    void canGetCarsByStatus() {
        // Arrange
        FakeCarRepository carRepo = new FakeCarRepository();

        BilerService bilerService = new BilerService(carRepo);

        // Act
        List<Car> result = bilerService.getCarsByStatus("TILGÆNGELIG");

        // Assert
        assertEquals(2, result.size());

        assertEquals("Mercedes", result.get(0).getBrand());
        assertEquals("C63", result.get(0).getModel());

        assertEquals("Ferrari", result.get(1).getBrand());
        assertEquals("488", result.get(1).getModel());
    }
}
