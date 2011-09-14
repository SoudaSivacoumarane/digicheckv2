package com.sterling.digicheck.dailyreport.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sterling.common.util.JSFUtil;
import com.sterling.common.util.NumberUtil;
import com.sterling.common.util.TimeUtils;
import com.sterling.digicheck.batch.exception.BatchException;
import com.sterling.digicheck.batch.service.BatchService;
import com.sterling.digicheck.branchoffice.exception.BranchOfficeException;
import com.sterling.digicheck.branchoffice.service.BranchOfficeService;
import com.sterling.digicheck.dailyreport.view.DailyReportView;
import com.sterling.digicheck.user.view.UserView;

@ManagedBean(name="dailyReportManagedBean")
@ViewScoped
public class DailyReportManagedBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6632709176244971797L;
		
	@ManagedProperty("#{branchOfficeService}")
	BranchOfficeService branchOfficeService;
	private List<DailyReportView> reportList = new ArrayList<DailyReportView>(0);
	@ManagedProperty("#{batchService}")
	private BatchService batchService;
	private String branchOfficeCode;
	private Date day = new Date();
	private String header;
	private String totalAmount;
	private String totalDocNum;
	
	public DailyReportManagedBean() {
		this.branchOfficeCode = JSFUtil.getSessionAttribute(UserView.class, "user").getSucursalId();
	}
	
	public List<SelectItem> getBranchOfficeItems(){
		List<SelectItem> bItems = null;
		try {
			bItems = branchOfficeService.branchOfficeItems();
		} catch (BranchOfficeException e) {
			e.printStackTrace();
		}
		return bItems;
	}
	
	
	public String performSearch(){
		if(branchOfficeCode.equals("-1")){
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Seleccione al menos una Sucursal", "Seleccione al menos una Sucursal");
		}else{
			try {
				reportList = batchService.searchDailyReport(branchOfficeCode, day);
				if(reportList != null){
					if(!reportList.isEmpty()){									
						this.header = "Fecha: " + TimeUtils.convertJavaDateToString(day);
						double totalA = 0;
						int totalDoc = 0;
						for (DailyReportView r : reportList) {
							totalA += r.getTotalAmount();
							totalDoc += r.getTotalDocNum();
						}
						this.totalAmount = NumberUtil.convertQuantity(totalA);
						this.totalDocNum ="$ " + String.valueOf(totalDoc);
					}else{
						JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "No se encontraron Resultados.", "No se encontraron Resultados.");
					}
				}else{
					JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "No se encontraron Resultados.", "No se encontraron Resultados.");
				}
			} catch (BatchException e) {
				JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Hubo un error al generar el Reporte Diario.", "Hubo un error al generar el Reporte Diario.");
			}
		}
		return null;
	}
	
	public void excelExportAction(){
		FacesContext context = FacesContext.getCurrentInstance();
		WritableWorkbook workbook = null;
		try {
			//create workbook and sheet for output
			HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "inline;filename=\"ReporteDiario.xls\"");
			response.setContentType("application/xls");
			
			workbook = Workbook.createWorkbook(response.getOutputStream());
			WritableSheet s = workbook.createSheet("Reporte Diario", 0);
			s.addCell(new Label(0, 0, "Hello World"));
			for (DailyReportView report : reportList) {
				s.addCell(new Label(0, 0, report.getDate()));
				s.addCell(new Label(0, 0, report.getReference()));
				s.addCell(new Label(0, 0, report.getAmount()));
			}			
			s.addCell(new Label(0, 0, this.totalDocNum));
			s.addCell(new Label(0, 0, this.totalAmount));

			workbook.write();
			workbook.close();
			context.responseComplete();
		} catch(IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} 
	}
	
	public void pdfExportAction(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment;filename=\"ReporteDiario.pdf\"");		
		Document document = new Document();
		try{
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();			
			document.add(new Paragraph(header));
			PdfPTable table = new PdfPTable(5);
			table.addCell("Fecha");
			table.addCell("Referencia");
			table.addCell("Divisa");
			table.addCell("No. Doc.");
			table.addCell("Importe");						
			for (DailyReportView report : reportList) {
				table.addCell(report.getDate());
				table.addCell(report.getReference());
				table.addCell(report.getCurrency());
				table.addCell(report.getDocNum());
				table.addCell(report.getAmount());
			}
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell(totalDocNum);
			table.addCell(totalAmount);
			
			document.add(table);		
			document.close(); 
			context.responseComplete();
		}catch(DocumentException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public String getBranchOfficeCode() {
		return branchOfficeCode;
	}

	public void setBranchOfficeCode(String branchOfficeCode) {
		this.branchOfficeCode = branchOfficeCode;
	}

	public void setBranchOfficeService(BranchOfficeService branchOfficeService) {
		this.branchOfficeService = branchOfficeService;
	}

	public List<DailyReportView> getReportList() {
		return reportList;
	}

	public void setReportList(List<DailyReportView> reportList) {
		this.reportList = reportList;
	}	

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}
		
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalDocNum() {
		return totalDocNum;
	}

	public void setTotalDocNum(String totalDocNum) {
		this.totalDocNum = totalDocNum;
	}

	public void setBatchService(BatchService batchService) {
		this.batchService = batchService;
	}					

}
