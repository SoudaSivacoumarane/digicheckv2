/**
 * 
 */
package com.sterling.digicheck.bank.view;

import java.io.Serializable;

/** 
 * Bank View
 * 
 * @author Edgar Joao
 */
public class BankView implements Serializable{
	/** Serial Version ID */
	private static final long serialVersionUID = -4836470143973592753L;
	private String bankId;
	private String code;
	private String name;
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
