package com.sterling.digicheck.document.view;

import java.io.Serializable;

import com.sterling.digicheck.batch.view.BatchView;
import com.sterling.digicheck.documenttype.view.DocumentTypeView;

public class DocumentView implements Serializable {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6093243672520498445L;
	 
    private Integer docId;	    
    private Integer docNumber;	    
    private byte[] docFile;	    
    private BatchView batchView;	        
    private DocumentTypeView documentTypeView;
    
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public Integer getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(Integer docNumber) {
		this.docNumber = docNumber;
	}
	public byte[] getDocFile() {
		return docFile;
	}
	public void setDocFile(byte[] docFile) {
		this.docFile = docFile;
	}
	public BatchView getBatchView() {
		return batchView;
	}
	public void setBatchView(BatchView batchView) {
		this.batchView = batchView;
	}
	public DocumentTypeView getDocumentTypeView() {
		return documentTypeView;
	}
	public void setDocumentTypeView(DocumentTypeView documentTypeView) {
		this.documentTypeView = documentTypeView;
	}           	
}
