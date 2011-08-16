package com.sterling.digicheck.currency.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "DIVISA")
@NamedQueries({@NamedQuery(name = "CurrencyEntity.findAll", query = "select c from CurrencyEntity c")})
public class CurrencyEntity implements Serializable {
	private static final long serialVersionUID = -93381186348363225L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DIV_ID")
	private Integer currencyId;
	@Column(name = "DIV_CODIGO")
	private String code;
	@Column(name = "DIV_NOMBRE")
	private String name;
	@Lob
	@Column(name = "DIV_IMAGEN")
	private byte[] image;
	
	public CurrencyEntity() {

	}
	
	public CurrencyEntity(Integer argCurrencyId, String argCode, String argName) {
		this.currencyId = argCurrencyId;
		this.code = argCode;
		this.name = argName;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer nCurrencyId) {
		currencyId = nCurrencyId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
