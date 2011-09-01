package com.sterling.digicheck.batch.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sterling.digicheck.branchoffice.view.BranchOfficeView;
import com.sterling.digicheck.check.view.CheckView;
import com.sterling.digicheck.currency.view.CurrencyView;
import com.sterling.digicheck.document.view.DocumentView;
import com.sterling.digicheck.user.view.UserView;

public class BatchView implements Serializable {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3590260924655428679L;

	private Integer batchId; 		
    private String reference;		    
    private BigDecimal batchAmount = new BigDecimal(0);           	    
    private Integer batchDocuments = new Integer(0);        	    
    private Date batchDate;        	    
    private Date batchDateAdded;	
	private CurrencyView currencyView = new CurrencyView();
	private BranchOfficeView branchOfficeView = new BranchOfficeView();	
	private UserView userView = new UserView();
	private List<CheckView> checkViewList = new ArrayList<CheckView>(0);
	private List<DocumentView> documentList = new ArrayList<DocumentView>(0);
	
	public Integer getBatchId() {
		return batchId;
	}
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public BigDecimal getBatchAmount() {
		return batchAmount;
	}
	public void setBatchAmount(BigDecimal batchAmount) {
		this.batchAmount = batchAmount;
	}
	public Integer getBatchDocuments() {
		return batchDocuments;
	}
	public void setBatchDocuments(Integer batchDocuments) {
		this.batchDocuments = batchDocuments;
	}
	public Date getBatchDate() {
		return batchDate;
	}
	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}
	public Date getBatchDateAdded() {
		return batchDateAdded;
	}
	public void setBatchDateAdded(Date batchDateAdded) {
		this.batchDateAdded = batchDateAdded;
	}	
	public CurrencyView getCurrencyView() {
		return currencyView;
	}
	public void setCurrencyView(CurrencyView currencyView) {
		this.currencyView = currencyView;
	}
	public BranchOfficeView getBranchOfficeView() {
		return branchOfficeView;
	}
	public void setBranchOfficeView(BranchOfficeView branchOfficeView) {
		this.branchOfficeView = branchOfficeView;
	}
	public UserView getUserView() {
		return userView;
	}
	public void setUserView(UserView userView) {
		this.userView = userView;
	}
	public List<CheckView> getCheckViewList() {
		return checkViewList;
	}
	public void setCheckViewList(List<CheckView> checkViewList) {
		this.checkViewList = checkViewList;
	}
	public List<DocumentView> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<DocumentView> documentList) {
		this.documentList = documentList;
	}	
}
