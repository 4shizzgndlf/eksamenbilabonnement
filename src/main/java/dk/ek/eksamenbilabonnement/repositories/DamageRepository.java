package dk.ek.eksamenbilabonnement.repositories;

import dk.ek.eksamenbilabonnement.models.Damage;
import java.util.List;
import java.util.Map;

public interface DamageRepository {

    List<Damage> findAllDamages();

    void createDamage(int bookingId, String description, double price);

    void deleteDamage(int damageId);

    boolean existsDamageForCar(int carId);

    int countDamages();

    Map<String, Integer> countDamagesPerCar();

    double getAverageDamagePrice();

    Map<String, Double> getTotalDamagePricePerCar();
}