package com.cpi.sterling.document.view;

import java.io.Serializable;

public class DocumentView implements Serializable {
	private static final long serialVersionUID = -8710097868582814732L;

	public static final int TYPE_FRONT = 1;

	public static final int TYPE_BACK = 2;
	
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
