package com.sterling.digicheck.bank.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sterling.common.dao.GenericDAO;
import com.sterling.digicheck.bank.entity.BankEntity;
import com.sterling.digicheck.bank.exception.BankException;
/**
 * Bank DAO
 * @author Edgar Joao
 *
 */
@Repository
public class BankDAO extends GenericDAO{

	private static final Logger logger = Logger.getLogger(BankDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<BankEntity> getAllBanks() throws BankException{		
		List<BankEntity> bankEntities = null;		
		Query query = null;
		try{	
			logger.info("Obtener Bancos");
			query = em.createNamedQuery("BankEntity.findAll");
			bankEntities = query.getResultList();
		}catch (Exception exception){
			BankException bankException = null;
			bankException = new BankException(exception, BankException.LAYER_DAO, BankException.ACTION_LISTS);
    		logger.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}		
		return bankEntities;
	}
	
	public void insertBank(BankEntity bankEntity)throws BankException{
		try{
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(bankEntity);
			em.getTransaction().commit();
		}catch (Exception exception){
			BankException bankException = null;
			bankException = new BankException(exception, BankException.LAYER_DAO, BankException.ACTION_INSERT);
    		logger.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}		
	}
	
	public void updateBank(BankEntity entity)throws BankException{		
		try{
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();			
		}catch (Exception exception){
			BankException bankException = null;
			bankException = new BankException(exception, BankException.LAYER_DAO, BankException.ACTION_UPDATE);
    		logger.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}
	}
	
	public void deleteBank(Integer bankId)throws BankException{
		BankEntity entity = null;		
		try{					
			em = emf.createEntityManager();
			em.getTransaction().begin();
			entity = em.find(BankEntity.class, bankId);			
			em.remove(entity);			
			em.getTransaction().commit();			
		}catch (Exception exception){
			em.flush();
			BankException bankException = null;
			bankException = new BankException(exception, BankException.LAYER_DAO, BankException.ACTION_DELETE);
    		logger.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}
	}
	
	public BankEntity getBankEntityById(Integer bankId) throws BankException{
		BankEntity entity = null;
		try{
			entity = em.find(BankEntity.class, bankId);			
		}catch (Exception exception){			
			BankException bankException = null;
			bankException = new BankException(exception, BankException.LAYER_DAO, BankException.ACTION_SELECT);
    		logger.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}
		return entity;
	}
}
