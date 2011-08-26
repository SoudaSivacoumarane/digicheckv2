package com.cpi.sterling.lot.view;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.cpi.sterling.check.view.CheckView;

public class LotView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5734035597642648721L;
	
	private int id;
	private int sucId;
	private int divId;
	private String reference;
	private double amount;
	private int noDocs;
	private String user;
	private Date date;
	private List<CheckView> chekViewList;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSucId() {
		return sucId;
	}
	public void setSucId(int sucId) {
		this.sucId = sucId;
	}
	public int getDivId() {
		return divId;
	}
	public void setDivId(int divId) {
		this.divId = divId;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getNoDocs() {
		return noDocs;
	}
	public void setNoDocs(int noDocs) {
		this.noDocs = noDocs;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<CheckView> getChekViewList() {
		return chekViewList;
	}
	public void setChekViewList(List<CheckView> chekViewList) {
		this.chekViewList = chekViewList;
	}		
}
