package com.sterling.digicheck.state.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sterling.common.dao.GenericDAO;
import com.sterling.digicheck.state.entity.StateEntity;

@Repository
public class StateDAO extends GenericDAO{
		
	@SuppressWarnings("unchecked")
	public List<StateEntity> getStates() {
		String sql = "select s from StateEntity s";
		return em.createQuery(sql).getResultList();
	}

}
