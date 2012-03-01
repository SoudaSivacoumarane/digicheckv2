package com.sterling.digicheck.profiles.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sterling.digicheck.permission.entity.PermissionEntity;

/**
 * 
 * @author rioslore
 * 
 */
@Entity
@Table(name = "PERFIL_PERMISO")
@NamedQueries({
	@NamedQuery(name = "ProfilePermissionEntity.findAll", query = "SELECT p FROM ProfilePermissionEntity p"), 
	@NamedQuery(name = "ProfilePermissionEntity.findByPrfId", query = "SELECT p FROM ProfilePermissionEntity p WHERE p.profilePermissionPK.prfId = :prfId"), 
	@NamedQuery(name = "ProfilePermissionEntity.findByPerId", query = "SELECT p FROM ProfilePermissionEntity p WHERE p.profilePermissionPK.perId = :perId"), 
	@NamedQuery(name = "ProfilePermissionEntity.findByPepFecha", query = "SELECT p FROM ProfilePermissionEntity p WHERE p.pepDate = :pepDate"
)})
public class ProfilePermissionEntity implements Serializable {

	@EmbeddedId
	protected ProfilePermissionPK profilePermissionPK;
	@Basic(optional = false)
	@Column(name = "PEP_FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date pepDate;
	@JoinColumn(name = "PRF_ID", referencedColumnName = "PRF_ID", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private ProfileEntity profile;
	@JoinColumn(name = "PER_ID", referencedColumnName = "PER_ID", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private PermissionEntity permissionEntity;

	public ProfilePermissionEntity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (profilePermissionPK != null ? profilePermissionPK.hashCode() : 0);
		return hash;
	}
	
	public ProfilePermissionPK getProfilePermissionPK() {
		return profilePermissionPK;
	}

	public void setProfilePermissionPK(ProfilePermissionPK profilePermissionPK) {
		this.profilePermissionPK = profilePermissionPK;
	}
	
	public Date getPepDate() {
		return pepDate;
	}

	public void setPepDate(Date pepDate) {
		this.pepDate = pepDate;
	}

	public ProfileEntity getProfile() {
		return profile;
	}

	public void setProfile(ProfileEntity profile) {
		this.profile = profile;
	}

	public PermissionEntity getPermissionEntity() {
		return permissionEntity;
	}

	public void setPermissionEntity(PermissionEntity permissionEntity) {
		this.permissionEntity = permissionEntity;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ProfilePermissionEntity)) {
			return false;
		}
		ProfilePermissionEntity other = (ProfilePermissionEntity) object;
		if ((this.profilePermissionPK == null && other.profilePermissionPK != null)
				|| (this.profilePermissionPK != null && !this.profilePermissionPK
						.equals(other.profilePermissionPK))) {
			return false;
		}
		return true;
	}
}
