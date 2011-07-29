package com.sterling.digicheck.user.view;

import java.io.Serializable;
import java.util.List;

import com.sterling.digicheck.user.permission.view.UserPermissionView;

public class UserView implements Serializable {
	private static final long serialVersionUID = 1091505038339380532L;
	private String sucursalId;
	private String login;
	private String name;
	private String password;
	private List<UserPermissionView> userPermissionViews;

	public UserView() {
		
	}
	
	public UserView(String nLogin){
		login = nLogin;
	}
	
	public UserView(String nSucursalId, String nLogin, String nNombre, String nPassword) {
		sucursalId = nSucursalId;
		login = nLogin;
		name= nNombre;
		password = nPassword;
	}
	
	public void goEditUser(){
		
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public String getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(String sucursalId) {
		this.sucursalId = sucursalId;
	}
	public String getName() {
		return name;
	}
	public void setName(String nombre) {
		this.name = nombre;
	}		
	
	public List<UserPermissionView> getUserPermissionViews() {
		return userPermissionViews;
	}

	public void setUserPermissionViews(List<UserPermissionView> userPermissionViews) {
		this.userPermissionViews = userPermissionViews;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof UserView))
			return false;
		UserView userView = (UserView)obj;		
		return this.getLogin().equals(userView.getLogin());		
	}
	
}