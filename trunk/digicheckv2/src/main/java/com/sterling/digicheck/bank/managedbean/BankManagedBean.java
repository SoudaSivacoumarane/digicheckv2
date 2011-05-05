package com.sterling.digicheck.bank.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.sterling.common.util.JSFUtil;
import com.sterling.digicheck.bank.exception.BankException;
import com.sterling.digicheck.bank.service.BankService;
import com.sterling.digicheck.bank.view.BankView;

@ManagedBean(name="bankManagedBean")
@ViewScoped
public class BankManagedBean implements Serializable{	
	/** Serial Version UID */
	private static final long serialVersionUID = -3472740015549876796L;
	private static final Logger logger = Logger.getLogger(BankManagedBean.class);
	@ManagedProperty("#{bankService}")
	private BankService bankService;
	private BankView bankView = new BankView();
	private BankView currentBank = new BankView();	
	private List<BankView> banks = null;
	private int page = 1;
	private String bankId;
			
	public void addBankAction(){
		try {
			bankService.insertBank(bankView);
			//JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa", "La sucursal se agrego correctamente");
			banks = bankService.getAllBanks();
		} catch (BankException e) {
			logger.error(e.getStackTrace());			
		}		
	}		
	
	public void goEditBank(){		
		try {
			currentBank = bankService.getBankEntityById(Integer.parseInt(bankId));
		} catch (NumberFormatException e) {			
		} catch (BankException e) {
			e.printStackTrace();
		}
	}
	
	public void editBank(){
		try {
			bankService.updateBank(currentBank);
		} catch (BankException e) {
			e.printStackTrace();
		}
	}
	
	public List<BankView> getBanks() {
		banks = new ArrayList<BankView>(0);
		try {
			banks = bankService.getAllBanks();	
		}catch (BankException bankException){
			logger.error(bankException.getStackTrace());
		}
		return banks;
	}
	
	public void deleteBank(){
		try {
			bankService.deleteBank(Integer.parseInt(bankId));
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

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public BankView getBankView() {
		return bankView;
	}

	public void setBankView(BankView bankView) {
		this.bankView = bankView;
	}	
	
}
