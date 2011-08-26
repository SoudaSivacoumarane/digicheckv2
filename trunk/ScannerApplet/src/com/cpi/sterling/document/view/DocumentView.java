package com.cpi.sterling.document.view;

import java.io.Serializable;

public class DocumentView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3383191080112904408L;
	
	private int docId;
	private int number;
	private byte[] file;
	private int docTypeId;
	private int checkId;
	
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public int getDocTypeId() {
		return docTypeId;
	}
	public void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}
	public int getCheckId() {
		return checkId;
	}
	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}

}
