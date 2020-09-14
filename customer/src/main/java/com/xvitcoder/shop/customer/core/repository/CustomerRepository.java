package com.xvitcoder.shop.customer.core.repository;

import com.xvitcoder.shop.customer.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
