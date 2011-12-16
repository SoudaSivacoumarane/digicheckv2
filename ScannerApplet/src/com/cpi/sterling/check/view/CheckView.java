package com.cpi.sterling.check.view;

import java.io.Serializable;
import java.util.List;

import com.cpi.sterling.document.view.DocumentView;

public class CheckView implements Serializable{
	private static final long serialVersionUID = 1728847140087177547L;
	private int chqId;
	private int lotId;
	private String abba;
	private String account;
	private double amount;
	private List<DocumentView> documentList;
	public CheckView() {
	}
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
	public List<DocumentView> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<DocumentView> documentList) {
		this.documentList = documentList;
	}
}