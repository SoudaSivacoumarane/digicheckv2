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
	private static final long serialVersionUID = 1715090681946047742L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BAN_ID")
	private Integer bankId;
	@Column(name = "BAN_ABBA")
	private String aba;
	@Column(name = "BAN_DESCRIPCION")
	private String description;

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