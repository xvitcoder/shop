package com.xvitcoder.customer.web.controller;

import com.xvitcoder.customer.core.domain.Customer;
import com.xvitcoder.customer.core.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<?> findCustomers() {
        return new ResponseEntity<>(customerService.findCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> findCustomerById(@PathVariable Long customerId) {
        return new ResponseEntity<>(customerService.findCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createdCustomer(@RequestBody Customer customer) {
        Customer createdCustomer;
        try {
            createdCustomer = customerService.createCustomer(customer);
            log.info("Created new customer: {}", customer);
        } catch (DataIntegrityViolationException e) {
            log.error("Failed to create new customer: {}, error: {}", customer, e.getMessage());
            throw e;
        }

        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
        Customer updatedCustomer;
        try {
            updatedCustomer = customerService.updateCustomer(customerId, customer);
            log.info("Updated customer: {}", customer);
        } catch (DataIntegrityViolationException e) {
            log.error("Failed to update customer: {}, error: {}", customer, e.getMessage());
            throw e;
        }

        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
