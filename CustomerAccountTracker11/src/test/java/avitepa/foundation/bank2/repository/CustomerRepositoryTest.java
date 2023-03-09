package avitepa.foundation.bank2.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import avitepa.foundation.bank2.model.Customer;


@SpringBootTest
@AutoConfigureMockMvc
class CustomerRepositoryTest {

	@Mock
	CustomerRepository customerRepository;
	
	@Test
	void findByAdharNumberTest() {
		  Customer customer1 = new Customer(11L,"RAM", "12345","234-345-23","MALE",new ArrayList<>());
		  when(customerRepository.findByAdharNumber("234-345-23")).thenReturn(customer1);
		  
		  assertEquals(customer1.getName(), customerRepository.findByAdharNumber("234-345-23").getName());
	}
	

}
