package avitepa.foundation.bank2.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import avitepa.foundation.bank2.exception.CustomerNotFoundException;
import avitepa.foundation.bank2.model.Account;
import avitepa.foundation.bank2.model.Customer;
import avitepa.foundation.bank2.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;


	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() throws CustomerNotFoundException
	{
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
			return  customerService.getCustomerById(id);
		
	}
	
	
	@GetMapping("/adharNumber/{adharNumber}")
	public ResponseEntity<Customer> findCustomerByAdharNumber(@PathVariable String adharNumber) {
		return customerService.findByAdharNumber(adharNumber);
			

	}
	
	@GetMapping("/AccountNumber/{accountNumber}")
	public ResponseEntity<Account> findAccounutByAccountNumber(@PathVariable String accountNumber) {
			return customerService.findByAccountNumber(accountNumber);

	}
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer newCustomer = customerService.createCustomer(customer);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		Customer updatedCustomer = customerService.updateCustomer(customer);
			return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	
	}


	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id){	
		Customer customer =customerService.deleteCustomer(id);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	@PostMapping("/transferfundsByAccount/{fromAccountNumber}/{toAccountNumber}/{amount}")
	public ResponseEntity<Account> transferFundsByAccNo(@PathVariable String fromAccountNumber, @PathVariable String toAccountNumber,
			@PathVariable Double amount) {
		Account updatedAccount = customerService.transferFunds(fromAccountNumber, toAccountNumber, amount);
		return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
	}


	
	
	//this is updated and refined code ....^

//	@GetMapping("/getAllCustomers")
//	public ResponseEntity<List<Customer>> getAllCustomers() throws CustomerNotFoundException
//	{
//		return customerService.getAllCustomers();
//	}
//	
//	@GetMapping("/getById/{id}")
//	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
//			return  customerService.getCustomerById(id);
//		
//	}
//	
//	
//	@GetMapping("/adharNumber/{adharNumber}")
//	public ResponseEntity<Customer> findCustomerByAdharNumber(@PathVariable String adharNumber) {
//		return customerService.findByAdharNumber(adharNumber);
//			
//
//	}
//	
//	@GetMapping("/AccountNumber/{accountNumber}")
//	public ResponseEntity<Account> findAccounutByAccountNumber(@PathVariable String accountNumber) {
//			return customerService.findByAccountNumber(accountNumber);
//
//	}
//	@PostMapping("/addCustomer")
//	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
//		Customer newCustomer = customerService.createCustomer(customer);
//		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
//	}
//	
//	@PutMapping("/edit")
//	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
//		Customer updatedCustomer = customerService.updateCustomer(customer);
//			return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
//	
//	}
//
//
//	@DeleteMapping("/deleteCustomer/{id}")
//	public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id){	
//		Customer customer =customerService.deleteCustomer(id);
//		return new ResponseEntity<>(customer,HttpStatus.OK);
//	}
//	
//	@PostMapping("/transferfundsByAccount/{fromAccountNumber}/{toAccountNumber}/{amount}")
//	public ResponseEntity<Account> transferFundsByAccNo(@PathVariable String fromAccountNumber, @PathVariable String toAccountNumber,
//			@PathVariable Double amount) {
//		Account updatedAccount = customerService.transferFunds(fromAccountNumber, toAccountNumber, amount);
//		return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
//	}


//	
//	List<Account> accountList = new ArrayList<>();
//	Account account1 = new Account();
//	account1.setId(11L);
//	account1.setAccountNumber("HDC765535");
//	account1.setAccountType("SAVING");
//	account1.setBalance(23543.87);
//	Account account2 = new Account();
//	account2.setId(12L);
//	account2.setAccountNumber("HDC65535");
//	account2.setAccountType("CURRENT");
//	account2.setBalance(3543.87);
//	
//	accountList.add(account1);
//	accountList.add(account2);
//	
//	Customer customer1 = new Customer();
//	customer1.setId(101L);
//	customer1.setName("xyz");
//	customer1.setAdharNumber("234-235-234");
//	customer1.setMobileNumber("234565767");
//	customer1.setAccount(accountList);
//	
//	List<Account> accountList2 = new ArrayList<>();
//	Account account3 = new Account();
//	account3.setId(17L);
//	account3.setAccountNumber("HDC65345535");
//	account3.setAccountType("CURRENT");
//	account3.setBalance(354343.87);
//	accountList2.add(account3);
//	
//	Customer customer2 = new Customer();
//	customer2.setId(102L);
//	customer2.setName("xyz");
//	customer2.setAdharNumber("234554-235-234");
//	customer2.setMobileNumber("245454565767");
//	customer2.setAccount(accountList2);
//	
//	List<Customer> customerList = new ArrayList<>();
//	customerList.add(customer1);
//	customerList.add(customer2);


	

}
