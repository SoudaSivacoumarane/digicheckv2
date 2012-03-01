package com.sterling.digicheck.profiles.view;

import java.io.Serializable;
import java.util.List;

/**
 * Profile View
 * @author rioslore
 *
 */
public class ProfileView implements Serializable{

	private String shortName;
	private List<ProfilePermissionsView> permissionsViews;
	
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<ProfilePermissionsView> getPermissionsViews() {
		return permissionsViews;
	}

	public void setPermissionsViews(List<ProfilePermissionsView> permissionsViews) {
		this.permissionsViews = permissionsViews;
	}
	
}
