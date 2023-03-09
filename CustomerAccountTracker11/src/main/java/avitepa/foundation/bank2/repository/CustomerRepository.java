package avitepa.foundation.bank2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import avitepa.foundation.bank2.model.Account;
import avitepa.foundation.bank2.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByAdharNumber(String adharNumber);

}