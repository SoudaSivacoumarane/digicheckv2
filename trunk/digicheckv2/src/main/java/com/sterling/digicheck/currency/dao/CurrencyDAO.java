package com.sterling.digicheck.currency.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sterling.common.dao.GenericDAO;
import com.sterling.digicheck.currency.entity.CurrencyEntity;
import com.sterling.digicheck.currency.exception.CurrencyException;

@Repository
public class CurrencyDAO extends GenericDAO {

	private static final Logger logger = Logger.getLogger(CurrencyDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<CurrencyEntity> getAllCurrencys()throws CurrencyException{		
		List<CurrencyEntity> currencyEntities = null;		
		Query query = null;
		try{			
			query = em.createNamedQuery("CurrencyEntity.findAll");
			currencyEntities = query.getResultList();			
		}catch (Exception exception){
			CurrencyException currencyException = null;
			currencyException = new CurrencyException(exception, CurrencyException.LAYER_DAO, CurrencyException.ACTION_LISTS);
    		logger.error(currencyException);
    		exception.printStackTrace(System.out);
    		throw currencyException;
		}
		return currencyEntities;		
	}
	
	public void insertCurrency(CurrencyEntity currencyEntity)throws CurrencyException{			
		try{		
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(currencyEntity);	
			em.getTransaction().commit();
		}catch (Exception exception){
			CurrencyException currencyException = null;
			currencyException = new CurrencyException(exception, CurrencyException.LAYER_DAO, CurrencyException.ACTION_INSERT);
    		logger.error(currencyException);
    		exception.printStackTrace(System.out);
    		throw currencyException;
		}
	}
	
	public void updateCurrency(CurrencyEntity currencyEntity)throws CurrencyException{		
		try{
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(currencyEntity);
			em.getTransaction().commit();
		}catch (Exception exception){
			CurrencyException currencyException = null;
			currencyException = new CurrencyException(exception, CurrencyException.LAYER_DAO, CurrencyException.ACTION_UPDATE);
    		logger.error(currencyException);
    		exception.printStackTrace(System.out);
    		throw currencyException;
		}
	}
	
	public void deleteCurrency(Integer currencyId)throws CurrencyException{		
		CurrencyEntity entity = null;
		try{									
			em = emf.createEntityManager();
			em.getTransaction().begin();
			entity = em.find(CurrencyEntity.class, currencyId);
			em.remove(entity);
			em.getTransaction().commit();
		}catch (Exception exception){
			CurrencyException currencyException = null;
			currencyException = new CurrencyException(exception, CurrencyException.LAYER_DAO, CurrencyException.ACTION_DELETE);
    		logger.error(currencyException);
    		exception.printStackTrace(System.out);
    		throw currencyException;
		}
	}
	
	public CurrencyEntity validateCurrency(int domainCurrencyName)throws CurrencyException{		
		CurrencyEntity currencyEntity = null;		
		try{						
			currencyEntity = em.find(CurrencyEntity.class, domainCurrencyName);				
		} catch (Exception exception){
			CurrencyException currencyException = null;
			currencyException = new CurrencyException(exception, CurrencyException.LAYER_SERVICE, CurrencyException.ACTION_SELECT);
    		logger.error(currencyException);
    		exception.printStackTrace(System.out);
    		throw currencyException;
		}
		return currencyEntity;
	}
	
}
