package dk.ek.eksamenbilabonnement.services;

import dk.ek.eksamenbilabonnement.models.Car;
import dk.ek.eksamenbilabonnement.models.User;
import dk.ek.eksamenbilabonnement.repositories.CarRepository;
import dk.ek.eksamenbilabonnement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
