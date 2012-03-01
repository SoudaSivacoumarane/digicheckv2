package com.sterling.digicheck.profiles.converter;

import com.sterling.digicheck.profiles.entity.ProfilePermissionEntity;
import com.sterling.digicheck.profiles.view.ProfilePermissionsView;

public class ProfileConverter {

	ProfilePermissionsView convertEntityToView(ProfilePermissionEntity entity, boolean selected){
		ProfilePermissionsView view = null;
		if(entity != null){
			view = new ProfilePermissionsView();
			view.setPerDescription(entity.getPermissionEntity().getPerDescripcion());
			view.setPerId(entity.getProfilePermissionPK().getPerId());
			view.setPrfId(entity.getProfilePermissionPK().getPrfId());
			view.setSelected(selected);
		}
		return view;
	}
	
	
}
