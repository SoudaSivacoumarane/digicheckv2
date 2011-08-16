package com.sterling.digicheck.batch.managedbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;

import com.sterling.common.util.JSFUtil;
import com.sterling.digicheck.batch.exception.BatchException;
import com.sterling.digicheck.batch.service.BatchService;
import com.sterling.digicheck.batch.view.BatchView;

@ManagedBean(name="batchManagedBean")
public class BatchManagedBean implements Serializable {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7403154827056176439L;
	private static final Logger logger = Logger.getLogger(BatchManagedBean.class);
	
	@ManagedProperty("#{batchService}")
	private BatchService batchService;	
	private String reference;
	private Date date = new Date();
	private String branchOfficeId;
	private boolean renderTable = Boolean.FALSE;
	private List<BatchView> batchViewList = null;
	private int page = 1;		
	
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
		try {
			batchViewList = batchService.searchBatchEntity(reference, date, Integer.parseInt(branchOfficeId));
			if(batchViewList != null){
				this.renderTable = Boolean.TRUE;
			}else{
				JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados.", "No se encontraron resultados.");
			}
		} catch (BatchException e) {			
			logger.error(e.getMessage());			
		}					
		return null;
	}
	
	public String goDigitalization(){
		return "digitalizacion.xhtml";
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
}
