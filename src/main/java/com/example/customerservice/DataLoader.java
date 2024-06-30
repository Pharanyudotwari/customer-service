package com.example.customerservice;

import com.example.customerservice.model.CustomerInterface;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Autowired
    public DataLoader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        CustomerInterface customer = new CustomerInterface();
        customer.setCustomerId("X101");
        customer.setCustomerName("John Doe");
        customerRepository.save(customer);
    }
}
