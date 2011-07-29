package com.sterling.digicheck.permission.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sterling.digicheck.user.permission.entity.UserPermissionEntity;
import com.sterling.digicheck.user.permissiontype.entity.PermissionTypeEntity;

@Entity
@Table(name = "PERMISO")
@NamedQueries({@NamedQuery(name = "PermissionEntity.findAll", query = "SELECT p FROM PermissionEntity p"), 
			   @NamedQuery(name = "PermissionEntity.findByPerId", query = "SELECT p FROM PermissionEntity p WHERE p.perId = :perId"), 
			   @NamedQuery(name = "PermissionEntity.findByPerDescripcion", query = "SELECT p FROM PermissionEntity p WHERE p.perDescripcion = :perDescripcion")})
public class PermissionEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8066096585595234860L;
	@Id
    @Basic(optional = false)
    @Column(name = "PER_ID")
    private Integer perId;
    @Basic(optional = false)
    @Column(name = "PER_DESCRIPCION")
    private String perDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "permiso")
    private Collection<UserPermissionEntity> usuarioPermisoCollection;
    @JoinColumn(name = "PET_CODE", referencedColumnName = "PET_CODE")
    @ManyToOne(optional = false)
    private PermissionTypeEntity petCode;

    public PermissionEntity() {
    }

    public PermissionEntity(Integer perId) {
        this.perId = perId;
    }

    public PermissionEntity(Integer perId, String perDescripcion) {
        this.perId = perId;
        this.perDescripcion = perDescripcion;
    }

    public Integer getPerId() {
        return perId;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    public String getPerDescripcion() {
        return perDescripcion;
    }

    public void setPerDescripcion(String perDescripcion) {
        this.perDescripcion = perDescripcion;
    }

    public Collection<UserPermissionEntity> getUsuarioPermisoCollection() {
        return usuarioPermisoCollection;
    }

    public void setUsuarioPermisoCollection(Collection<UserPermissionEntity> usuarioPermisoCollection) {
        this.usuarioPermisoCollection = usuarioPermisoCollection;
    }

    public PermissionTypeEntity getPetCode() {
        return petCode;
    }

    public void setPetCode(PermissionTypeEntity petCode) {
        this.petCode = petCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perId != null ? perId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermissionEntity)) {
            return false;
        }
        PermissionEntity other = (PermissionEntity) object;
        if ((this.perId == null && other.perId != null) || (this.perId != null && !this.perId.equals(other.perId))) {
            return false;
        }
        return true;
    }
   
}
