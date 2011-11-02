package com.sterling.digicheck.batch.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
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
import com.sterling.digicheck.document.view.DocumentView;
import com.sterling.digicheck.security.service.SecurityAuthorizationService;
import com.sterling.digicheck.user.exception.UserException;
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
	@ManagedProperty("#{securityAuthorizationService}")
	SecurityAuthorizationService securityAuthorizationService;
	private String reference;
	private Date date;
	private String branchOfficeId;
	private boolean renderTable = Boolean.FALSE;
	private List<BatchView> batchViewList = null;
	private int page = 1;
	private BranchOfficeView branchOfficeView = new BranchOfficeView();
	private BatchView batchView = new BatchView();
	private BatchView batchDocumentView = new BatchView();
	private UserView userView = new UserView();
	private String currencySelected;
	private String batchId;
	
	public String searchByCriteria(){
		if(this.branchOfficeId.equals("-1")){
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Seleccione al menos una Sucursal", "Seleccione una Sucursal");
		}else{
			try {
				batchViewList = batchService.searchBatchEntity(reference.trim(), date, Integer.parseInt(branchOfficeId));
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
			this.batchView.setBatchDocuments(null);
			this.batchView.setBatchAmount(null);
			this.batchView.setBatchDate(new Date());
			this.batchView.setUserView(userView);			
			this.batchView.setBranchOfficeView(branchOfficeView);
		} catch (BranchOfficeException e) {
			logger.error(e);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		}
		JSFUtil.redirect("digitalizacion.xhtml");
	}
	
	public void goViewDigitalizationAction(ActionEvent actionEvent){
		try {
			this.userView = JSFUtil.getSessionAttribute(UserView.class, "user");
			this.branchOfficeView = branchOfficeService.validateBranchOffice(Integer.parseInt(userView.getSucursalId()));
			this.batchView.setBatchDocuments(null);
			this.batchView.setBatchAmount(null);
			this.batchView.setReference("");
			this.currencySelected ="-1";
			this.batchView.setBatchDate(new Date());
			this.batchView.setUserView(userView);
			this.batchView.setBranchOfficeView(branchOfficeView);
		} catch (BranchOfficeException e) {
			logger.error(e);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		}
		JSFUtil.redirect("digitalizacion.xhtml");
	}
	
	public void goViewDigitalization(){
		try {
			this.batchDocumentView = batchService.getBatchViewById(batchId);
		} catch (BatchException e) {
			logger.error(e.getCustomError(), e);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		}
		JSFUtil.redirect("ver_digitalizacion.xhtml");
	}
	
	public void deleteBatch(){
		try{
			batchService.deleteBatch(batchId);									
			cleanValues();
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El Lote se ha eliminado exitosamente.", "El lote se ha eliminado exitosamente.");						
		} catch (BatchException e) {
			logger.error(e.getCustomError(), e);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Ha Ocurrido un error al tratar de eliminar el lote.", "Ha Ocurrido un error al tratar de eliminar el lote.");
		}
		JSFUtil.redirect("cheques.xhtml");
	}
	
	public void insertBatch(){
		try {			
			this.batchView.setCurrencyView(this.currencyService.getCurrencyById(Integer.parseInt(currencySelected)));
			this.batchService.insertBatch(batchView);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El Lote se ha guardado exitosamente.", "El lote se ha guardado exitosamente.");
		} catch (BatchException e) {		
			logger.error(e.getCustomError(), e);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		} catch (CurrencyException e) {
			logger.error(e.getCustomError(), e);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());		
		}		
	}
	
	public void back(){
		try {
			this.userView = JSFUtil.getSessionAttribute(UserView.class, "user");
			this.branchOfficeView = branchOfficeService.validateBranchOffice(Integer.parseInt(userView.getSucursalId()));
			this.batchView.setUserView(userView);
			this.batchView.setBranchOfficeView(branchOfficeView);
		} catch (BranchOfficeException e) {
			logger.error(e);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		}
		JSFUtil.redirect("cheques.xhtml");
	}
	
	public void cleanValues(){
		this.batchViewList = new ArrayList<BatchView>(0);
		this.branchOfficeView = new BranchOfficeView();
		this.userView = new UserView();
		this.page = 0;
		this.reference = "";		
		this.renderTable = Boolean.FALSE;
		this.branchOfficeId = JSFUtil.getSessionAttribute(UserView.class, "user").getSucursalId();
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
			logger.error(e);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		}
		return currencyList;
	}
	
	public void validateForm(){
		if(this.batchView.getReference() != null){
			if(!this.batchView.getReference().equals("")){
				JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Ingrese una referencia.", "Ingrese una Referencia.");
			}
		}
	}
	
	public boolean isBranchOfficeAllowed(){
		boolean branchofficeallowed = Boolean.FALSE;		
		try {
			branchofficeallowed = securityAuthorizationService.hasPermission("7", JSFUtil.getSessionAttribute(UserView.class, "user").getLogin());
		} catch (UserException userException) {
			logger.error(userException);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, userException.getMessage(), userException.getMessage());
		}
		return !branchofficeallowed;
	}	
	
	public boolean isDigitize(){
		boolean digitize = Boolean.FALSE;		
		try {
			digitize = securityAuthorizationService.hasPermission("2", JSFUtil.getSessionAttribute(UserView.class, "user").getLogin());
		} catch (UserException userException) {
			logger.info(userException.getCustomError(), userException);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, userException.getMessage(), userException.getMessage());
		}
		return digitize;
	}
		
	public void sendToPrintAction(){
		FacesContext context = FacesContext.getCurrentInstance();		
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment;filename=\"Cheques.pdf\"");		
		Document document = new Document();
		StringBuilder filePath = null; 
		try{
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();			
			Image check = null;
			int index = 0;
			for(DocumentView view : batchDocumentView.getDocumentList()){				
				filePath = new StringBuilder("http://localhost:8080/digicheck/image?id=" + view.getDocId());
				check = Image.getInstance(new URL(filePath.toString()));
				check.scaleToFit(400, 400);
				document.add(new Paragraph(""));
				document.add(new Paragraph(""));
				if(index%2 == 0){				
					document.newPage();
				}
				document.add(check);								
				index++;
			}											
			document.close(); 
			context.responseComplete();
		}catch(DocumentException e){
			logger.error(e);			
		} catch (IOException e) {
			logger.error(e);
		}	
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
	public void setSecurityAuthorizationService(
			SecurityAuthorizationService securityAuthorizationService) {
		this.securityAuthorizationService = securityAuthorizationService;
	}
	public BatchView getBatchDocumentView() {
		return batchDocumentView;
	}
	public void setBatchDocumentView(BatchView batchDocumentView) {
		this.batchDocumentView = batchDocumentView;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}		
}
