package com.sterling.digicheck.profiles.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Profile View
 * @author rioslore
 *
 */
public class ProfileView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6734523063195272120L;
	private String shortName;
	private List<ProfilePermissionsView> permissionsViews = new ArrayList<ProfilePermissionsView>(0);
	
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
