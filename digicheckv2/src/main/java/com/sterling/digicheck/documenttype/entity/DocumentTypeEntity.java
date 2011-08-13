package com.sterling.digicheck.documenttype.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sterling.digicheck.document.entity.DocumentEntity;

@Entity
@Table(name = "TIPO_DOCUMENTO")
@NamedQueries({@NamedQuery(name = "DocumentTypeEntity.findAll", query = "SELECT t FROM DocumentTypeEntity t"), 
	           @NamedQuery(name = "DocumentTypeEntity.findByDotId", query = "SELECT t FROM DocumentTypeEntity t WHERE t.docTypeId = :docTypeId"), 
	           @NamedQuery(name = "DocumentTypeEntity.findByDotDescripcion", query = "SELECT t FROM DocumentTypeEntity t WHERE t.docTypeDescription = :docTypeDescription")
	           })
public class DocumentTypeEntity implements Serializable {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6383089898421437618L;

	@Id
    @Basic(optional = false)
    @Column(name = "DOT_ID")
    private Character docTypeId;
    @Basic(optional = false)
    @Column(name = "DOT_DESCRIPCION")
    private String docTypeDescription;
    @OneToMany(mappedBy = "documentType")
    private Collection<DocumentEntity> documentEntityCollection;
    
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
	public Collection<DocumentEntity> getDocumentEntityCollection() {
		return documentEntityCollection;
	}
	public void setDocumentEntityCollection(
			Collection<DocumentEntity> documentEntityCollection) {
		this.documentEntityCollection = documentEntityCollection;
	}
    
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (docTypeId != null ? docTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DocumentTypeEntity)) {
            return false;
        }
        DocumentTypeEntity other = (DocumentTypeEntity) object;
        if ((this.docTypeId == null && other.docTypeId != null) || (this.docTypeId != null && !this.docTypeId.equals(other.docTypeId))) {
            return false;
        }
        return true;
    }
	
}
