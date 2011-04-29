/**
 * 
 */
package com.sterling.common.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * @author Edgar Joao
 */
@Repository
public abstract class GenericDAO {
		
	protected EntityManager em;
	
	@PersistenceContext	
	public void setEntityManager(EntityManager em){
		this.em = em;
	}
}
