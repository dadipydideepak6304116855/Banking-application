package com.wipro.bank.repository;
import java.util.List;
import com.wipro.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	public List<Account> findByAccountType(String AccountType);


}
