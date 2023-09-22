package com.wipro.bank.controller;

import java.util.List;
import com.wipro.bank.entity.Customer;
import com.wipro.bank.exception.CustomerNotFoundException;
import com.wipro.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
    @Autowired
    private CustomerService customerService;
    
    
    //To insert new customer
    @PostMapping("/insertcustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
    	 Customer savedCustomer = customerService.addCustomer(customer);
         // Return a ResponseEntity with the saved customer and an HTTP status code.
         return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
         
     }
    
    
    
    // To Update an existing customer by ID
    @PutMapping("/update/{customerId}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long customerId,
            @RequestBody Customer updatedCustomer) {
    	
    	
        // Check if the customer with the given ID exists
        Customer existingCustomer = customerService.getCustomerById(customerId);

        if (existingCustomer == null) {
            // Customer not found, return 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
     // Update the existing customer with the data from updatedCustomer
        existingCustomer.setCustomerName(updatedCustomer.getCustomerName());
        existingCustomer.setCustomerAddress(updatedCustomer.getCustomerAddress());
        existingCustomer.setAadharNumber(updatedCustomer.getAadharNumber());
        
     // Save the updated customer to the database
        Customer savedCustomer = customerService.addCustomer(existingCustomer);
        
        // Return the updated customer with HTTP 200 OK status
        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }
    
    
    
    
    //Get customer details by id
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    
         
    // Get all customers
    @GetMapping("/getallcustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    
    
    
    
    
    
    // Delete customer by ID
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        boolean deleted = customerService.deleteCustomerById(customerId);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    
    // Delete all customers
    @DeleteMapping("/deleteallcustomers")
    public ResponseEntity<Void> deleteAllCustomers() {
        customerService.deleteAllCustomers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    
  
    
 }
