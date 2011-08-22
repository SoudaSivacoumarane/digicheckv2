package com.sterling.digicheck.document.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sterling.digicheck.check.entity.CheckEntity;
import com.sterling.digicheck.documenttype.entity.DocumentTypeEntity;

@Entity
@Table(name = "DOCUMENTO")
@NamedQueries({@NamedQuery(name = "DocumentEntity.findAll", query = "SELECT d FROM DocumentEntity d"), 
			   @NamedQuery(name = "DocumentEntity.findByDocId", query = "SELECT d FROM DocumentEntity d WHERE d.docId = :docId"), 
			   @NamedQuery(name = "DocumentEntity.findByDocNumero", query = "SELECT d FROM DocumentEntity d WHERE d.docNumber = :docNumber")})
public class DocumentEntity implements Serializable{
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1737662553508000671L;
	@Id
    @Basic(optional = false)
    @Column(name = "DOC_ID")
    private Integer docId;
    @Basic(optional = false)
    @Column(name = "DOC_NUMERO")
    private int docNumber;
    @Basic(optional = false)
    @Lob
    @Column(name = "DOC_ARCHIVO")
    private byte[] docFile;
    @JoinColumn(name = "CHQ_ID", referencedColumnName = "CHQ_ID")
    @ManyToOne(optional = false)
    private CheckEntity checkEntity;
    @JoinColumn(name = "DOT_ID", referencedColumnName = "DOT_ID")
    @ManyToOne
    private DocumentTypeEntity documentType;       
    
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public int getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(int docNumber) {
		this.docNumber = docNumber;
	}
	public byte[] getDocFile() {
		return docFile;
	}
	public void setDocFile(byte[] docFile) {
		this.docFile = docFile;
	}	
	public CheckEntity getCheckEntity() {
		return checkEntity;
	}
	public void setCheckEntity(CheckEntity checkEntity) {
		this.checkEntity = checkEntity;
	}
	public DocumentTypeEntity getDocumentType() {
		return documentType;
	}
	public void setDocumentType(DocumentTypeEntity documentType) {
		this.documentType = documentType;
	}
	
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (docId != null ? docId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentEntity)) {
            return false;
        }
        DocumentEntity other = (DocumentEntity) object;
        if ((this.docId == null && other.docId != null) || (this.docId != null && !this.docId.equals(other.docId))) {
            return false;
        }
        return true;
    }

}
