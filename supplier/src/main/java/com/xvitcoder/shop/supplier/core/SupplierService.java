package com.xvitcoder.shop.supplier.core;

import com.xvitcoder.shop.supplier.core.domain.Supplier;
import com.xvitcoder.shop.supplier.core.exception.SupplierNotFoundException;
import com.xvitcoder.shop.supplier.core.repository.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional(readOnly = true)
    public List<Supplier> findSuppliers() {
        return supplierRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Supplier findSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId)
                .orElseThrow(() -> new SupplierNotFoundException(supplierId));
    }

    @Transactional
    public Supplier createSupplier(Supplier supplier) {
        supplier.setId(null);
        supplier.setCreatedOn(new Date());

        supplier = supplierRepository.save(supplier);

        return supplier;
    }

    @Transactional
    public Supplier updateSupplier(Long supplierId, Supplier supplier) {
        Supplier existingSupplier = findSupplierById(supplierId);

        existingSupplier.setName(supplier.getName());
        existingSupplier.setSupplierNumber(supplier.getSupplierNumber());
        existingSupplier.setChangedOn(new Date());

        existingSupplier = supplierRepository.save(existingSupplier);

        return existingSupplier;
    }

    @Transactional
    public void deleteSupplier(Long supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
