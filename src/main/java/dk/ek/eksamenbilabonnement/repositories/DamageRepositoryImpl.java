package dk.ek.eksamenbilabonnement.repositories;

import dk.ek.eksamenbilabonnement.models.Damage;
import dk.ek.eksamenbilabonnement.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
}
