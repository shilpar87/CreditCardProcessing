package org.test.proj.ccprocessor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CreditCardInfoData {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	private String cardNumber;
	private String cardLimit;
	private String cardBalance;
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(String limit) {
		this.cardLimit = limit;
	}

	public String getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(String cardBalance) {
		this.cardBalance = cardBalance;
	}
}