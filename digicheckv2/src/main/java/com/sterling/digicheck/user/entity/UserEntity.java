package com.sterling.digicheck.user.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sterling.digicheck.user.permission.entity.UserPermissionEntity;

@Entity
@Table(name = "USUARIO")
@NamedQueries({
		@NamedQuery(name = "UserEntity.findAll", query = "select u from UserEntity u"),
		@NamedQuery(name = "UserEntity.login", query = "select u from UserEntity u where u.login = :user and u.password = :pass")
		})
public class UserEntity implements Serializable {
	private static final long serialVersionUID = -2386177213472335274L;
	@Id
	@Column(name = "USU_LOGIN")
	private String login;
	@Column(name = "USU_PASSWORD")
	private String password;
	@Column(name = "USU_NOMBRE")
	private String nombre;
	@Column(name = "SUC_ID")
	private int sucursalId;
	@OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER, mappedBy = "usuario")
    private Collection<UserPermissionEntity> userPermissionEntity;
	
	public UserEntity() {

	}
	
	public UserEntity(String argLogin, String argPassword, String argNombre, int argSucursal) {
		this.login = argLogin;
		this.password = argPassword;
		this.nombre = argNombre;
		this.sucursalId = argSucursal;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	public Collection<UserPermissionEntity> getUserPermissionEntity() {
		return userPermissionEntity;
	}

	public void setUserPermissionEntity(
			Collection<UserPermissionEntity> userPermissionEntity) {
		this.userPermissionEntity = userPermissionEntity;
	}		

}
