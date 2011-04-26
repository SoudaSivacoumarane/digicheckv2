package com.sterling.digicheck.bank.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BANCO")
public class BankEntity implements Serializable {
	private static final long serialVersionUID = 5113528482715104208L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BAN_ID")
	private Integer bankId;
	@Column(name = "BAN_CODIGO")
	private String code;
	@Column(name = "BAN_NOMBRE")
	private String name;
	@Column(name = "BAN_CUENTA")
	private String account;

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer nBankId) {
		bankId = nBankId;
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

	@Column(name = "BAN_CUENTA")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}