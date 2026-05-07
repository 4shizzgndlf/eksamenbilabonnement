package dk.ek.eksamenbilabonnement.services;

import dk.ek.eksamenbilabonnement.models.Car;
import dk.ek.eksamenbilabonnement.models.User;
import dk.ek.eksamenbilabonnement.repositories.CarRepository;
import dk.ek.eksamenbilabonnement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BilerService {
    private final CarRepository carRepo;

    public BilerService(CarRepository carRepo) {
        this.carRepo = carRepo;
    }

    public List<Car> getCarsByStatus(String status) {
        return carRepo.findByStatus(status);
    }

    public void createCar(Car car) {
        carRepo.insertCar(car);
    }

    public void updateCar(Car car) {
        carRepo.updateCar(car);
    }

    public int getCarCount() {
        return carRepo.countCars();
    }

    public int getAvailableCarCount() {
        return carRepo.findByStatus("TILGÆNGELIG").size();
    }

    public int getRentedCarCount() {
        return carRepo.findByStatus("UDLEJET").size();
    }

    public int getCarsWithDamageCount() {
        return carRepo.countCarsWithDamage();
    }

    public Map<String, Integer> getSoldCarsByBrandAndModel() {
        return carRepo.countSoldCarsByBrandAndModel();
    }

    public int getReturnedCarsCount() {
        return carRepo.countReturnedCars();
    }

    public double getAverageMonthlyPrice() {
        return carRepo.getAverageSubscriptionPrice();
    }

    public double getAveragePurchasePrice() {
        return carRepo.getAveragePurchasePrice();
    }

    public double getAverageRentedCarMonthlyPrice() {
        return carRepo.getAverageSubscriptionPriceForRentedCars();
    }

    public double getTotalRentedMonthlyPrice() {
        return carRepo.getTotalSubscriptionPriceForRentedCars();
    }
}
