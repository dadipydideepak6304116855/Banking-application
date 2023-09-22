package com.wipro.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.bank.entity.Customer;
import com.wipro.bank.exception.CustomerNotFoundException;
import com.wipro.bank.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestCustomerController {

    private MockMvc mockMvc;

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testAddCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);

        when(customerService.addCustomer(any(Customer.class))).thenReturn(customer); //it returns the customer which i added

        mockMvc.perform(post("/api/customer/insertcustomer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(customer))) //converts the customer object to a JSON string using an ObjectMapper
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Long customerId = 1L;
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(customerId);
        updatedCustomer.setCustomerName("Updated Name");

        Customer existingCustomer = new Customer();
        existingCustomer.setId(customerId);

        when(customerService.getCustomerById(customerId)).thenReturn(existingCustomer);
        when(customerService.addCustomer(any(Customer.class))).thenReturn(updatedCustomer);

        mockMvc.perform(put("/api/customer/update/{customerId}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedCustomer)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerName").value("Updated Name"));
    }

    @Test
    public void testUpdateCustomerNotFound() throws Exception {
        Long customerId = 1L;
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(customerId);

        when(customerService.getCustomerById(customerId)).thenReturn(null);

        mockMvc.perform(put("/api/customer/update/{customerId}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedCustomer)))
                .andExpect(status().isNotFound());
    }

  


    @Test
    public void testDeleteCustomer() throws Exception {
        Long customerId = 1L;

        when(customerService.deleteCustomerById(customerId)).thenReturn(true);

        mockMvc.perform(delete("/api/customer/delete/{customerId}", customerId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteAllCustomers() throws Exception {
        mockMvc.perform(delete("/api/customer/deleteallcustomers"))
                .andExpect(status().isNoContent());
    }

  
}
