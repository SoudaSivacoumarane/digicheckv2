package com.sterling.digicheck.currency.view;

import java.io.Serializable;

public class CurrencyView implements Serializable {
	
	private static final long serialVersionUID = 1091505038339380532L;
	private String currencyId;
	private String code;
	private String name;
	private String image;

	public CurrencyView() {
		
	}	
	
	public CurrencyView(String nBankId, String nCode, String nName, String nImage) {
		currencyId = nBankId;
		code = nCode;
		name= nName;
		image = nImage;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof CurrencyView))
			return false;
		CurrencyView bankView = (CurrencyView)obj; 
		return this.getCurrencyId().equals(bankView.getCurrencyId());		
	}
	
}