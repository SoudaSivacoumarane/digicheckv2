package com.cpi.sterling.document.dto;

import java.io.Serializable;

public class DocumentDTO implements Serializable {
	private static final long serialVersionUID = 113988406665907423L;
	private int docId;
	private byte[] file;
	private int docTypeId;
	private int checkId;
	
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
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