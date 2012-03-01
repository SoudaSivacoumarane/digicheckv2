package com.sterling.digicheck.profiles.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * 
 * @author rioslore
 *
 */
@Embeddable
public class ProfilePermissionPK implements Serializable {

	@Basic(optional = false)
	@Column(name = "PRF_ID")
	private int prfId;
	@Basic(optional = false)
	@Column(name = "PER_ID")
	private int perId;

	public ProfilePermissionPK() {
		// TODO Auto-generated constructor stub
	}

	public ProfilePermissionPK(int prfId, int perId) {
		this.prfId = prfId;
		this.perId = perId;
	}

	public int getPrfId() {
		return prfId;
	}

	public void setPrfId(int prfId) {
		this.prfId = prfId;
	}

	public int getPerId() {
		return perId;
	}

	public void setPerId(int perId) {
		this.perId = perId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) prfId;
		hash += (int) perId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ProfilePermissionPK)) {
			return false;
		}
		ProfilePermissionPK other = (ProfilePermissionPK) object;
		if (this.prfId != other.prfId) {
			return false;
		}
		if (this.perId != other.perId) {
			return false;
		}
		return true;
	}

}
