package com.sterling.digicheck.branchoffice.view;

import java.io.Serializable;

import com.sterling.digicheck.state.view.StateView;

public class BranchOfficeView implements Serializable {
	private static final long serialVersionUID = 1091505038339380532L;
	private String sucId;
	private String name;
	private String address;
	private String community;
	private String zip;
	private StateView state = new StateView();
	private String city;

	public BranchOfficeView() {
		
	}	
	
	public BranchOfficeView(String argSucId, String nName, String nAddress, String nCommunity, String nZip, StateView nState, String nCity) {
		this.sucId = argSucId;
		this.name = nName;
		this.address = nAddress;
		this.community = nCommunity;
		this.zip = nZip;
		this.state = nState;
		this.city = nCity;
	}
	
	
	
	public String getSucId() {
		return sucId;
	}

	public void setSucId(String sucId) {
		this.sucId = sucId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public StateView getState() {
		return state;
	}
	public void setState(StateView state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
}