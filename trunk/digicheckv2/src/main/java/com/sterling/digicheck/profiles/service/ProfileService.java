package com.sterling.digicheck.profiles.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterling.digicheck.profiles.converter.ProfileConverter;
import com.sterling.digicheck.profiles.dao.ProfileDAO;
import com.sterling.digicheck.profiles.entity.ProfileEntity;
import com.sterling.digicheck.profiles.entity.ProfilePermissionEntity;
import com.sterling.digicheck.profiles.exception.ProfileException;
import com.sterling.digicheck.profiles.view.ProfilePermissionsView;
import com.sterling.digicheck.profiles.view.ProfileView;

@Service("profileService")
public class ProfileService {

	private static final Logger logger = Logger.getLogger(ProfileService.class);
	
	@Autowired
	private ProfileDAO profileDAO;
	
	public List<ProfileView> permissionList(String login) throws ProfileException{
		List<ProfileView> profileViews = new ArrayList<ProfileView>(0);			
		try{
			ProfileConverter converter = new ProfileConverter();			
			List<ProfileEntity> profileEntities = profileDAO.getProfileEntity();
			for(ProfileEntity pe : profileEntities){
				ProfileView profileView = new ProfileView();
				profileView.setShortName(pe.getProfileDescription());
				System.out.println("Permisos ---> " + pe.getProfilePermissionEntityCollection().size());
				for(ProfilePermissionEntity pp : pe.getProfilePermissionEntityCollection()){
					
					ProfilePermissionsView proPermissionsView = converter.convertEntityToView(pp);
					profileView.getPermissionsViews().add(proPermissionsView);
				}
				profileViews.add(profileView);
			}
		}catch(Exception exception){
			ProfileException profileException = null;
			profileException = new ProfileException(exception, ProfileException.LAYER_SERVICE, ProfileException.ACTION_LISTS);
    		logger.error(profileException);
    		exception.printStackTrace(System.out);
    		throw profileException;
		}	
		return profileViews;
	}
	
	public void saveOrUpdate(List<ProfilePermissionsView> views) throws ProfileException{
		
	}
}
