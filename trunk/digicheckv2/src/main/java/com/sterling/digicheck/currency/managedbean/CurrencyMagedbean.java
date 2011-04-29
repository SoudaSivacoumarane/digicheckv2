package com.sterling.digicheck.currency.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.sterling.digicheck.currency.service.CurrencyService;

@ManagedBean(name="currencyMagedbean")
@ViewScoped
public class CurrencyMagedbean implements Serializable {
	/** Serial Version UID */
	private static final long serialVersionUID = -5466571077218267924L;
	@ManagedProperty("#{currencyService}")
	private CurrencyService currencyService;


	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}		

}
