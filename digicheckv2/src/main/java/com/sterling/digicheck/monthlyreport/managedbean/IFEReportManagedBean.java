package com.sterling.digicheck.monthlyreport.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.sterling.common.util.JSFUtil;
import com.sterling.common.util.TimeUtils;
import com.sterling.digicheck.batch.exception.BatchException;
import com.sterling.digicheck.batch.service.BatchService;
import com.sterling.digicheck.batch.view.BatchView;
import com.sterling.digicheck.branchoffice.exception.BranchOfficeException;
import com.sterling.digicheck.branchoffice.service.BranchOfficeService;
/**
 * 
 * @author joao
 *
 */
@ManagedBean(name="ifeBean")
@RequestScoped
public class IFEReportManagedBean implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 963840873225011666L;

	private static final Logger logger = Logger.getLogger(IFEReportManagedBean.class);
	
	@ManagedProperty("#{branchOfficeService}")
	BranchOfficeService branchOfficeService;
	@ManagedProperty("#{batchService}")
	private BatchService batchService;
	private String branchOfficeCode;
	private BatchView batchDocumentView;
	private Date date = new Date();
	
	public void sendToPDFAction(){
		try{			
			List<Integer> list = new ArrayList<Integer>(0);
			list = batchService.getIFEsList(branchOfficeCode, TimeUtils.convertJavaDateToString(date));
			if(!list.isEmpty()){
				FacesContext context = FacesContext.getCurrentInstance();		
				HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();		
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition", "attachment;filename=\"IFEs-" + System.currentTimeMillis() + ".pdf\"");		
				Document document = new Document();
				StringBuilder filePath = null; 
				
				PdfWriter.getInstance(document, response.getOutputStream());
				document.open();
				Paragraph header = new Paragraph("IFE ",new Font(Font.BOLD));
				header.setAlignment(Element.ALIGN_CENTER);			
				document.add(header);
				Image check = null;
				int index = 0;
				for(Integer id : list){				
					filePath = new StringBuilder("http://localhost:8080/digicheck/image?id=" + id.intValue());
					check = Image.getInstance(new URL(filePath.toString()));
					check.scaleToFit(400, 400);
					document.add(new Paragraph(""));
					document.add(new Paragraph(""));
					if(index > 0  && index%2 == 0){				
						document.newPage();
					}
					document.add(check);								
					index++;
				}											
				document.close(); 
				context.responseComplete();
			}else{
				JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "No se encontraron documentos.", "No se encontraron documentos.");
			}
		}catch(DocumentException e){
			logger.error(e);			
		} catch (IOException e) {
			logger.error(e);
		} catch (BatchException e) {
			logger.error(e);
		}	
	}
	
	
	public List<SelectItem> getBranchOfficeItems(){
		List<SelectItem> bItems = null;
		try {
			bItems = branchOfficeService.branchOfficeItems();
		} catch (BranchOfficeException e) {
			e.printStackTrace();
			logger.info(e);
		}
		return bItems;
	}
	
	public void setBatchService(BatchService batchService) {
		this.batchService = batchService;
	}

	public BatchView getBatchDocumentView() {
		return batchDocumentView;
	}

	public void setBatchDocumentView(BatchView batchDocumentView) {
		this.batchDocumentView = batchDocumentView;
	}

	public String getBranchOfficeCode() {
		return branchOfficeCode;
	}

	public void setBranchOfficeCode(String branchOfficeCode) {
		this.branchOfficeCode = branchOfficeCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setBranchOfficeService(BranchOfficeService branchOfficeService) {
		this.branchOfficeService = branchOfficeService;
	}
	
}
