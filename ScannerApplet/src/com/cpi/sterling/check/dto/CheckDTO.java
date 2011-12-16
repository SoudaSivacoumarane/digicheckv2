package com.cpi.sterling.check.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cpi.sterling.document.dto.DocumentDTO;

public class CheckDTO implements Serializable {
	private static final long serialVersionUID = -6913604891449751245L;
	private int chqId;
	private int lotId;
	private String abba;
	private String account;
	private double amount;
	private List<DocumentDTO> documentDTOList = new ArrayList<DocumentDTO>(0);
	
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
	public List<DocumentDTO> getDocumentDTOList() {
		return documentDTOList;
	}
	public void setDocumentDTOList(List<DocumentDTO> documentDTOList) {
		this.documentDTOList = documentDTOList;
	}
}