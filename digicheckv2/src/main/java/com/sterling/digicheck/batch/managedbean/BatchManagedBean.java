package com.sterling.digicheck.batch.managedbean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.sterling.common.util.JSFUtil;
import com.sterling.digicheck.batch.service.BatchService;

@ManagedBean(name="batchManagedBean")
public class BatchManagedBean implements Serializable {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7403154827056176439L;
	
	@ManagedProperty("#{batchService}")
	private BatchService batchService;	
	private String reference;
	private Date date = new Date();
	private String branchOfficeId;
	
	
	public String searchByCriteria(){
		if(this.getReference().equals("")){
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Ingrese una referencia", "Ingrese una referencia");
		}
		if(this.getDate().equals("")){
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Ingrese una fecha", "Ingrese una fecha");
		}
		if(this.branchOfficeId.equals("-1")){
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Seleccione al menos una Sucursal", "Seleccione al menos una Sucursal");
		}
			
		
		return null;
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
	
}
