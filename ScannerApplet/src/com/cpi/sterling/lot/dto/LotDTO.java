package com.cpi.sterling.lot.dto;

import java.io.Serializable;
import java.sql.Date;

public class LotDTO implements Serializable {
	private static final long serialVersionUID = -5606007881700788460L;
	private int id;
	private int sucId;
	private int divId;
	private String reference;
	private double amount;
	private int noDocs;
	private String user;
	private Date date;
	private Date addDate;
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
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
}