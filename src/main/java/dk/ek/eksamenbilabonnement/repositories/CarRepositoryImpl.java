package dk.ek.eksamenbilabonnement.repositories;

import dk.ek.eksamenbilabonnement.models.Car;
import dk.ek.eksamenbilabonnement.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CarRepositoryImpl implements CarRepository {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public List<Car> findDamagedCars() {
        List<Car> cars = new ArrayList<>();

        String sql = "SELECT * FROM cars WHERE status = 'SKADET'";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                cars.add(new Car(
                        rs.getInt("id"),
                        rs.getString("image_url"),
                        rs.getString("vehicle_number"),
                        rs.getString("vin"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("equipment_level"),
                        rs.getDouble("steel_price"),
                        rs.getDouble("registration_fee"),
                        rs.getInt("co2_emissions"),
                        rs.getString("status"),
                        rs.getDouble("subscription_price"),
                        rs.getDouble("purchase_price")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cars;
    }

    public void updateCarStatus(int carId, String status) {
        String sql = "UPDATE cars SET status = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, carId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Car> findByStatus(String status) {
        List<Car> cars = new ArrayList<>();

        String sql = "SELECT * FROM cars WHERE status = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cars.add(new Car(
                        rs.getInt("id"),
                        rs.getString("image_url"),
                        rs.getString("vehicle_number"),
                        rs.getString("vin"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("equipment_level"),
                        rs.getDouble("steel_price"),
                        rs.getDouble("registration_fee"),
                        rs.getInt("co2_emissions"),
                        rs.getString("status"),
                        rs.getDouble("subscription_price"),
                        rs.getDouble("purchase_price")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cars;
    }

    public void insertCar(Car car) {

        String sql = "INSERT INTO cars (image_url, vehicle_number, vin, brand, model, equipment_level, steel_price, registration_fee, co2_emissions, status, subscription_price, purchase_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, car.getImageUrl());
            ps.setString(2, car.getVehicleNumber());
            ps.setString(3, car.getVin());
            ps.setString(4, car.getBrand());
            ps.setString(5, car.getModel());
            ps.setInt(6, car.getEquipmentLevel());
            ps.setDouble(7, car.getSteelPrice());
            ps.setDouble(8, car.getRegistrationFee());
            ps.setInt(9, car.getCo2Emissions());
            ps.setString(10, car.getStatus());
            ps.setDouble(11, car.getSubscriptionPrice());
            ps.setDouble(12, car.getPurchasePrice());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCar(Car car) {
        String sql = "UPDATE cars SET brand=?, model=?, vin=?, vehicle_number=?, subscription_price=?, purchase_price=?, status=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getVin());
            ps.setString(4, car.getVehicleNumber());
            ps.setDouble(5, car.getSubscriptionPrice());
            ps.setDouble(6, car.getPurchasePrice());
            ps.setString(7, car.getStatus());
            ps.setInt(8, car.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countCars() {
        String sql = "SELECT COUNT(*) FROM cars";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public int countCarsWithDamage() {

        String sql = """
                    SELECT COUNT(DISTINCT b.car_id) AS damaged_cars
                    FROM damages d
                    JOIN bookings b ON d.booking_id = b.id
                """;

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt("damaged_cars");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public Map<String, Integer> countSoldCarsByBrandAndModel() {

        Map<String, Integer> result = new HashMap<>();

        String sql = """
                    SELECT brand, model, COUNT(*) AS sold_count
                    FROM cars
                    WHERE status = 'SOLGT'
                    GROUP BY brand, model
                """;

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String carName =
                        rs.getString("brand") + " " +
                                rs.getString("model");

                int count = rs.getInt("sold_count");

                result.put(carName, count);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int countReturnedCars() {

        String sql = "SELECT COUNT(*) FROM cars WHERE status = 'AFLEVERET'";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public double getAverageSubscriptionPrice() {
        String sql = "SELECT AVG(subscription_price) FROM cars";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public double getAveragePurchasePrice() {
        String sql = "SELECT AVG(purchase_price) FROM cars";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public double getAverageSubscriptionPriceForRentedCars() {
        String sql = "SELECT AVG(subscription_price) FROM cars WHERE status = 'UDLEJET'";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public double getTotalSubscriptionPriceForRentedCars() {
        String sql = "SELECT SUM(subscription_price) FROM cars WHERE status = 'UDLEJET'";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
}
