package com.sterling.digicheck.branchoffice.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.sterling.common.util.JSFUtil;
import com.sterling.digicheck.branchoffice.exception.BranchOfficeException;
import com.sterling.digicheck.branchoffice.service.BranchOfficeService;
import com.sterling.digicheck.branchoffice.view.BranchOfficeView;

@ManagedBean(name="branchOfficeManagedbean")
@ViewScoped
public class BranchOfficeManagedbean implements Serializable {
	/** Serial Version UID */
	private static final long serialVersionUID = -3292054842997979343L;
	
	private static final Logger logger = Logger.getLogger(BranchOfficeManagedbean.class);
	
	@ManagedProperty("#{branchOfficeService}")
	private BranchOfficeService branchOfficeService;
	private BranchOfficeView branchOfficeView = new BranchOfficeView();
	private BranchOfficeView currentBranchOfficeView = new BranchOfficeView();
	private List<BranchOfficeView> branchOfficeList;
	private int page = 1;
	private String sucId;
	
	public void addBranchOffice(){
		try {
			branchOfficeService.insertBranchOffice(branchOfficeView);
			//JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa", "La sucursal se agrego correctamente.");
		} catch (BranchOfficeException e) {
			logger.error(e);
		}
	}
	
	public void deleteBranchOffice(){
		try{
			branchOfficeService.deleteBranchOffice(Integer.parseInt(sucId));
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa", "La sucursal se elimino correctamente.");
		} catch (BranchOfficeException e) {			
			logger.error(e);
		}
	}
	
	public void editBranchOffice(){
		try{
			branchOfficeService.updateBranchOffice(currentBranchOfficeView);
			//JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa", "La sucursal se actualizo correctamente.");
		} catch (BranchOfficeException e) {
			logger.error(e);
		}
	}
	
	public void goEditBranchOffice(){
		try {
			currentBranchOfficeView = branchOfficeService.validateBranchOffice(Integer.parseInt(sucId));
		} catch (NumberFormatException e) {		
		} catch (BranchOfficeException e) {
			logger.error(e);
		}
	}
	
	public void setBranchOfficeService(BranchOfficeService branchOfficeService) {
		this.branchOfficeService = branchOfficeService;
	}

	public BranchOfficeView getBranchOfficeView() {
		return branchOfficeView;
	}
	
	public void setBranchOfficeView(BranchOfficeView branchOfficeView) {
		this.branchOfficeView = branchOfficeView;
	}

	public BranchOfficeView getCurrentBranchOfficeView() {
		return currentBranchOfficeView;
	}

	public List<BranchOfficeView> getBranchOfficeList() {
		try {
			branchOfficeList = branchOfficeService.getAllBranchOffices();
		} catch (BranchOfficeException e) {
			logger.error(e);
		}
		return branchOfficeList;
	}
	
	public List<SelectItem> getStatesList(){		
		return branchOfficeService.getSelectStates();
	}

	public void setBranchOfficeList(List<BranchOfficeView> branchOfficeList) {
		this.branchOfficeList = branchOfficeList;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSucId() {
		return sucId;
	}

	public void setSucId(String sucId) {
		this.sucId = sucId;
	}	
		
}
