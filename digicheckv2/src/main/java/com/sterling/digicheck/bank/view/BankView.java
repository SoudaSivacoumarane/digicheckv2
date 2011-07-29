/**
 * 
 */
package com.sterling.digicheck.bank.view;

import java.io.Serializable;

/**
 * Bank View
 * 
 * @author Edgar Joao
 */
public class BankView implements Serializable {
	/** Serial Version ID */
	private static final long serialVersionUID = -4836470143973592753L;
	private String bankId;
	private String aba;
	private String description;

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
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

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BankView))
			return false;
		BankView bankView = (BankView) obj;
		return this.getBankId().equals(bankView.getBankId());
	}
}
