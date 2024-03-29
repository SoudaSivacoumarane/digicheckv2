package com.sterling.digicheck.batch.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sterling.digicheck.branchoffice.entity.BranchOfficeEntity;
import com.sterling.digicheck.check.entity.CheckEntity;
import com.sterling.digicheck.currency.entity.CurrencyEntity;
import com.sterling.digicheck.user.entity.UserEntity;

@Entity
@Table(name = "LOTE")
@SqlResultSetMapping(name="monthlyReportEntity", entities={@EntityResult(entityClass=BatchEntity.class, fields={@FieldResult(name="batchId", column="LOT_ID"), @FieldResult(name="currencyId", column="DIV_ID")}),										
														   @EntityResult(entityClass=CurrencyEntity.class, fields={@FieldResult(name="name", column="DIV_NOMBRE") 
															   													   })})
@NamedQueries({@NamedQuery(name = "BatchEntity.findAll", query = "SELECT l FROM BatchEntity l"),
			   @NamedQuery(name = "BatchEntity.findBySearchCriteria", query = "SELECT l FROM BatchEntity l WHERE UPPER(l.reference) LIKE :reference AND l.branchOfficeId.sucId = :sucId AND l.batchDate BETWEEN :beforeDate AND :afterDate"),
			   @NamedQuery(name = "BatchEntity.findByLotReferencia", query = "SELECT l FROM BatchEntity l WHERE l.reference = :reference"), 
			   @NamedQuery(name = "BatchEntity.findByLotImporte", query = "SELECT l FROM BatchEntity l WHERE l.batchAmount = :batchAmount"), 
			   @NamedQuery(name = "BatchEntity.findByLotId", query = "SELECT l FROM BatchEntity l WHERE l.batchId = :batchId"), 
			   @NamedQuery(name = "BatchEntity.findByLotDocumentos", query = "SELECT l FROM BatchEntity l WHERE l.batchDocuments = :batchDocuments"), 
			   @NamedQuery(name = "BatchEntity.findByLotFecha", query = "SELECT l FROM BatchEntity l WHERE l.batchDate = :batchDate"), 
			   @NamedQuery(name = "BatchEntity.findByLotFechaAlta", query = "SELECT l FROM BatchEntity l WHERE l.batchDateAdded = :batchDateAdded")})
public class BatchEntity implements Serializable {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4472330594862813027L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOT_ID")
    private Integer batchId; 
	@Basic(optional = false)
    @Column(name = "LOT_REFERENCIA")
    private String reference;	
    @Basic(optional = false)
    @Column(name = "LOT_IMPORTE")
    private BigDecimal batchAmount;           
    @Basic(optional = false)
    @Column(name = "LOT_DOCUMENTOS")
    private int batchDocuments;        
    @Basic(optional = false)
    @Column(name = "LOT_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date batchDate;        
    @Basic(optional = false)
    @Column(name = "LOT_FECHA_ALTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date batchDateAdded;                
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "batch")
    private List<CheckEntity> checkEntityCollection;    
    @JoinColumn(name = "DIV_ID", referencedColumnName = "DIV_ID")
    @ManyToOne(optional = false)
    private CurrencyEntity currencyId;    
    @JoinColumn(name = "SUC_ID", referencedColumnName = "SUC_ID")
    @ManyToOne(optional = false)
    private BranchOfficeEntity branchOfficeId;    
    @JoinColumn(name = "USU_LOGIN", referencedColumnName = "USU_LOGIN")
    @ManyToOne(optional = false)
    private UserEntity userLogin;
    @Column(name = "LOT_TIPO")
    private Integer type;  
    
    @PrePersist
    public void onPrePersist() {
    	this.batchDateAdded = new Date();
    }
    
    
	public String getReference() {
		return reference;
	}
	public void setLotReference(String reference) {
		this.reference = reference;
	}
	public BigDecimal getBatchAmount() {
		return batchAmount;
	}
	public void setBatchAmount(BigDecimal batchAmount) {
		this.batchAmount = batchAmount;
	}
	public Integer getBatchId() {
		return batchId;
	}
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	public int getBatchDocuments() {
		return batchDocuments;
	}
	public void setBatchDocuments(int batchDocuments) {
		this.batchDocuments = batchDocuments;
	}
	public Date getBatchDate() {
		return batchDate;
	}
	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}
	public Date getBatchDateAdded() {
		return batchDateAdded;
	}
	public void setBatchDateAdded(Date batchDateAdded) {
		this.batchDateAdded = batchDateAdded;
	}	
	public List<CheckEntity> getCheckEntityCollection() {
		return checkEntityCollection;
	}
	public void setCheckEntityCollection(List<CheckEntity> checkEntityCollection) {
		this.checkEntityCollection = checkEntityCollection;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public CurrencyEntity getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(CurrencyEntity currencyId) {
		this.currencyId = currencyId;
	}
	public BranchOfficeEntity getBranchOfficeId() {
		return branchOfficeId;
	}
	public void setBranchOfficeId(BranchOfficeEntity branchOfficeId) {
		this.branchOfficeId = branchOfficeId;
	}
	public UserEntity getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(UserEntity userLogin) {
		this.userLogin = userLogin;
	}    
	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	@Override
    public int hashCode() {
        int hash = 0;
        hash += (batchId != null ? batchId.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BatchEntity)) {
            return false;
        }
        BatchEntity other = (BatchEntity) object;
        if ((this.batchId == null && other.batchId != null) || (this.batchId != null && !this.batchId.equals(other.batchId))) {
            return false;
        }
        return true;
    }
	
}
