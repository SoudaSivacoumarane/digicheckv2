package com.sterling.digicheck.user.permission.pk;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserPermissionPK implements Serializable{
	 /**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2205208208505371773L;
	@Basic(optional = false)
	    @Column(name = "USU_LOGIN")
	    private String usuLogin;
	    @Basic(optional = false)
	    @Column(name = "PER_ID")
	    private int perId;

	    public UserPermissionPK() {
	    }

	    public UserPermissionPK(String usuLogin, int perId) {
	        this.usuLogin = usuLogin;
	        this.perId = perId;
	    }

	    public String getUsuLogin() {
	        return usuLogin;
	    }

	    public void setUsuLogin(String usuLogin) {
	        this.usuLogin = usuLogin;
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
	        hash += (usuLogin != null ? usuLogin.hashCode() : 0);
	        hash += (int) perId;
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof UserPermissionPK)) {
	            return false;
	        }
	        UserPermissionPK other = (UserPermissionPK) object;
	        if ((this.usuLogin == null && other.usuLogin != null) || (this.usuLogin != null && !this.usuLogin.equals(other.usuLogin))) {
	            return false;
	        }
	        if (this.perId != other.perId) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "digicheck.entities.UsuarioPermisoPK[usuLogin=" + usuLogin + ", perId=" + perId + "]";
	    }
}
