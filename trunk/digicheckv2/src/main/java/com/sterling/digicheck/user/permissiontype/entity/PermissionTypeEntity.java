package com.sterling.digicheck.user.permissiontype.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sterling.digicheck.permission.entity.PermissionEntity;
@Entity
@Table(name = "TIPO_PERMISO")
@NamedQueries({@NamedQuery(name = "PermissionTypeEntity.findAll", query = "SELECT t FROM PermissionTypeEntity t"), 
			   @NamedQuery(name = "PermissionTypeEntity.findByPetCode", query = "SELECT t FROM PermissionTypeEntity t WHERE t.petCode = :petCode"), 
			   @NamedQuery(name = "PermissionTypeEntity.findByPetDescripcion", query = "SELECT t FROM PermissionTypeEntity t WHERE t.petDescripcion = :petDescripcion")})
public class PermissionTypeEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5039795980793035732L;
	@Id
    @Basic(optional = false)
    @Column(name = "PET_CODE")
    private String petCode;
    @Column(name = "PET_DESCRIPCION")
    private String petDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "petCode")
    private Collection<PermissionEntity> permisoCollection;

    public PermissionTypeEntity() {
    }

    public PermissionTypeEntity(String petCode) {
        this.petCode = petCode;
    }

    public String getPetCode() {
        return petCode;
    }

    public void setPetCode(String petCode) {
        this.petCode = petCode;
    }

    public String getPetDescripcion() {
        return petDescripcion;
    }

    public void setPetDescripcion(String petDescripcion) {
        this.petDescripcion = petDescripcion;
    }

    public Collection<PermissionEntity> getPermisoCollection() {
        return permisoCollection;
    }

    public void setPermisoCollection(Collection<PermissionEntity> permisoCollection) {
        this.permisoCollection = permisoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (petCode != null ? petCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermissionTypeEntity)) {
            return false;
        }
        PermissionTypeEntity other = (PermissionTypeEntity) object;
        if ((this.petCode == null && other.petCode != null) || (this.petCode != null && !this.petCode.equals(other.petCode))) {
            return false;
        }
        return true;
    }

}
