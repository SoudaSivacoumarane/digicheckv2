package com.sterling.digicheck.check.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sterling.digicheck.batch.entity.BatchEntity;
import com.sterling.digicheck.document.entity.DocumentEntity;

@Entity
@Table(name = "CHEQUE")
@NamedQueries({@NamedQuery(name = "CheckEntity.findAll", query = "SELECT c FROM CheckEntity c"), 
	           @NamedQuery(name = "CheckEntity.findByChqId", query = "SELECT c FROM CheckEntity c WHERE c.chqId = :chqId"), 
	           @NamedQuery(name = "CheckEntity.findByChqAbba", query = "SELECT c FROM CheckEntity c WHERE c.chqAbba = :chqAbba"), 
	           @NamedQuery(name = "CheckEntity.findByChqCuenta", query = "SELECT c FROM CheckEntity c WHERE c.chqAccount = :chqAccount"), 
	           @NamedQuery(name = "CheckEntity.findByChqImporte", query = "SELECT c FROM CheckEntity c WHERE c.chqAmount = :chqAmount")})
public class CheckEntity implements Serializable {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5908099809660543175L;
	
	@Id
    @Basic(optional = false)
    @Column(name = "CHQ_ID")
    private Integer chqId;
    @Basic(optional = false)
    @Column(name = "CHQ_ABBA")
    private String chqAbba;
    @Basic(optional = false)
    @Column(name = "CHQ_CUENTA")
    private String chqAccount;
    @Basic(optional = false)
    @Column(name = "CHQ_IMPORTE")
    private BigDecimal chqAmount;
    @OneToMany(mappedBy = "checkEntity")
    private List<DocumentEntity> documentEntityCollection;
    @JoinColumn(name = "LOT_ID", referencedColumnName = "LOT_ID")
    @ManyToOne(optional = false)
    private BatchEntity batch;
    
    public CheckEntity() {
    }

    public CheckEntity(Integer chqId) {
        this.chqId = chqId;
    }

    public CheckEntity(Integer chqId, String chqAbba, String chqAccount, BigDecimal chqAmount) {
        this.chqId = chqId;
        this.chqAbba = chqAbba;
        this.chqAccount = chqAccount;
        this.chqAmount = chqAmount;
    }

    public Integer getChqId() {
        return chqId;
    }

    public void setChqId(Integer chqId) {
        this.chqId = chqId;
    }

    public String getChqAbba() {
        return chqAbba;
    }

    public void setChqAbba(String chqAbba) {
        this.chqAbba = chqAbba;
    }
    
    public String getChqAccount() {
		return chqAccount;
	}

	public void setChqAccount(String chqAccount) {
		this.chqAccount = chqAccount;
	}

	public BigDecimal getChqAmount() {
		return chqAmount;
	}

	public void setChqAmount(BigDecimal chqAmount) {
		this.chqAmount = chqAmount;
	}

	public List<DocumentEntity> getDocumentEntityCollection() {
		return documentEntityCollection;
	}

	public void setDocumentEntityCollection(
			List<DocumentEntity> documentEntityCollection) {
		this.documentEntityCollection = documentEntityCollection;
	}

	public BatchEntity getBatch() {
		return batch;
	}

	public void setBatch(BatchEntity batch) {
		this.batch = batch;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (chqId != null ? chqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CheckEntity)) {
            return false;
        }
        CheckEntity other = (CheckEntity) object;
        if ((this.chqId == null && other.chqId != null) || (this.chqId != null && !this.chqId.equals(other.chqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sterling.digicheck.check.entity.CheckEntity[chqId=" + chqId + "]";
    }

}
