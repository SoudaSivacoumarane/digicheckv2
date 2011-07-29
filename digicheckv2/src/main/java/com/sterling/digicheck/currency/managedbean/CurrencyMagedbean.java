package com.sterling.digicheck.currency.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.sterling.common.util.JSFUtil;
import com.sterling.digicheck.currency.exception.CurrencyException;
import com.sterling.digicheck.currency.service.CurrencyService;
import com.sterling.digicheck.currency.view.CurrencyView;

@ManagedBean(name="currencyMagedbean")
@ViewScoped
public class CurrencyMagedbean implements Serializable {
	/** Serial Version UID */
	private static final long serialVersionUID = -5466571077218267924L;
	@ManagedProperty("#{currencyService}")
	private CurrencyService currencyService;
	private List<CurrencyView> currencyList;
	private String currencyId;
	private CurrencyView currencyView = new CurrencyView();
	private CurrencyView currentView = new CurrencyView();
	private int page = 1;		
	
	public void addCurrency(){
		try {
			currencyService.insertCurrency(currencyView);
			//JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa", "La divisa se agrego correctamente.");
		} catch (CurrencyException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCurrency(){
		try {
			currencyService.deleteCurrency(Integer.parseInt(currencyId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (CurrencyException e) {
			e.printStackTrace();
		}
	}
	
	public void goEditCurrency(){		
		try {
			currentView = currencyService.getCurrencyById(Integer.parseInt(currencyId));			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (CurrencyException e) {
			e.printStackTrace();
		}		
	}
	
	public void editCurrency(){
		try {
			currencyService.updateCurrency(currentView);
			//JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa", "La divisa se edito correctamente.");
		} catch (CurrencyException e) {
			e.printStackTrace();
		}
	}		
	
	public CurrencyView getCurrencyView() {
		return currencyView;
	}

	public void setCurrencyView(CurrencyView currencyView) {
		this.currencyView = currencyView;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}
	
	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public List<CurrencyView> getCurrencyList() {
		try {
			currencyList = currencyService.getAllCurrencys();
		} catch (CurrencyException e) {
			e.printStackTrace();
		}
		return currencyList;
	}

	public void setCurrencyList(List<CurrencyView> currencyList) {
		this.currencyList = currencyList;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public CurrencyView getCurrentView() {
		return currentView;
	}

	public void setCurrentView(CurrencyView currentView) {
		this.currentView = currentView;
	}				
	
}
