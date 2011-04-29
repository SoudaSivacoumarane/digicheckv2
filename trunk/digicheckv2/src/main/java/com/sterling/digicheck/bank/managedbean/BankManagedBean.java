package com.sterling.digicheck.bank.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Size;

import com.sterling.common.util.JSFUtil;
import com.sterling.digicheck.bank.exception.BankException;
import com.sterling.digicheck.bank.service.BankService;
import com.sterling.digicheck.bank.view.BankView;

@ManagedBean(name="bankManagedBean")
@SessionScoped
public class BankManagedBean implements Serializable{	
	/** Serial Version UID */
	private static final long serialVersionUID = -3472740015549876796L;
	@ManagedProperty("#{bankService}")
	private BankService bankService;	
	private BankView currentBank = new BankView();	
	private List<BankView> banks = null;
	private int page = 1;
	
	@Size(min=4, max=4, message="Selecciona una cantidad correcta.")
	private String code;
	@Size(min=1, message="Ingresa un nombre de Banco.")
	private String name;
	@Size(min=1, message="Ingresa un nombre de Cuenta.")
	private String account;
	
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
	
	public void deleteBank(){
		try {
			bankService.deleteBank(10);
		} catch (BankException e) {
			e.printStackTrace();
		}
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}		

	
	
	
}
