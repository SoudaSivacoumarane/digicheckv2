package com.sterling.digicheck.bank.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "BANCO")
@NamedQueries({@NamedQuery(name = "BankEntity.findAll", query = "select b from BankEntity b")})
public class BankEntity implements Serializable {
	private static final long serialVersionUID = 5113528482715104208L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BAN_ID")
	private Integer bankId;
	@Column(name = "ABA")
	private String aba;
	@Column(name = "DESCRIPCION")
	private String description;
	
	public BankEntity() {
	}
	
	public BankEntity(Integer argBankId, String argABA, String argDescription) {
		this.bankId = argBankId;
		this.aba = argABA;
		this.description = argDescription;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer nBankId) {
		bankId = nBankId;
	}	

	public String getAba() {
		return aba;
	}

	public void setAba(String aba) {
		this.aba = aba;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}