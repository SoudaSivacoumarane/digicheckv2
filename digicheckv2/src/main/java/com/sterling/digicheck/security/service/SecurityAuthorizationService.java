package com.sterling.digicheck.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterling.digicheck.user.exception.UserException;
import com.sterling.digicheck.user.permission.dao.UserPermissionDAO;

@Service("securityAuthorizationService")
public class SecurityAuthorizationService {
	
	@Autowired
	private UserPermissionDAO userPermissionDAO;
	
	public boolean hasPermission(String permission, String login) throws UserException {
		return userPermissionDAO.hasPermission(permission, login);
	}
	
}
