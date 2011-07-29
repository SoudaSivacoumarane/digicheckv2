package com.sterling.digicheck.user.permission.view;

import java.io.Serializable;

public class UserPermissionView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2332652777493565031L;
	private String description;
	private boolean hasPermission;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isHasPermission() {
		return hasPermission;
	}
	public void setHasPermission(boolean hasPermission) {
		this.hasPermission = hasPermission;
	}		
	
}
