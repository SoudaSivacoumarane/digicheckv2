package com.sterling.digicheck.user.permission.entity;

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
import com.sterling.digicheck.user.entity.UserEntity;
import com.sterling.digicheck.user.permission.pk.UserPermissionPK;

@Entity
@Table(name = "USUARIO_PERMISO")
@NamedQueries({@NamedQuery(name = "UserPermissionEntity.findAll", query = "SELECT u FROM UserPermissionEntity u"), 
			   @NamedQuery(name = "UserPermissionEntity.findByUsuLogin", query = "SELECT u FROM UserPermissionEntity u WHERE u.usuarioPermisoPK.usuLogin = :usuLogin"), 
			   @NamedQuery(name = "UserPermissionEntity.findByPerId", query = "SELECT count(*) FROM UserPermissionEntity u WHERE u.usuarioPermisoPK.perId = :perId AND u.usuarioPermisoPK.usuLogin = :usuLogin"), 
			   @NamedQuery(name = "UserPermissionEntity.findByUspFecha", query = "SELECT u FROM UserPermissionEntity u WHERE u.uspFecha = :uspFecha")})
public class UserPermissionEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9047079830881342041L;
	@EmbeddedId
    protected UserPermissionPK usuarioPermisoPK;
    @Basic(optional = false)
    @Column(name = "USP_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uspFecha;
    @JoinColumn(name = "PER_ID", referencedColumnName = "PER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PermissionEntity permiso;
    @JoinColumn(name = "USU_LOGIN", referencedColumnName = "USU_LOGIN", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserEntity usuario;

    public UserPermissionEntity() {
    }

    public UserPermissionEntity(UserPermissionPK usuarioPermisoPK) {
        this.usuarioPermisoPK = usuarioPermisoPK;
    }

    public UserPermissionEntity(UserPermissionPK usuarioPermisoPK, Date uspFecha) {
        this.usuarioPermisoPK = usuarioPermisoPK;
        this.uspFecha = uspFecha;
    }

    public UserPermissionEntity(String usuLogin, int perId) {
        this.usuarioPermisoPK = new UserPermissionPK(usuLogin, perId);
    }
    
    public UserPermissionEntity(String usuLogin, int perId, Date uspFecha, PermissionEntity argPermiso, UserEntity argUsuario) {
        this.usuarioPermisoPK = new UserPermissionPK(usuLogin, perId);
        this.uspFecha = uspFecha;
        this.permiso = argPermiso;
        this.usuario = argUsuario;
    }

    public UserPermissionPK getUsuarioPermisoPK() {
        return usuarioPermisoPK;
    }

    public void setUsuarioPermisoPK(UserPermissionPK usuarioPermisoPK) {
        this.usuarioPermisoPK = usuarioPermisoPK;
    }

    public Date getUspFecha() {
        return uspFecha;
    }

    public void setUspFecha(Date uspFecha) {
        this.uspFecha = uspFecha;
    }

    public PermissionEntity getPermiso() {
        return permiso;
    }

    public void setPermiso(PermissionEntity permiso) {
        this.permiso = permiso;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioPermisoPK != null ? usuarioPermisoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserPermissionEntity)) {
            return false;
        }
        UserPermissionEntity other = (UserPermissionEntity) object;
        if ((this.usuarioPermisoPK == null && other.usuarioPermisoPK != null) || (this.usuarioPermisoPK != null && !this.usuarioPermisoPK.equals(other.usuarioPermisoPK))) {
            return false;
        }
        return true;
    }
   
}
