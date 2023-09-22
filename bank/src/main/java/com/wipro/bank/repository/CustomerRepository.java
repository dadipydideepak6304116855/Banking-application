package com.wipro.bank.repository;

import java.util.List;

import com.wipro.bank.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("SELECT c FROM Customer c JOIN c.accounts a WHERE a.accountType = :accountType")
    List<Customer> findByAccountType(@Param("accountType") String accountType);
}
