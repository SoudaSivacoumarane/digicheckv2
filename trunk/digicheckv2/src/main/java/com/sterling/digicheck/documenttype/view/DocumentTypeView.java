package com.sterling.digicheck.documenttype.view;

import java.io.Serializable;

public class DocumentTypeView implements Serializable {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6135829121823779706L;
	
	private Character docTypeId;    
    private String docTypeDescription;
    
	public Character getDocTypeId() {
		return docTypeId;
	}
	public void setDocTypeId(Character docTypeId) {
		this.docTypeId = docTypeId;
	}
	public String getDocTypeDescription() {
		return docTypeDescription;
	}
	public void setDocTypeDescription(String docTypeDescription) {
		this.docTypeDescription = docTypeDescription;
	}        
}
