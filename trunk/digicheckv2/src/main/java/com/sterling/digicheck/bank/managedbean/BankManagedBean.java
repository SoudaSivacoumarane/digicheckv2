package com.sterling.digicheck.bank.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.sterling.common.util.JSFUtil;
import com.sterling.digicheck.bank.exception.BankException;
import com.sterling.digicheck.bank.service.BankService;
import com.sterling.digicheck.bank.view.BankView;

@ManagedBean(name="bankManagedBean")
@SessionScoped
public class BankManagedBean {
	
	@ManagedProperty("#{bankService}")
	private BankService bankService;	
	private BankView currentBank;	
	private List<BankView> banks = null;
	private int page = 1;
	
	public BankManagedBean() {		
	}		
	
	public void addBankAction(){
		try {
			bankService.insertBank(getCurrentBank());
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa", "La sucursal se agrego correctamente");
			banks = bankService.getAllBanks();
		} catch (BankException e) {
			e.printStackTrace();
		}		
	}		
	
	public BankView getCurrentBank() {
		return currentBank;
	}
	public void setCurrentBank(BankView currentBank) {
		this.currentBank = currentBank;
	}
	public List<BankView> getBanks() {
		banks = new ArrayList<BankView>(0);
		try {
			banks = bankService.getAllBanks();	
		}catch (BankException bankException){
			bankException.printStackTrace();
		}
		return banks;
	}
	public void setBanks(List<BankView> banks) {
		this.banks = banks;
	}
	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}		
	
}
