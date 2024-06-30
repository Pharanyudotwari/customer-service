package com.example.customerservice.controller;

import com.example.customerservice.model.CustomerInterface;
import com.example.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getCustomer")
    public ResponseEntity<?> getCustomerName(@RequestParam String customerId) {
        CustomerInterface customer = customerService.getCustomer(customerId);
        if (customer != null) {
            CustomerResponse response = new CustomerResponse(customer.getCustomerId(), customer.getCustomerName());
            return ResponseEntity.ok(response);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Customer with ID " + customerId + " not found.");
            return ResponseEntity.status(404).body(errorResponse);
        }
    }

    @PostMapping("/getCustomer")
    public ResponseEntity<?> getCustomerById(@RequestBody CustomerRequest request) {
        CustomerInterface customer = customerService.getCustomer(request.getCustomerId());
        if (customer != null) {
            CustomerResponse response = new CustomerResponse(customer.getCustomerId(), customer.getCustomerName());
            return ResponseEntity.ok(response);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Customer with ID " + request.getCustomerId() + " not found.");
            return ResponseEntity.status(404).body(errorResponse);
        }
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequest request) {
        CustomerInterface existingCustomer = customerService.getCustomer(request.getCustomerId());
        if (existingCustomer != null) {
            ErrorResponse errorResponse = new ErrorResponse("Customer with ID " + request.getCustomerId() + " already exists.");
            return ResponseEntity.status(409).body(errorResponse);
        }
        CustomerInterface newCustomer = customerService.createCustomer(request.getCustomerId(), request.getCustomerName());
        CustomerResponse response = new CustomerResponse(newCustomer.getCustomerId(), newCustomer.getCustomerName());
        return ResponseEntity.ok().body(response);
    }

    @Data
    @AllArgsConstructor
    public static class CustomerResponse {
        private String customerId;
        private String customerName;
    }

    @Data
    public static class CustomerRequest {
        private String customerId;
        private String customerName;
    }

    @Data
    @AllArgsConstructor
    public static class ErrorResponse {
        private String message;
    }
}
