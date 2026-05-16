package dk.ek.eksamenbilabonnement.repositories;

import dk.ek.eksamenbilabonnement.models.Customer;

import java.util.List;

public interface CustomerRepository {

    // Get all customers
    List<Customer> findAll();

    // Find one customer by ID
    Customer findById(int id);

    // Create a new customer
    void createCustomer(Customer customer);

    // Update existing customer
    void updateCustomer(Customer customer);

    // Delete customer by ID
    void deleteCustomer(int id);
}