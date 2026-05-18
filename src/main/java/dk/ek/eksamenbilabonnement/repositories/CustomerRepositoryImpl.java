package dk.ek.eksamenbilabonnement.repositories;

import dk.ek.eksamenbilabonnement.models.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    public List<Customer> findAll() {

        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM customers";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Customer c = new Customer(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("zip_code"),
                        rs.getString("drivers_license"),
                        rs.getString("cpr"),
                        rs.getTimestamp("created_at")
                );

                customers.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching customers", e);
        }

        return customers;
    }

    public Customer findById(int id) {

        String sql = "SELECT * FROM customers WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("zip_code"),
                        rs.getString("drivers_license"),
                        rs.getString("cpr"),
                        rs.getTimestamp("created_at")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void createCustomer(Customer customer) {

        String sql = """
        INSERT INTO customers
        (first_name, last_name, email, phone_number,
         address, city, zip_code, drivers_license, cpr)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhoneNumber());
            ps.setString(5, customer.getAddress());
            ps.setString(6, customer.getCity());
            ps.setString(7, customer.getZipCode());
            ps.setString(8, customer.getDriversLicense());
            ps.setString(9, customer.getCpr());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error creating customer", e);
        }
    }

    public void updateCustomer(Customer customer) {

        String sql = "UPDATE customers SET first_name=?, last_name=?, email=?, phone_number=?, address=?, city=?, zip_code=?, drivers_license=?, cpr=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhoneNumber());
            ps.setString(5, customer.getAddress());
            ps.setString(6, customer.getCity());
            ps.setString(7, customer.getZipCode());
            ps.setString(8, customer.getDriversLicense());
            ps.setString(9, customer.getCpr());
            ps.setInt(10, customer.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating customer", e);
        }
    }

    public void deleteCustomer(int id) {

        String sql = "DELETE FROM customers WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting customer", e);
        }
    }
}