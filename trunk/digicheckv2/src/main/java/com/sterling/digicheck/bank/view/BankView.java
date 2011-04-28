/**
 * 
 */
package com.sterling.digicheck.bank.view;

import java.io.Serializable;

import javax.validation.constraints.Size;

/**
 *
 * TODO rioslore:Document this.
 * 
 * @author rioslore
 */
public class BankView implements Serializable{
	/** Serial Version ID */
	private static final long serialVersionUID = -4836470143973592753L;
	private String bankId;
	@Size(min=4, max=4, message="Selecciona una cantidad correcta.")
	private String code;
	@Size(min=1, message="Ingresa un nombre de Banco.")
	private String name;
	@Size(min=1, message="Ingresa un nombre de Cuenta.")
	private String account;

	public BankView() {
		
	}
	
	public BankView(String nBankId){
		bankId = nBankId;
	}
	
	public BankView(String nBankId, String nCode, String nName, String nAccount) {
		bankId = nBankId;
		code = nCode;
		name= nName;
		account = nAccount;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String nombre) {
		this.name = nombre;
	}
	
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BankView))
			return false;
		BankView bankView = (BankView)obj; 
		return this.getBankId().equals(bankView.getBankId());		
	}
}
