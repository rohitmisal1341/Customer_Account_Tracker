package avitepa.foundation.bank2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatRuntimeException;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.aspectj.util.LangUtil.ProcessController.Thrown;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.*;
import static org.mockito.BDDMockito.willDoNothing;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.BDDMockito.given;
import avitepa.foundation.bank2.exception.AccountTypeSameException;
import avitepa.foundation.bank2.model.Account;
import avitepa.foundation.bank2.model.Customer;
import avitepa.foundation.bank2.repository.AccountRepository;
import avitepa.foundation.bank2.repository.CustomerRepository;
import avitepa.foundation.bank2.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private AccountRepository accountRepository;
	
	@InjectMocks
	private CustomerService customerService;
	
	
    
 
    
    @Test
    public void createCustomerTest() {
    	
    	Customer customer;

       Account account;
       
    	 account = new Account();
         account.setAccountNumber("1234567890");
         account.setAccountType("saving");
         account.setBalance(5000.0);

         List<Account> accountList = new ArrayList<>();
         accountList.add(account);

         customer = new Customer();
         customer.setId(1L);
         customer.setName("xyyx fg");
         customer.setMobileNumber("1234567890");
         customer.setAdharNumber("123456789012");
         customer.setGender("male");
         customer.setAccount(accountList);
        when(accountRepository.saveAll(customer.getAccount())).thenReturn(customer.getAccount());
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.createCustomer(customer);

        assertEquals(1, result.getAccount().size());
//        assertThat(result).isEqualTo(customer);
//        verify(accountRepository, times(1)).saveAll(customer.getAccount());
//        verify(customerRepository, times(1)).save(customer);
    }
    
    //customer with exception
   
    
    
    
    @Test
    void getAllCustomersTest() {
    	

        Account account;
        
     	 account = new Account();
          account.setAccountNumber("1234567890");
          account.setAccountType("saving");
          account.setBalance(5000.0);

          List<Account> accountList = new ArrayList<>();
          accountList.add(account);
          
          Customer customer1 = new Customer(11L,"RAM", "12345","234-345-23","MALE",accountList);
          
          
          List<Account> accountList2 = new ArrayList<>();
  		Account account1 = new Account();
  		account1.setId(11L);
  		account1.setAccountNumber("HDC765535");
  		account1.setAccountType("SAVING");
  		account1.setBalance(23543.87);
  		Account account2 = new Account();
  		account2.setId(12L);
  		account2.setAccountNumber("HDC65535");
  		account2.setAccountType("CURRENT");
  		account2.setBalance(3543.87);
  		
  		accountList.add(account1);
  		accountList.add(account2);
  		
  		Customer customer2 = new Customer(12L,"RAM", "12345","234-345-23","MALE",accountList2);
  		
  		when(customerRepository.findAll()).thenReturn(List.of(customer1,customer2));
  		
  		List<Customer> customerList = customerService.getAllCustomers().getBody();
  		
//  		assertThat(customerList).isNotNull();
//  		assertThat(customerList.size()).isEqualTo(2);
  		
  		assertEquals(2, customerList.size());
          
          
    	
    }
    
    @Test
    void getCustomerByIdTest(){

    	Customer customer;

       Account account;
       
    	 account = new Account();
         account.setAccountNumber("1234567890");
         account.setAccountType("saving");
         account.setBalance(5000.0);

         List<Account> accountList = new ArrayList<>();
         accountList.add(account);
         customer = new Customer();
         customer.setId(1L);
         customer.setName("xyyx fg");
         customer.setMobileNumber("1234567890");
         customer.setAdharNumber("123456789012");
         customer.setGender("male");
         customer.setAccount(accountList);
//         when(accountRepository.saveAll(customer.getAccount())).thenReturn(customer.getAccount());
//         when(customerRepository.save(customer)).thenReturn(customer);

//         Customer result = customerService.getCustomerById(1L).getBody();
         given(customerRepository.findById(1L)).willReturn(Optional.of(customer));
         
         Customer customerCheck = customerService.getCustomerById(customer.getId()).getBody();
        assertThat(customerCheck).isNotNull();
         
    }
    
    
    @Test
    void getAccountByAccountNumber() {

        Account account;
        
     	 account = new Account();
     	 account.setId(1234L);
          account.setAccountNumber("HDFC4567890");
          account.setAccountType("saving");
          account.setBalance(5000.0);
          
          given(accountRepository.findByAccountNumber("HDFC4567890")).willReturn(account);
          
          Account accountCheck = accountRepository.findByAccountNumber(account.getAccountNumber());
          
          
          assertEquals(1234L, accountCheck.getId());
          assertEquals("HDFC4567890", accountCheck.getAccountNumber());
          assertEquals("saving", accountCheck.getAccountType());

          assertEquals(5000.0, accountCheck.getBalance());


    }
    
    
    @Test
    void getCustomerByAdharNumberTest() {
    	
            
          List<Account> accountList = new ArrayList<>();
  		Account account1 = new Account();
  		account1.setId(11L);
  		account1.setAccountNumber("HDC765535");
  		account1.setAccountType("SAVING");
  		account1.setBalance(23543.87);
  		Account account2 = new Account();
  		account2.setId(12L);
  		account2.setAccountNumber("HDC65535");
  		account2.setAccountType("CURRENT");
  		account2.setBalance(3543.87);
  		
  		accountList.add(account1);
  		accountList.add(account2);
  		
  		Customer customer2 = new Customer(12L,"RAM", "12345","234-345-23","MALE",accountList);
  		
  		given(customerRepository.findByAdharNumber("234-345-23")).willReturn(customer2);
  		
  		Customer customerCheck = customerService.findByAdharNumber(customer2.getAdharNumber()).getBody();
  		
  		assertEquals("RAM", customerCheck.getName());
  		assertEquals(12L, customerCheck.getId());
  		assertEquals("12345", customerCheck.getMobileNumber());
  		
    }

    @Test
    void deleteCustomerTest() {
        Customer customer;

        Account account;
        
        account = new Account();
        account.setAccountNumber("1234567890");
        account.setAccountType("saving");
        account.setBalance(5000.0);

        List<Account> accountList = new ArrayList<>();
        accountList.add(account);

        customer = new Customer();
        customer.setId(1L);
        customer.setName("xyyx fg");
        customer.setMobileNumber("1234567890");
        customer.setAdharNumber("1234-5678-9012");
        customer.setGender("male");
        customer.setAccount(accountList);

        given(customerRepository.findById(1L)).willReturn(Optional.of(customer));
        doNothing().when(customerRepository).delete(customer);

        Customer customerChecked = customerService.deleteCustomer(1L);
        

        assertEquals(customer.getGender(), customerChecked.getGender());
    }

  
    
    @Test
    void transferFundTest() {
        
        List<Account> accountList = new ArrayList<>();
		Account account1 = new Account();
		account1.setId(11L);
		account1.setAccountNumber("HDC765535");
		account1.setAccountType("SAVING");
		account1.setBalance(1000.0);
		Account account2 = new Account();
		account2.setId(12L);
		account2.setAccountNumber("HDC65535");
		account2.setAccountType("CURRENT");
		account2.setBalance(1000.0);
		
		accountList.add(account1);
		accountList.add(account2);
		
		System.out.println("running upto this..");
		Customer customer2 = new Customer(12L,"RAM", "12345","234-345-23","MALE",accountList);
		
		when(accountRepository.saveAll(customer2.getAccount())).thenReturn(customer2.getAccount());
	//	when(accountRepository.save(account1)).thenReturn(account1);
	//	when(accountRepository.save(account2)).thenReturn(account2);

		when(accountRepository.findByAccountNumber("HDC765535")).thenReturn(account1);
		when(accountRepository.findByAccountNumber("HDC65535")).thenReturn(account2);

        when(customerRepository.save(customer2)).thenReturn(customer2);
		
		
		Account accountDebited = customerService.transferFunds(customer2.getAccount().get(0).getAccountNumber(), 
				customer2.getAccount().get(1).getAccountNumber()	, 5.0);
		double remainingAmount = accountDebited.getBalance();
		System.out.println(remainingAmount);
		System.out.println(remainingAmount);
		assertEquals(995.0, accountDebited.getBalance());
    }
    
  @Test
  void updateCustomerTest() {
	  
	  Optional<Customer> customer= Optional.ofNullable(new Customer(11L,"name", "12345","234-345-23","MALE",new ArrayList<>()));
	  
	  when(customerRepository.findById(11L)).thenReturn(customer);
  	
  	 Customer customer2 = new Customer(11L,"updatedName", "12345","234-345-23","MALE",new ArrayList<>());
  	
 	when(customerService.updateCustomer(customer2)).thenReturn(customer2);
  	
  	 assertEquals(customerRepository.findById(11L).get().getName(),customer2.getName());
  	
  }
   
    

    
   

//    

//  this is back up data 

//	@Test
//	public void testGetAllCustomers() {
//		List<Account> accountList = new ArrayList<>();
//		Account account1 = new Account();
//		account1.setId(11L);
//		account1.setAccountNumber("HDC765535");
//		account1.setAccountType("SAVING");
//		account1.setBalance(23543.87);
//		Account account2 = new Account();
//		account2.setId(12L);
//		account2.setAccountNumber("HDC65535");
//		account2.setAccountType("CURRENT");
//		account2.setBalance(3543.87);
//		
//		accountList.add(account1);
//		accountList.add(account2);
//		
//		Customer customer1 = new Customer();
//		customer1.setId(101L);
//		customer1.setName("xyz");
//		customer1.setAdharNumber("234-235-234");
//		customer1.setMobileNumber("234565767");
//		customer1.setAccount(accountList);
//		
//		List<Account> accountList2 = new ArrayList<>();
//		Account account3 = new Account();
//		account3.setId(17L);
//		account3.setAccountNumber("HDC65345535");
//		account3.setAccountType("CURRENT");
//		account3.setBalance(354343.87);
//		accountList2.add(account3);
//		Customer customer2 = new Customer();
//		customer2.setId(102L);
//		customer2.setName("xyz");
//		customer2.setAdharNumber("234554-235-234");
//		customer2.setMobileNumber("245454565767");
//		customer2.setAccount(accountList2);
//		
//		List<Customer> customerList = new ArrayList<>();
//		customerList.add(customer1);
//		customerList.add(customer2);
//		
//		
//		when(customerRepository.findAll()).thenReturn(customerList);
//		
//		List<Customer> customerCheck  = (List<Customer>) customerService.getAllCustomers();
//		
//		assertEquals(2, customerCheck.size());
//		
////		assertThat(customerCheck.size()).isEqualTo(2);
//			
//	}
    
//    @org.junit.Before
//    public void setUp() {
//        account = new Account();
//        account.setAccountNumber("1234567890");
//        account.setAccountType("saving");
//        account.setBalance(5000.0);
//
//        List<Account> accountList = new ArrayList<>();
//        accountList.add(account);
//
//        customer = new Customer();
//        customer.setId(1L);
//        customer.setName("John Doe");
//        customer.setMobileNumber("1234567890");
//        customer.setAdharNumber("123456789012");
//        customer.setGender("male");
//        customer.setAccount(accountList);
//    }

//  @Test
//  void updateCustomerTest() {
//  	 Customer customer1 = new Customer(11L,"customerName", "12345","234-345-23","MALE",new ArrayList<>());
////  	 given(customerRepository.save(customer1)).willReturn(customer1);
//  	 when(customerRepository.save(customer1)).thenReturn(customer1);
////  	 Customer customer2 = new Customer(11L,"updatedName", "12345","234-345-23","MALE",new ArrayList<>());
//  	 customer1.getId();
////  	 customer1.setId(customer1.getId());
//  	 customer1.setName("updatedName");
//  	 customer1.setGender("FEMALE");
//  	 Customer updatedCustomer = customerService.updateCustomer(customer1);
//  	 assertThat(updatedCustomer.getName()).isEqualTo("updatedName");
//  	 assertThat(updatedCustomer.getGender()).isEqualTo("FEMALE");
////  	 assertEquals(customer1.getName(), updatedCustomer.getName());
//  }
//  

}
