package com.cpi.sterling.lot.dto;

import java.io.Serializable;
import java.util.Calendar;

public class LotDTO implements Serializable {
	private static final long serialVersionUID = -7611262709048089785L;
	private int id;
	private int sucId;
	private int divId;
	private String reference;
	private double amount;
	private int noDocs;
	private String user;
	private Calendar date;
	private Calendar addDate;
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
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Calendar getAddDate() {
		return addDate;
	}
	public void setAddDate(Calendar addDate) {
		this.addDate = addDate;
	}
}