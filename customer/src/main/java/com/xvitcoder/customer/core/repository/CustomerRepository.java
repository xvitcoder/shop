package com.xvitcoder.customer.core.repository;

import com.xvitcoder.customer.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
