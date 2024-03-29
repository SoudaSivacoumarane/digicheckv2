package com.sterling.digicheck.currency.view;

import java.io.Serializable;

public class CurrencyView implements Serializable {
	
	private static final long serialVersionUID = 1091505038339380532L;
	private String currencyId;
	private String code;
	private String name;

	public CurrencyView() {
		
	}	
	
	public CurrencyView(String nBankId, String nCode, String nName) {
		currencyId = nBankId;
		code = nCode;
		name= nName;		
	}
	

	public String getName() {
		return name;
	}
	public void setName(String nombre) {
		this.name = nombre;
	}
	
	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof CurrencyView))
			return false;
		CurrencyView bankView = (CurrencyView)obj; 
		return this.getCurrencyId().equals(bankView.getCurrencyId());		
	}
	
}