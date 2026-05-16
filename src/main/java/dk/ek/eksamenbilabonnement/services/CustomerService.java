package dk.ek.eksamenbilabonnement.services;

import dk.ek.eksamenbilabonnement.models.Customer;
import dk.ek.eksamenbilabonnement.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // GET all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // CREATE customer
    public void createCustomer(Customer customer) {
        customerRepository.createCustomer(customer);
    }

    // UPDATE customer
    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }

    // DELETE customer
    public void deleteCustomer(int id) {
        customerRepository.deleteCustomer(id);
    }

    // FIND by id
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id);
    }
}