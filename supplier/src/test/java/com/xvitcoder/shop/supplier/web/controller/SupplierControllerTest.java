package com.xvitcoder.shop.supplier.web.controller;

import com.xvitcoder.shop.supplier.core.SupplierService;
import com.xvitcoder.shop.supplier.core.domain.Supplier;
import com.xvitcoder.shop.supplier.core.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SupplierController.class)
class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplierService supplierService;

    @Test
    void findSuppliers() throws Exception {
        mockMvc.perform(get("/api/suppliers"))
                .andExpect(status().isOk());
    }

    @Test
    void findSupplierById() {
    }

    @Test
    void createSupplier() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("supplier1");
        supplier.setSupplierNumber("12345");

        mockMvc.perform(post("/api/suppliers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("supplier1"));

    }

    @Test
    void updateSupplier() {
    }

    @Test
    void deleteSupplier() {
    }
}