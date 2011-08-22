package com.sterling.digicheck.batch.managedbean;

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
import com.sterling.digicheck.batch.exception.BatchException;
import com.sterling.digicheck.batch.service.BatchService;
import com.sterling.digicheck.batch.view.BatchView;
import com.sterling.digicheck.branchoffice.exception.BranchOfficeException;
import com.sterling.digicheck.branchoffice.service.BranchOfficeService;
import com.sterling.digicheck.branchoffice.view.BranchOfficeView;
import com.sterling.digicheck.currency.exception.CurrencyException;
import com.sterling.digicheck.currency.service.CurrencyService;
import com.sterling.digicheck.currency.view.CurrencyView;
import com.sterling.digicheck.user.view.UserView;

@ManagedBean(name="batchManagedBean")
@SessionScoped
public class BatchManagedBean implements Serializable {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7403154827056176439L;
	private static final Logger logger = Logger.getLogger(BatchManagedBean.class);
	
	@ManagedProperty("#{batchService}")
	private BatchService batchService;	
	@ManagedProperty("#{branchOfficeService}")
	private BranchOfficeService branchOfficeService;
	@ManagedProperty("#{currencyService}")
	private CurrencyService currencyService;	
	private String reference;
	private Date date = new Date();
	private String branchOfficeId;
	private boolean renderTable = Boolean.FALSE;
	private List<BatchView> batchViewList = null;
	private int page = 1;
	private BranchOfficeView branchOfficeView = new BranchOfficeView();
	private BatchView batchView = new BatchView();
	private UserView userView = new UserView();
	private String currencySelected;
	
	public String searchByCriteria(){
		if(this.getReference().equals("")){
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Ingrese una referencia", "Ingrese una referencia");
		}else if(this.getDate().equals("")){
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Ingrese una fecha", "Ingrese una fecha");
		}else if(this.branchOfficeId.equals("-1")){
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Seleccione al menos una Sucursal", "Seleccione al menos una Sucursal");
		}else{
			try {
				batchViewList = batchService.searchBatchEntity(reference, date, Integer.parseInt(branchOfficeId));
				if(batchViewList != null){
					this.renderTable = Boolean.TRUE;
				}else{
					this.renderTable = Boolean.FALSE;
					JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados.", "No se encontraron resultados.");
				}
			} catch (BatchException e) {			
				logger.error(e.getMessage());			
			}
		}					
		return null;
	}
	
	public void goDigitalization(){
		try {
			this.userView = JSFUtil.getSessionAttribute(UserView.class, "user");
			this.branchOfficeView = branchOfficeService.validateBranchOffice(Integer.parseInt(userView.getSucursalId()));
			this.batchView.setUserView(userView);
			this.batchView.setBranchOfficeView(branchOfficeView);
		} catch (BranchOfficeException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		}
		JSFUtil.redirect("digitalizacion.xhtml");
	}
	
	public void insertBatch(){
		try {			
			this.batchView.setCurrencyView(this.currencyService.getCurrencyById(Integer.parseInt(currencySelected)));
			this.batchService.insertBatch(batchView);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El Lote se ha guardado exitosamente.", "El lote se ha guardado exitosamente.");
		} catch (BatchException e) {		
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		} catch (CurrencyException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());		
		}		
	}
	
	public void back(){
		JSFUtil.redirect("cheques.xhtml");
	}
	
	public void cleanValues(){
		this.batchViewList = new ArrayList<BatchView>(0);
		this.branchOfficeView = new BranchOfficeView();
		this.userView = new UserView();
		this.page = 0;
		this.reference = "";
		this.date = new Date();
		this.renderTable = Boolean.FALSE;
		this.branchOfficeId = "-1";
		this.batchView = new BatchView();
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
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		}
		return currencyList;
	}
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBranchOfficeId() {
		return branchOfficeId;
	}
	public void setBranchOfficeId(String branchOfficeId) {
		this.branchOfficeId = branchOfficeId;
	}
	public void setBatchService(BatchService batchService) {
		this.batchService = batchService;
	}
	public boolean isRenderTable() {
		return renderTable;
	}
	public void setRenderTable(boolean renderTable) {
		this.renderTable = renderTable;
	}
	public List<BatchView> getBatchViewList() {
		return batchViewList;
	}
	public void setBatchViewList(List<BatchView> batchViewList) {
		this.batchViewList = batchViewList;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public BranchOfficeView getBranchOfficeView() {
		return branchOfficeView;
	}
	public void setBranchOfficeView(BranchOfficeView branchOfficeView) {
		this.branchOfficeView = branchOfficeView;
	}
	public void setBranchOfficeService(BranchOfficeService branchOfficeService) {
		this.branchOfficeService = branchOfficeService;
	}
	public UserView getUserView() {
		return userView;
	}
	public void setUserView(UserView userView) {
		this.userView = userView;
	}
	public BatchView getBatchView() {
		return batchView;
	}
	public void setBatchView(BatchView batchView) {
		this.batchView = batchView;
	}
	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}	
	public String getCurrencySelected() {
		return currencySelected;
	}
	public void setCurrencySelected(String currencySelected) {
		this.currencySelected = currencySelected;
	}	
}