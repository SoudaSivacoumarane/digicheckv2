package com.sterling.digicheck.state.view;

import java.io.Serializable;

public class StateView implements Serializable {
	private static final long serialVersionUID = 1091505038339380532L;
	private String code;	
	private String name;
	

	public StateView() {		
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
}