package com.sterling.digicheck.monthlyreport.managedbean;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sterling.common.util.JSFUtil;
import com.sterling.common.util.NumberUtil;
import com.sterling.common.util.StringUtils;
import com.sterling.digicheck.batch.exception.BatchException;
import com.sterling.digicheck.batch.service.BatchService;
import com.sterling.digicheck.branchoffice.exception.BranchOfficeException;
import com.sterling.digicheck.branchoffice.service.BranchOfficeService;
import com.sterling.digicheck.monthlyreport.view.MonthlyReportView;
import com.sterling.digicheck.user.view.UserView;

@ManagedBean(name="monthlyReportManagedBean")
@ViewScoped
public class MonthlyReportManagedBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8478305475988511100L;
	
	@ManagedProperty("#{branchOfficeService}")
	BranchOfficeService branchOfficeService;	
	private String branchOfficeCode;
	@ManagedProperty("#{batchService}")
	private BatchService batchService;
	private List<MonthlyReportView> reportList = new ArrayList<MonthlyReportView>(0);		
	private String month;
	private String year;
	private String header;
	private String totalAmount;
	private String totalDocNum;
	
	public MonthlyReportManagedBean() {
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
				batchService.searchMonthlyReport(month, year, branchOfficeCode);
			} catch (BatchException e) {
				JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Hubo un error al generar el Reporte Mensual.", "Hubo un error al generar el Reporte Mensual.");
			}						
			reportList = new ArrayList<MonthlyReportView>();			
			try {
				JSFUtil.getSessionAttribute(UserView.class, "user");
				reportList = batchService.searchMonthlyReport(month, year, branchOfficeCode);
				if(!reportList.isEmpty()){
					String branchOfficeName = branchOfficeService.validateBranchOffice(Integer.parseInt(JSFUtil.getSessionAttribute(UserView.class, "user").getSucursalId())).getName();				
					this.header = "Mes: " + StringUtils.getMonth(Integer.parseInt(month)) + "/" + year + " Sucursal " + branchOfficeName;
					double totalA = 0;
					int totalDoc = 0;
					for (MonthlyReportView r : reportList) {
						totalA += r.getTotalAmount();
						totalDoc += r.getTotalDocNum();
					}
					this.totalAmount = "$ " + NumberUtil.convertQuantity(totalA);
					this.totalDocNum = String.valueOf(totalDoc);
				}else{
					JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "No se encontraron Resultados.", "No se encontraron Resultados.");	
				}
				
			} catch (BatchException e) {
				JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Hubo un error al generar el Reporte Mensual.", e.getMessage());
			} catch (BranchOfficeException branchOfficeException){
				JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Hubo un error al generar el Reporte Mensual.", branchOfficeException.getMessage());
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
			response.setHeader("Content-disposition", "inline;filename=\"ReporteMensual.xls\"");
			response.setContentType("application/xls");
			
			workbook = Workbook.createWorkbook(response.getOutputStream());
			WritableSheet s = workbook.createSheet("Reporte Mensual", 0);
			WritableFont grayFont = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
			WritableCellFormat headerCellFormat = new WritableCellFormat(grayFont);
			WritableCellFormat numberCellFormat = new WritableCellFormat();			
			headerCellFormat.setBackground(Colour.GRAY_25);
			headerCellFormat.setAlignment(Alignment.CENTRE);
			headerCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);			
			numberCellFormat.setAlignment(Alignment.RIGHT);
			
			s.addCell(new Label(0, 0, header));
			s.mergeCells(0, 0, 4, 0);			
			s.addCell(new Label(0, 1, "Fecha", headerCellFormat));
			s.addCell(new Label(1, 1, "Referencia", headerCellFormat));
			s.addCell(new Label(2, 1, "Divisa", headerCellFormat));
			s.addCell(new Label(3, 1, "No. Doc.", headerCellFormat));
			s.addCell(new Label(4, 1, "Importe", headerCellFormat));
								
			for (int i = 2; i <= reportList.size() + 1; i++) {	
				MonthlyReportView view = reportList.get(i - 2);				
				s.addCell(new Label(0, i, view.getDate()));
				s.addCell(new Label(1, i, view.getReference()));
				s.addCell(new Label(2, i, view.getCurrency()));
				s.addCell(new Label(3, i, view.getDocNum(),numberCellFormat));
				s.addCell(new Label(4, i, view.getAmount(),numberCellFormat));
			}					
			s.addCell(new Label(3, reportList.size() + 2, totalDocNum, numberCellFormat));
			s.addCell(new Label(4, reportList.size() + 2, totalAmount, numberCellFormat));
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
		response.setHeader("Content-disposition", "attachment;filename=\"ReporteMensual.pdf\"");		
		Document document = new Document();
		try{
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();						
			PdfPTable table = new PdfPTable(5);			
			PdfPCell cellHeader;					
			cellHeader = new PdfPCell(new Phrase(header));			
			cellHeader.setColspan(5);
			table.addCell(cellHeader);
								
			PdfPCell date = null;
			PdfPCell reference = null;
			PdfPCell currency = null;
			PdfPCell docNum = null;
			PdfPCell amount = null;
			
			date = new PdfPCell(new Phrase("Fecha", new Font(Font.BOLD)));
			date.setBackgroundColor(new Color(211, 211, 211));
			date.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			reference = new PdfPCell(new Phrase("Referencia", new Font(Font.BOLD)));
			reference.setBackgroundColor(new Color(211, 211, 211));
			reference.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			currency = new PdfPCell(new Phrase("Divisa", new Font(Font.BOLD)));
			currency.setBackgroundColor(new Color(211, 211, 211));
			currency.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			docNum = new PdfPCell(new Phrase("No. Doc.", new Font(Font.BOLD)));
			docNum.setBackgroundColor(new Color(211, 211, 211));
			docNum.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			amount = new PdfPCell(new Phrase("Importe", new Font(Font.BOLD)));
			amount.setBackgroundColor(new Color(211, 211, 211));
			amount.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.addCell(date);			
			table.addCell(reference);
			table.addCell(currency);
			table.addCell(docNum);
			table.addCell(amount);			
			
			PdfPCell docNumCellAling = null;
			PdfPCell amountCellAling = null;			
			for (MonthlyReportView report : reportList) {									
				table.addCell(new Phrase(report.getDate()));
				table.addCell(new Phrase(report.getReference()));
				table.addCell(new Phrase(report.getCurrency()));
				
				docNumCellAling = new PdfPCell(new Phrase(report.getDocNum()));
				docNumCellAling.setHorizontalAlignment(Element.ALIGN_RIGHT);				
				table.addCell(docNumCellAling);
				
				amountCellAling = new PdfPCell(new Phrase("$ " + report.getAmount()));
				amountCellAling.setHorizontalAlignment(Element.ALIGN_RIGHT);							
				table.addCell(amountCellAling);
			}
			table.addCell("");
			table.addCell("");
			table.addCell("");
			
			PdfPCell totalDocCellAling = null;
			PdfPCell totalAmountCellAling = null;	
			
			totalDocCellAling = new PdfPCell(new Phrase(totalDocNum));
			totalDocCellAling.setHorizontalAlignment(Element.ALIGN_RIGHT);	
						
			totalAmountCellAling = new PdfPCell(new Phrase(totalAmount));
			totalAmountCellAling.setHorizontalAlignment(Element.ALIGN_RIGHT);
						
			table.addCell(totalDocCellAling);
			table.addCell(totalAmountCellAling);
			
			document.add(table);		
			document.close(); 
			context.responseComplete();
		}catch(DocumentException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public List<MonthlyReportView> getReportList() {
		return reportList;
	}

	public void setReportList(List<MonthlyReportView> reportList) {
		this.reportList = reportList;
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
		
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setBatchService(BatchService batchService) {
		this.batchService = batchService;
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
	
}
