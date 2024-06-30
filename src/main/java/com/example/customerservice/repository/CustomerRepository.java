package com.example.customerservice.repository;

import com.example.customerservice.model.CustomerInterface;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerInterface, Long> {
    CustomerInterface findByCustomerId(String customerId);
}
