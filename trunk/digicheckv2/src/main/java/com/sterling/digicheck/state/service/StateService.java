package com.sterling.digicheck.state.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterling.digicheck.state.dao.StateDAO;
import com.sterling.digicheck.state.entity.StateEntity;
@Service("stateService")
public class StateService {
	
	@Autowired
	private StateDAO stateDAO;
	
	public List<StateEntity> getStates() {
		return stateDAO.getStates();
	}

	public void setStateDAO(StateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}		

}
