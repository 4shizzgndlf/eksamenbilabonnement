package dk.ek.eksamenbilabonnement.services;

import dk.ek.eksamenbilabonnement.models.Booking;
import dk.ek.eksamenbilabonnement.models.Car;
import dk.ek.eksamenbilabonnement.models.Damage;
import dk.ek.eksamenbilabonnement.models.User;
import dk.ek.eksamenbilabonnement.repositories.BookingRepository;
import dk.ek.eksamenbilabonnement.repositories.CarRepository;
import dk.ek.eksamenbilabonnement.repositories.DamageRepository;
import dk.ek.eksamenbilabonnement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SkadeService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final DamageRepository damageRepository;

    public SkadeService(BookingRepository bookingRepository, CarRepository carRepository, DamageRepository damageRepository) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.damageRepository = damageRepository;
    }

    public List<Car> getDamagedCars() {
        return carRepository.findDamagedCars();
    }

    public List<Damage> getAllDamages() {
        return damageRepository.findAllDamages();
    }

    public void createDamageAndMarkCarAsDamaged(int bookingId, String description, double price) {

        // 1. Insert damage
        damageRepository.createDamage(bookingId, description, price);

        // 2. Find car_id from booking
        int carId = bookingRepository.findCarIdByBookingId(bookingId);

        // 3. Update car status → SKADET
        carRepository.updateCarStatus(carId, "SKADET");
    }

    public void deleteDamageAndMarkCarAvailable(int damageId, int bookingId) {

        int carId = bookingRepository.findCarIdByBookingId(bookingId);

        // 1. Delete damage
        damageRepository.deleteDamage(damageId);

        // 2. Check if ANY damages still exist for this car
        boolean hasMoreDamages = damageRepository.existsDamageForCar(carId);

        // 3. Only update if no damages left
        if (!hasMoreDamages) {
            carRepository.updateCarStatus(carId, "TILGÆNGELIG");
        }
    }

    public int getDamageCount() {
        return damageRepository.countDamages();
    }

    public Map<String, Integer> getDamagesPerCar() {
        return damageRepository.countDamagesPerCar();
    }

    public double getAverageDamagePrice() {
        return damageRepository.getAverageDamagePrice();
    }

    public Map<String, Double> getTotalDamagePricePerCar() {
        return damageRepository.getTotalDamagePricePerCar();
    }
}
