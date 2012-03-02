package com.sterling.digicheck.profiles.converter;

import java.util.ArrayList;
import java.util.List;

import com.sterling.digicheck.profiles.entity.ProfilePermissionEntity;
import com.sterling.digicheck.profiles.view.ProfilePermissionsView;

public class ProfileConverter {

	public ProfilePermissionsView convertEntityToView(ProfilePermissionEntity entity){
		ProfilePermissionsView view = null;
		if(entity != null){
			view = new ProfilePermissionsView();
			view.setPerDescription(entity.getPermissionEntity().getPerDescripcion());
			view.setPerId(entity.getProfilePermissionPK().getPerId());
			view.setPrfId(entity.getProfilePermissionPK().getPrfId());
		}
		return view;
	}
	
	public List<ProfilePermissionsView> convertEntitysToViews(List<ProfilePermissionEntity> entities){
		List<ProfilePermissionsView> viewsList = new ArrayList<ProfilePermissionsView>(0);
		if(!entities.isEmpty()){
			for(ProfilePermissionEntity e : entities){
				viewsList.add(convertEntityToView(e));
			}
		}
		return viewsList;
	}
	
	
}
