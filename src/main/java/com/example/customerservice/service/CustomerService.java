package com.example.customerservice.service;

import com.example.customerservice.model.CustomerInterface;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerInterface getCustomer(String customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    public CustomerInterface createCustomer(String customerId, String customerName) {
        CustomerInterface customer = new CustomerInterface();
        customer.setCustomerId(customerId);
        customer.setCustomerName(customerName);
        return customerRepository.save(customer);
    }
}
