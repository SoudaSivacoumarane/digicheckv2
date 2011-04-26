package com.sterling.digicheck.state.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sterling.digicheck.state.entity.StateEntity;

@Repository
public class StateDAO {

	private EntityManager em = null;

	@SuppressWarnings("unchecked")
	public List<StateEntity> getStates() {
		String sql = "select s from StateEntity s";
		return em.createQuery(sql).getResultList();
	}

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

}
