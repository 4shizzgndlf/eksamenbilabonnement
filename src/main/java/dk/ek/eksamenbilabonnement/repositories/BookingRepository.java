package dk.ek.eksamenbilabonnement.repositories;

import dk.ek.eksamenbilabonnement.models.Booking;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingRepository {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public List<Booking> findActiveBookings() {
        List<Booking> bookings = new ArrayList<>();

        String sql = "SELECT * FROM bookings WHERE status = 'AKTIV'";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("id"),
                        rs.getInt("car_id"),
                        rs.getInt("user_id"),
                        rs.getString("customer_name"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getDouble("monthly_price"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bookings;
    }

    public List<Booking> findFinishedBookings() {
        List<Booking> bookings = new ArrayList<>();

        String sql = "SELECT * FROM bookings WHERE status = 'FÆRDIG'";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("id"),
                        rs.getInt("car_id"),
                        rs.getInt("user_id"),
                        rs.getString("customer_name"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getDouble("monthly_price"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bookings;
    }

    public void createBooking(Booking booking) {
        String sql = "INSERT INTO bookings (car_id, user_id, customer_name, start_date, end_date, monthly_price, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, booking.getCarId());

            if (booking.getUserId() == 0) {
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setInt(2, booking.getUserId());
            }

            ps.setString(3, booking.getCustomerName());
            ps.setDate(4, booking.getStartDate());
            ps.setDate(5, booking.getEndDate());
            ps.setDouble(6, booking.getMonthlyPrice());
            ps.setString(7, booking.getStatus());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBooking(Booking booking) {
        String sql = "UPDATE bookings SET car_id=?, user_id=?, customer_name=?, start_date=?, end_date=?, monthly_price=?, status=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, booking.getCarId());

            if (booking.getUserId() == 0) {
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setInt(2, booking.getUserId());
            }

            ps.setString(3, booking.getCustomerName());
            ps.setDate(4, booking.getStartDate());
            ps.setDate(5, booking.getEndDate());
            ps.setDouble(6, booking.getMonthlyPrice());
            ps.setString(7, booking.getStatus());
            ps.setInt(8, booking.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}