package com.sterling.digicheck.dailyreport.view;

import java.io.Serializable;

public class DailyReportView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7225663640715768908L;
	private String date;
	private String reference;
	private String currency;
	private String docNum;
	private String amount;
	private double totalAmount;
	private int totalDocNum;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getTotalDocNum() {
		return totalDocNum;
	}
	public void setTotalDocNum(int totalDocNum) {
		this.totalDocNum = totalDocNum;
	}
		
}
