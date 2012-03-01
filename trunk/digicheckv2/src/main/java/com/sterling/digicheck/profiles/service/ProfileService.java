package com.sterling.digicheck.profiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterling.digicheck.profiles.dao.ProfileDAO;

@Service("profileService")
public class ProfileService {

	@Autowired
	private ProfileDAO profileDAO;
}
