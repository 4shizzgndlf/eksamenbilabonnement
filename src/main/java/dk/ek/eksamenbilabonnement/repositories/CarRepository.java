package dk.ek.eksamenbilabonnement.repositories;

import dk.ek.eksamenbilabonnement.models.Car;
import java.util.List;

public interface CarRepository {

    List<Car> findDamagedCars();

    List<Car> findByStatus(String status);

    void insertCar(Car car);

    void updateCar(Car car);

    void updateCarStatus(int carId, String status);
}