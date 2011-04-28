/**
 * 
 */
package com.sterling.common.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Edgar Joao
 */
public abstract class GenericDAO {
	
	protected EntityManager em = null;	
	
	@PersistenceContext
	public void setEntityManager(EntityManager em){
		this.em = em;
	}
}
