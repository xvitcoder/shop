package com.xvitcoder.shop.supplier.web.controller;

import com.xvitcoder.shop.supplier.core.SupplierService;
import com.xvitcoder.shop.supplier.core.domain.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<?> findSuppliers() {
        return new ResponseEntity<>(supplierService.findSuppliers(), HttpStatus.OK);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<?> findSupplierById(@PathVariable Long supplierId) {
        return new ResponseEntity<>(supplierService.findSupplierById(supplierId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSupplier(@RequestBody Supplier supplier) {
        Supplier createdSupplier;
        try {
            createdSupplier = supplierService.createSupplier(supplier);
            log.info("Created new supplier: {}", supplier);
        } catch (DataIntegrityViolationException e) {
            log.error("Failed to create new supplier: {}, error: {}", supplier, e.getMessage());
            throw e;
        }

        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<?> updateSupplier(@PathVariable Long supplierId, @RequestBody Supplier supplier) {
        Supplier updatedSupplier;
        try {
            updatedSupplier = supplierService.updateSupplier(supplierId, supplier);
            log.info("Updated supplier: {}", supplier);
        } catch (DataIntegrityViolationException e) {
            log.error("Failed to update supplier: {}, error: {}", supplier, e.getMessage());
            throw e;
        }

        return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long supplierId) {
        supplierService.deleteSupplier(supplierId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
