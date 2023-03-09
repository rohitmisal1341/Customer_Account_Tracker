package avitepa.foundation.bank2.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  @Column(unique = true)
  private String mobileNumber;
  @Column(unique = true)
  private String adharNumber;
  private String gender;

  
  @OneToMany(cascade = CascadeType.ALL)
  private List<Account> account;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getMobileNumber() {
	return mobileNumber;
}

public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}

public String getAdharNumber() {
	return adharNumber;
}

public void setAdharNumber(String adharNumber) {
	this.adharNumber = adharNumber;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public List<Account> getAccount() {
	return account;
}

public void setAccount(List<Account> account) {
	this.account = account;
}

public Customer(Long id, String name, String mobileNumber, String adharNumber, String gender, List<Account> account) {
	super();
	this.id = id;
	this.name = name;
	this.mobileNumber = mobileNumber;
	this.adharNumber = adharNumber;
	this.gender = gender;
	this.account = account;
}

public Customer() {
	super();
	// TODO Auto-generated constructor stub
}




//public Account getAccount() {
//	return account;
//}
//
//public void setAccount(Account account) {
//	this.account = account;
//}

  // Getters and Setters
  
  
  
}