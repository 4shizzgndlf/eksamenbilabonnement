package dk.ek.eksamenbilabonnement.repositories;

import dk.ek.eksamenbilabonnement.models.Damage;
import dk.ek.eksamenbilabonnement.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DamageRepositoryImpl implements DamageRepository {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public List<Damage> findAllDamages() {
        List<Damage> damages = new ArrayList<>();

        String sql = "SELECT * FROM damages";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                damages.add(new Damage(
                        rs.getInt("id"),
                        rs.getInt("booking_id"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getBoolean("approved"),
                        rs.getTimestamp("reported_at")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return damages;
    }

    public void createDamage(int bookingId, String description, double price) {
        String sql = "INSERT INTO damages (booking_id, description, price) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookingId);
            ps.setString(2, description);
            ps.setDouble(3, price);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteDamage(int damageId) {
        String sql = "DELETE FROM damages WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, damageId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsDamageForCar(int carId) {
        String sql = """
        SELECT COUNT(*) 
        FROM damages d
        JOIN bookings b ON d.booking_id = b.id
        WHERE b.car_id = ?
    """;

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, carId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public int countDamages() {

        String sql = "SELECT COUNT(*) FROM damages";

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

    public Map<String, Integer> countDamagesPerCar() {

        Map<String, Integer> result = new HashMap<>();

        String sql = """
        SELECT c.brand, c.model, COUNT(d.id) AS damage_count
        FROM damages d
        JOIN bookings b ON d.booking_id = b.id
        JOIN cars c ON b.car_id = c.id
        GROUP BY c.id, c.brand, c.model
    """;

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String carName = rs.getString("brand") + " " + rs.getString("model");
                int count = rs.getInt("damage_count");

                result.put(carName, count);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public double getAverageDamagePrice() {

        String sql = "SELECT AVG(price) FROM damages";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0.0;
    }

    public Map<String, Double> getTotalDamagePricePerCar() {

        Map<String, Double> result = new HashMap<>();

        String sql = """
        SELECT c.brand, c.model, SUM(d.price) AS total_damage_price
        FROM damages d
        JOIN bookings b ON d.booking_id = b.id
        JOIN cars c ON b.car_id = c.id
        GROUP BY c.id, c.brand, c.model
    """;

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String carName = rs.getString("brand") + " " + rs.getString("model");
                double total = rs.getDouble("total_damage_price");

                result.put(carName, total);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
