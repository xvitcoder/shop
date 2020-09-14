package com.xvitcoder.shop.customer.core.service;

import com.xvitcoder.shop.customer.core.domain.Customer;
import com.xvitcoder.shop.customer.core.exception.CustomerNotFoundException;
import com.xvitcoder.shop.customer.core.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @Transactional
    public Customer createCustomer(Customer customer) {
        customer.setId(null);
        customer.setCreatedOn(new Date());

        customer = customerRepository.save(customer);

        return customer;
    }

    @Transactional
    public Customer updateCustomer(Long customerId, Customer customer) {
        Customer existingCustomer = findCustomerById(customerId);

        existingCustomer.setName(customer.getName());
        existingCustomer.setCustomerNumber(customer.getCustomerNumber());
        existingCustomer.setChangedOn(new Date());

        existingCustomer = customerRepository.save(existingCustomer);

        return existingCustomer;
    }

    @Transactional
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
