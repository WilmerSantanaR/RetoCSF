package com.example.empresa.services;

import com.example.empresa.model.Customer;
import com.example.empresa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(String name, String email, String password) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Long id, String name, String email, String password) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setName(name);
            customer.setEmail(email);
            customer.setPassword(password);
            return customerRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
