package com.xvitcoder.shop.supplier.core;

import com.xvitcoder.shop.supplier.AbstractIntegrationTest;
import com.xvitcoder.shop.supplier.core.domain.Supplier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SupplierServiceTest extends AbstractIntegrationTest {

    @Autowired
    private SupplierService supplierService;

    @Test
    void findSuppliers() {
    }

    @Test
    void findSupplierById() {
    }

    @Test
    void createSupplier() {
        Supplier supplier = new Supplier();
        supplier.setName("supplier1");
        supplier.setSupplierNumber("12345");

        supplier = supplierService.createSupplier(supplier);

        assertNotNull(supplier);
        assertNotNull(supplier.getId());
        assertEquals(supplier.getName(), "supplier1");
        assertEquals(supplier.getSupplierNumber(), "12345");
    }

    @Test
    void updateSupplier() {
    }

    @Test
    void deleteSupplier() {
    }
}