package com.sterling.digicheck.cash.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.sterling.common.util.JSFUtil;
import com.sterling.digicheck.batch.view.BatchView;
import com.sterling.digicheck.branchoffice.exception.BranchOfficeException;
import com.sterling.digicheck.branchoffice.service.BranchOfficeService;
import com.sterling.digicheck.branchoffice.view.BranchOfficeView;
import com.sterling.digicheck.currency.exception.CurrencyException;
import com.sterling.digicheck.currency.service.CurrencyService;
import com.sterling.digicheck.currency.view.CurrencyView;
import com.sterling.digicheck.security.service.SecurityAuthorizationService;
import com.sterling.digicheck.user.exception.UserException;
import com.sterling.digicheck.user.view.UserView;
/**
 * 
 * @author Edgar Joao
 *
 */
@ManagedBean(name="cashManagedBean")
@SessionScoped
public class CashManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6362811795364160139L;

	private static final Logger logger = Logger.getLogger(CashManagedBean.class);

	@ManagedProperty("#{branchOfficeService}")
	private BranchOfficeService branchOfficeService;
	@ManagedProperty("#{currencyService}")
	private CurrencyService currencyService;	
	@ManagedProperty("#{securityAuthorizationService}")
	SecurityAuthorizationService securityAuthorizationService;
	
	private Date date;
	private String currencySelected;
	private String totalAmount;
		
	private BranchOfficeView branchOfficeView = new BranchOfficeView();
	private BatchView batchView = new BatchView();
	private BatchView batchDocumentView = new BatchView();
	private UserView userView = new UserView();
	
	
	public void goCashDigitalization(){
		try {
			this.userView = JSFUtil.getSessionAttribute(UserView.class, "user");
			this.branchOfficeView = branchOfficeService.validateBranchOffice(Integer.parseInt(userView.getSucursalId()));
			this.batchView.setBatchDocuments(null);
			this.batchView.setBatchAmount(null);
			this.batchView.setBatchDate(new Date());
			this.batchView.setReference("");
			this.currencySelected = "-1";
			this.batchView.setUserView(userView);
			this.batchView.setBranchOfficeView(branchOfficeView);
		} catch (BranchOfficeException e) {
			logger.error(e);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		}
		JSFUtil.redirect("digitalizacion_efectivo.xhtml");
	}
	
	public List<SelectItem> getCurrencyItems(){
		List<SelectItem> currencyList = null;
		SelectItem selectItem = null;		
		try {
			currencyList = new ArrayList<SelectItem>(0);
			for(CurrencyView cv : this.currencyService.getAllCurrencys()){
				selectItem = new SelectItem();
				selectItem.setLabel(cv.getName());
				selectItem.setValue(cv.getCurrencyId());
				currencyList.add(selectItem);
			}
		} catch (CurrencyException e) {
			logger.error(e);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		}
		return currencyList;
	}
	
	public boolean isCashDigitize(){
		boolean digitize = Boolean.FALSE;		
		try {
			digitize = securityAuthorizationService.hasPermission("11", JSFUtil.getSessionAttribute(UserView.class, "user").getLogin());
		} catch (UserException userException) {
			logger.info(userException.getCustomError(), userException);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, userException.getMessage(), userException.getMessage());
		}
		return digitize;
	}		

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCurrencySelected() {
		return currencySelected;
	}

	public void setCurrencySelected(String currencySelected) {
		this.currencySelected = currencySelected;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setBranchOfficeService(BranchOfficeService branchOfficeService) {
		this.branchOfficeService = branchOfficeService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public void setSecurityAuthorizationService(
			SecurityAuthorizationService securityAuthorizationService) {
		this.securityAuthorizationService = securityAuthorizationService;
	}

	public BranchOfficeView getBranchOfficeView() {
		return branchOfficeView;
	}

	public void setBranchOfficeView(BranchOfficeView branchOfficeView) {
		this.branchOfficeView = branchOfficeView;
	}

	public BatchView getBatchView() {
		return batchView;
	}

	public void setBatchView(BatchView batchView) {
		this.batchView = batchView;
	}

	public BatchView getBatchDocumentView() {
		return batchDocumentView;
	}

	public void setBatchDocumentView(BatchView batchDocumentView) {
		this.batchDocumentView = batchDocumentView;
	}

	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}
		
}
