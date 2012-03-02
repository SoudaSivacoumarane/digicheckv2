package com.sterling.digicheck.profiles.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sterling.digicheck.user.entity.UserEntity;
/**
 * 
 * @author rioslore
 *
 */
@Entity
@Table(name = "PERFIL")
@NamedQueries({
		@NamedQuery(name = "ProfileEntity.findAll", query = "SELECT p FROM ProfileEntity p"), 
		@NamedQuery(name = "ProfileEntity.findByPrfId", query = "SELECT p FROM ProfileEntity p WHERE p.prfId = :prfId"), 
		@NamedQuery(name = "ProfileEntity.findByPrfDescripcion", query = "SELECT p FROM ProfileEntity p WHERE p.profileDescription = :prfDescripcion"
)})
public class ProfileEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2579457409143051411L;
	@Id
	@Basic(optional = false)
	@Column(name = "PRF_ID")
	private Integer prfId;
	@Column(name = "PRF_DESCRIPCION", length = 50)
	private String profileDescription;
	@OneToMany(mappedBy = "profileEntity")
	private List<UserEntity> userEntities;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "permissionEntity")
	private List<ProfilePermissionEntity> profilePermissionEntityCollection;

	public Integer getPrfId() {
		return prfId;
	}

	public void setPrfId(Integer prfId) {
		this.prfId = prfId;
	}

	public String getProfileDescription() {
		return profileDescription;
	}

	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}

	public List<UserEntity> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(List<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}

	public List<ProfilePermissionEntity> getProfilePermissionEntityCollection() {
		return profilePermissionEntityCollection;
	}

	public void setProfilePermissionEntityCollection(
			List<ProfilePermissionEntity> profilePermissionEntityCollection) {
		this.profilePermissionEntityCollection = profilePermissionEntityCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (prfId != null ? prfId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ProfileEntity)) {
			return false;
		}
		ProfileEntity other = (ProfileEntity) object;
		if ((this.prfId == null && other.prfId != null)
				|| (this.prfId != null && !this.prfId.equals(other.prfId))) {
			return false;
		}
		return true;
	}

}
