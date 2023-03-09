package avitepa.foundation.bank2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import avitepa.foundation.bank2.exception.AccountException;
import avitepa.foundation.bank2.exception.AccountNotFoundException;
import avitepa.foundation.bank2.exception.AccountTypeSameException;
import avitepa.foundation.bank2.exception.CustomerNotFoundException;
import avitepa.foundation.bank2.model.Account;
import avitepa.foundation.bank2.model.Customer;
import avitepa.foundation.bank2.repository.AccountRepository;
import avitepa.foundation.bank2.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountRepository accountRepository;
	
	
	
	public Customer createCustomer(Customer customer) throws AccountTypeSameException{
		List<Account> acc= customer.getAccount();
		int count=0;
		int count2=0;
		for(Account a : acc) {
			
			if(a.getAccountType().equalsIgnoreCase("saving")) {
				count++;
			}
			if(a.getAccountType().equalsIgnoreCase("current")) {
				count2++;
			}
			
		}
		if(count>1 || count2>1) {
				throw new AccountTypeSameException("it seems you are trying to create two account of same type of Single customer");
		}
		accountRepository.saveAll(acc);
		return customerRepository.save(customer);
				
	}
	
	
	public Customer updateCustomer(Customer customer){
  Customer customer1 = customerRepository.findById(customer.getId()).orElse(null);
  if(Objects.isNull(customer1)) {
      throw new CustomerNotFoundException("Customer Not Found....");
  }
  
  customer1.setName(customer.getName());
  customer1.setMobileNumber(customer.getMobileNumber());
  customer1.setAdharNumber(customer.getAdharNumber());
  customer1.setGender(customer.getGender());
  
  List<Account> updatedAccounts = new ArrayList<>();
  for (Account account : customer1.getAccount()) {
      Account updatedAccount = customer.getAccount().stream()
          .filter(a -> a.getId().equals(account.getId()))
          .findFirst()
          .orElse(account);
      updatedAccounts.add(updatedAccount);
  }
  customer1.setAccount(updatedAccounts);
  
  return customerRepository.save(customer1);
}
	
//	public Customer updateCustomer(Customer customer){
//		Customer customer1 = customerRepository.findById(customer.getId()).orElse(null);
//		if(Objects.isNull(customer1)) {
//			throw new CustomerNotFoundException("Customer Not Found....");
//		}
//		
//			customer1.setName(customer.getName());
//			customer1.setMobileNumber(customer.getMobileNumber());
//			customer1.setAdharNumber(customer.getAdharNumber());
//			customer1.setGender(customer.getGender());
//			customer1.setAccount(accountRepository.saveAll(customer.getAccount()));
//			return customerRepository.save(customer1);
//
//		
//	}




	
	public ResponseEntity<Customer> getCustomerById(Long id) throws CustomerNotFoundException{
//		return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
		Customer customer = customerRepository.findById(id).orElse(null);
		if(Objects.isNull(customer)) {
			throw new CustomerNotFoundException("Customer of this ID is not Found...");
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	public ResponseEntity<List<Customer>> getAllCustomers() throws CustomerNotFoundException{
		List<Customer> customers =  customerRepository.findAll();
		if(customers.size()==0) {
			throw new CustomerNotFoundException("No customer Found");
		}
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	public Customer deleteCustomer(Long id) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(id).orElse(null);

		if(Objects.nonNull(customer)) {
			customerRepository.deleteById(id);
			return customer;
		}
		 throw new CustomerNotFoundException("Customer of this ID is Not Present...");
		
	}

	public ResponseEntity<Customer> findByAdharNumber(String adharNumber) throws CustomerNotFoundException {
		Customer customer = customerRepository.findByAdharNumber(adharNumber);
		if(Objects.isNull(customer)) {
			throw new CustomerNotFoundException("Customer of this AADHAR NUMBER is not found Or you put wrong ADHAR NUMBER...");
		}
		
			return new ResponseEntity<>(customer, HttpStatus.OK);
		
	}

	public ResponseEntity<Account> findByAccountNumber(String accountNumber) throws AccountNotFoundException{
		Account account = accountRepository.findByAccountNumber(accountNumber);
		if(Objects.isNull(account)) {
			throw new AccountNotFoundException("Account of this Account Number is not Found ...");
		}
		
			return new ResponseEntity<>(account, HttpStatus.OK);
		
	}

	
	
	public Account transferFunds(String fromAccountNumber, String toAccountNumber, Double amount) throws AccountNotFoundException,AccountException{
		Account fromAccountNumber1 = accountRepository.findByAccountNumber(fromAccountNumber);
		Account toAccountNumber1 = accountRepository.findByAccountNumber(toAccountNumber);
		
		if(fromAccountNumber1.equals(toAccountNumber1))
			throw new AccountException("You are trying to send money to the same account...");

		
			if(Objects.isNull(fromAccountNumber1)) {
				throw new AccountNotFoundException("The Sender Account you selected is not Valid or not present In database..");
			}
			if(Objects.isNull(toAccountNumber1)) {
				throw new AccountNotFoundException("The Reciever Account you selected is not Valid or not present In database..");
			}
			else {
				
				Double fromAccountBalance = fromAccountNumber1.getBalance();
				if (fromAccountBalance >= amount) {
					fromAccountNumber1.setBalance(fromAccountBalance - amount);
					toAccountNumber1.setBalance(toAccountNumber1.getBalance() + amount);
					accountRepository.save(fromAccountNumber1);
					accountRepository.save(toAccountNumber1);
				}
				else {
					throw new AccountException("The sender Account has not sufficent balance to send");
				}
			}
		
		return fromAccountNumber1;
	}
	
	//old and crud code ....
//	
//	public Customer createCustomer(Customer customer) throws AccountTypeSameException{
//		List<Account> acc= customer.getAccount();
//		int count=0;
//		int count2=0;
//		for(Account a : acc) {
//			
//			if(a.getAccountType().equalsIgnoreCase("saving")) {
//				count++;
//			}
//			if(a.getAccountType().equalsIgnoreCase("current")) {
//				count2++;
//			}
//			
//		}
//		if(count>1 || count2>1) {
//				throw new AccountTypeSameException("it seems you are trying to create two account of same type of Single customer");
//		}
//		accountRepository.saveAll(acc);
//		return customerRepository.save(customer);
//				
//	}
//	
//	public Customer updateCustomer(Customer customer){
//		Customer customer1 = customerRepository.findById(customer.getId()).orElse(null);
//		if(Objects.isNull(customer1)) {
//			throw new CustomerNotFoundException("Customer Not Found....");
//		}
//		
//			customer1.setName(customer.getName());
//			customer1.setMobileNumber(customer.getMobileNumber());
//			customer1.setAdharNumber(customer.getAdharNumber());
//			customer1.setGender(customer.getGender());
//			customer1.setAccount(accountRepository.saveAll(customer.getAccount()));
//			return customerRepository.save(customer1);
//
//		
//	}
//
//	
//	public ResponseEntity<Customer> getCustomerById(Long id) throws CustomerNotFoundException{
////		return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
//		Customer customer = customerRepository.findById(id).orElse(null);
//		if(Objects.isNull(customer)) {
//			throw new CustomerNotFoundException("Customer of this ID is not Found...");
//		}
//		return new ResponseEntity<>(customer, HttpStatus.OK);
//	}
//
//	public ResponseEntity<List<Customer>> getAllCustomers() throws CustomerNotFoundException{
//		List<Customer> customers =  customerRepository.findAll();
//		if(customers.size()==0) {
//			throw new CustomerNotFoundException("No customer Found");
//		}
//		return new ResponseEntity<>(customers, HttpStatus.OK);
//	}
//
//	public Customer deleteCustomer(Long id) throws CustomerNotFoundException {
//		Customer customer = customerRepository.findById(id).orElse(null);
//
//		if(Objects.nonNull(customer)) {
//			customerRepository.deleteById(id);
//			return customer;
//		}
//		 throw new CustomerNotFoundException("Customer of this ID is Not Present...");
//		
//	}
//
//	public ResponseEntity<Customer> findByAdharNumber(String adharNumber) throws CustomerNotFoundException {
//		Customer customer = customerRepository.findByAdharNumber(adharNumber);
//		if(Objects.isNull(customer)) {
//			throw new CustomerNotFoundException("Customer of this AADHAR NUMBER is not found Or you put wrong ADHAR NUMBER...");
//		}
//		
//			return new ResponseEntity<>(customer, HttpStatus.OK);
//		
//	}
//
//	public ResponseEntity<Account> findByAccountNumber(String accountNumber) throws AccountNotFoundException{
//		Account account = accountRepository.findByAccountNumber(accountNumber);
//		if(Objects.isNull(account)) {
//			throw new AccountNotFoundException("Account of this Account Number is not Found ...");
//		}
//		
//			return new ResponseEntity<>(account, HttpStatus.OK);
//		
//	}
//
//	
//	
//	public Account transferFunds(String fromAccountNumber, String toAccountNumber, Double amount) throws AccountNotFoundException,AccountException{
//		Account fromAccountNumber1 = accountRepository.findByAccountNumber(fromAccountNumber);
//		Account toAccountNumber1 = accountRepository.findByAccountNumber(toAccountNumber);
//		
//		if(!fromAccountNumber1.equals(toAccountNumber1)) {
//			if(Objects.isNull(fromAccountNumber1)) {
//				throw new AccountNotFoundException("The Sender Account you selected is not Valid or not present In database..");
//			}
//			if(Objects.isNull(toAccountNumber1)) {
//				throw new AccountNotFoundException("The Reciever Account you selected is not Valid or not present In database..");
//			}
//			else {
//				
//				Double fromAccountBalance = fromAccountNumber1.getBalance();
//				if (fromAccountBalance >= amount) {
//					fromAccountNumber1.setBalance(fromAccountBalance - amount);
//					toAccountNumber1.setBalance(toAccountNumber1.getBalance() + amount);
//					accountRepository.save(fromAccountNumber1);
//					accountRepository.save(toAccountNumber1);
//				}
//				else {
//					throw new AccountException("The sender Account has not sufficent balance to send");
//				}
//			}
//		}else {
//			throw new AccountException("You are trying to send money to the same account...");
//		}
//		
//		return fromAccountNumber1;
//	}
	


}