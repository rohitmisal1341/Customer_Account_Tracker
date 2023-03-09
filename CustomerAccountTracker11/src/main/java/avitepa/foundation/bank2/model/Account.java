package avitepa.foundation.bank2.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity

public class Account{


@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String accountNumber;
  private String accountType;
  private Double balance;



public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getAccountNumber() {
	return accountNumber;
}

public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}

public String getAccountType() {
	return accountType;
}

public void setAccountType(String accountType) {
	this.accountType = accountType;
}

public Double getBalance() {
	return balance;
}

public void setBalance(Double balance) {
	this.balance = balance;
}

public Account(Long id, String accountNumber, String accountType, Double balance) {
	super();
	this.id = id;
	this.accountNumber = accountNumber;
	this.accountType = accountType;
	this.balance = balance;
}
public Account(Long id) {
	super();
	this.id = id;
	
}

public Account() {
	super();
	// TODO Auto-generated constructor stub
}


  
}