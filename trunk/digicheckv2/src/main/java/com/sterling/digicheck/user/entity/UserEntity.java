package com.sterling.digicheck.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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

}
