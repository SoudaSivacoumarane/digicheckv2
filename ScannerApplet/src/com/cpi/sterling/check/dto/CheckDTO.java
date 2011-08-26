package com.cpi.sterling.check.dto;

import java.io.Serializable;

public class CheckDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8122974379157831923L;
	private int chqId;
	private int lotId;
	private String abba;
	private String account;
	private double amount;
	
	public int getChqId() {
		return chqId;
	}
	public void setChqId(int chqId) {
		this.chqId = chqId;
	}
	public int getLotId() {
		return lotId;
	}
	public void setLotId(int lotId) {
		this.lotId = lotId;
	}
	public String getAbba() {
		return abba;
	}
	public void setAbba(String abba) {
		this.abba = abba;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}		
}
