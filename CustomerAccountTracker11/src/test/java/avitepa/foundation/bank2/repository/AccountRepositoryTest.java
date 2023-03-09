package avitepa.foundation.bank2.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import avitepa.foundation.bank2.model.Account;


@SpringBootTest
@AutoConfigureMockMvc
class AccountRepositoryTest {

	@Mock
	AccountRepository accountRepository;
	
	@Test
	void findByAccountNumber() {
		Account account = new Account(1L,"HBC1234","SAVING",234.345);
		when(accountRepository.findByAccountNumber("HBC1234")).thenReturn(account);
		assertEquals(account.getBalance(), accountRepository.findByAccountNumber("HBC1234").getBalance());
	}

}
