package dk.ek.eksamenbilabonnement.repositories;

import dk.ek.eksamenbilabonnement.models.Car;
import java.util.List;
import java.util.Map;

public interface CarRepository {

    List<Car> findDamagedCars();

    List<Car> findByStatus(String status);

    void insertCar(Car car);

    void updateCar(Car car);

    void updateCarStatus(int carId, String status);

    int countCars();

    int countCarsWithDamage();

    Map<String, Integer> countSoldCarsByBrandAndModel();

    int countReturnedCars();

    double getAverageSubscriptionPrice();

    double getAveragePurchasePrice();

    double getAverageSubscriptionPriceForRentedCars();

    double getTotalSubscriptionPriceForRentedCars();
}