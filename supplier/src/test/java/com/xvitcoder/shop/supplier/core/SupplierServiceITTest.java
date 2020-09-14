package com.xvitcoder.shop.supplier.core;

import com.xvitcoder.shop.supplier.AbstractIntegrationTest;
import com.xvitcoder.shop.supplier.core.domain.Supplier;
import com.xvitcoder.shop.supplier.core.exception.SupplierNotFoundException;
import com.xvitcoder.shop.supplier.core.repository.SupplierRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SupplierServiceITTest extends AbstractIntegrationTest {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplierRepository supplierRepository;

    @BeforeEach
    void initialize() {
        supplierRepository.deleteAll();
    }

    @Test
    void findSuppliers() {
        Supplier supplier = new Supplier();
        supplier.setName("supplier1");
        supplier.setSupplierNumber("12345");

        supplierService.createSupplier(supplier);

        List<Supplier> suppliers = supplierService.findSuppliers();

        assertNotNull(suppliers);
        assertEquals(1, suppliers.size());
    }

    @Test
    void findSuppliers_should_return_empty_list() {
        List<Supplier> suppliers = supplierService.findSuppliers();

        assertNotNull(suppliers);
        assertEquals(0, suppliers.size());
    }

    @Test
    void findSupplierById() {
        Supplier supplier = new Supplier();
        supplier.setName("supplier1");
        supplier.setSupplierNumber("12345");

        supplier = supplierService.createSupplier(supplier);

        supplier = supplierService.findSupplierById(supplier.getId());

        assertNotNull(supplier);
        assertNotNull(supplier.getId());
        assertNotNull(supplier.getCreatedOn());
        assertNull(supplier.getChangedOn());
        assertEquals(supplier.getName(), "supplier1");
        assertEquals(supplier.getSupplierNumber(), "12345");
    }

    @Test
    void findSupplierById_should_throw_SupplierNotFoundException() {
        Exception exception = assertThrows(SupplierNotFoundException.class, () -> supplierService.findSupplierById(1L));
    }

    @Test
    void createSupplier() {
        Supplier supplier = new Supplier();
        supplier.setName("supplier1");
        supplier.setSupplierNumber("12345");

        supplier = supplierService.createSupplier(supplier);

        assertNotNull(supplier);
        assertNotNull(supplier.getId());
        assertNotNull(supplier.getCreatedOn());
        assertNull(supplier.getChangedOn());
        assertEquals(supplier.getName(), "supplier1");
        assertEquals(supplier.getSupplierNumber(), "12345");
    }

    @Test
    void createSupplier_should_throw_ConstraintViolationException() {
        Supplier supplier1 = new Supplier();
        supplier1.setName("supplier1");

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> supplierService.createSupplier(supplier1));
        assertTrue(exception.getConstraintViolations().size() > 0);
        assertTrue(exception.getConstraintViolations().stream()
                .anyMatch(constraintViolation -> constraintViolation.getPropertyPath().toString().equals("supplierNumber")));

        Supplier supplier2 = new Supplier();
        supplier2.setSupplierNumber("12345");

        exception = assertThrows(ConstraintViolationException.class, () -> supplierService.createSupplier(supplier2));
        assertTrue(exception.getConstraintViolations().size() > 0);
        assertTrue(exception.getConstraintViolations().stream()
                .anyMatch(constraintViolation -> constraintViolation.getPropertyPath().toString().equals("name")));
    }

    @Test
    void updateSupplier() {
        Supplier supplier = new Supplier();
        supplier.setName("supplier1");
        supplier.setSupplierNumber("12345");

        supplier = supplierService.createSupplier(supplier);

        assertNotNull(supplier);
        assertNotNull(supplier.getId());
        assertNotNull(supplier.getCreatedOn());
        assertNull(supplier.getChangedOn());
        assertEquals(supplier.getName(), "supplier1");
        assertEquals(supplier.getSupplierNumber(), "12345");

        supplier.setSupplierNumber("54321");
        supplier.setName("supplier2");

        supplier = supplierService.updateSupplier(supplier.getId(), supplier);

        assertNotNull(supplier.getChangedOn());
        assertNotNull(supplier.getChangedOn());
        assertEquals(supplier.getName(), "supplier2");
        assertEquals(supplier.getSupplierNumber(), "54321");
    }

    @Test
    void deleteSupplier() {
        Supplier supplier = new Supplier();
        supplier.setName("supplier1");
        supplier.setSupplierNumber("12345");

        supplier = supplierService.createSupplier(supplier);

        assertEquals(1, supplierService.findSuppliers().size());

        supplierService.deleteSupplier(supplier.getId());

        assertEquals(0, supplierService.findSuppliers().size());
    }
}