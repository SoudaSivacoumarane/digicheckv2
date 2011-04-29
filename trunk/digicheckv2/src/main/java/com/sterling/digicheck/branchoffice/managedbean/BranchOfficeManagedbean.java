package com.sterling.digicheck.branchoffice.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;

import com.sterling.digicheck.branchoffice.service.BranchOfficeService;

@ManagedBean(name="branchOfficeService")
public class BranchOfficeManagedbean implements Serializable {
	/** Serial Version UID */
	private static final long serialVersionUID = -3292054842997979343L;

	private static final Logger logger = Logger.getLogger(BranchOfficeManagedbean.class);
	
	@ManagedProperty("#{branchOfficeService}")
	private BranchOfficeService branchOfficeService;
	
	
	
	
	public void setBranchOfficeService(BranchOfficeService branchOfficeService) {
		this.branchOfficeService = branchOfficeService;
	}
		
}
