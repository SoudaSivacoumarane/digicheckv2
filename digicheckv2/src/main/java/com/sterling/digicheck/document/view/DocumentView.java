package com.sterling.digicheck.document.view;

import java.io.Serializable;

import com.sterling.digicheck.documenttype.view.DocumentTypeView;

public class DocumentView implements Serializable {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6093243672520498445L;
	 
    private Integer docId;	          
    private byte[] docFile;	        	       
    private DocumentTypeView documentTypeView;
    
    public DocumentView() {
	}
    
    public DocumentView(Integer id, DocumentTypeView docTypeView) {
    	this.docId = id;
    	this.documentTypeView = docTypeView;
	}
    
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public byte[] getDocFile() {
		return docFile;
	}
	public void setDocFile(byte[] docFile) {
		this.docFile = docFile;
	}
	public DocumentTypeView getDocumentTypeView() {
		return documentTypeView;
	}
	public void setDocumentTypeView(DocumentTypeView documentTypeView) {
		this.documentTypeView = documentTypeView;
	}           	
}
