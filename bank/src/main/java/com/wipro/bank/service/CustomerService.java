package com.wipro.bank.service;

import java.util.List;
import java.util.Optional;
import com.wipro.bank.entity.Customer;
import com.wipro.bank.repository.AccountRepository;
import com.wipro.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	@Autowired
    private  CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;

   // @Autowired
   // public CustomerService(CustomerRepository customerRepository) {
     //   this.customerRepository = customerRepository;
    //}
    
    //add new customer
    public Customer addCustomer(Customer customer) {
        // Add any additional business logic/validation here if needed
        return customerRepository.save(customer);
    }
    
    //get customer by id
	public Customer getCustomerById(Long customerId) {
		// TODO Auto-generated method stub
		return customerRepository.findById(customerId).orElse(null);
	}
	
	 // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    
   // Delete customer by ID
    public boolean deleteCustomerById(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            customerRepository.delete(customerOptional.get());
            return true; // Customer was deleted
        } else {
            return false; // Customer with the given ID was not found
        }
    }
    
    //get all customers with accounts
    public List<Customer> getAllCustomersWithAccounts() {
        // Fetch customers with accounts from the repository
        return customerRepository.findAll();
    }
    
    
     // Delete all customers
    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }
}
