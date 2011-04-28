package com.sterling.digicheck.bank.dao;

import java.util.List;

import javax.persistence.Query;

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

	@SuppressWarnings("unchecked")
	public List<BankEntity> getAllBanks() throws BankException{		
		List<BankEntity> bankEntities = null;		
		Query query = null;
		try{			
			query = em.createNamedQuery("BankEntity.findAll");
			bankEntities = query.getResultList();					
		}catch (Exception exception){
			BankException bankException = null;
			bankException = new BankException(exception, BankException.LAYER_DAO, BankException.ACTION_LISTS);
    		//log.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}		
		return bankEntities;
	}
	
	public void insertBank(BankEntity bankEntity)throws BankException{
		try{
			em.persist(bankEntity);
		}catch (Exception exception){
			BankException bankException = null;
			bankException = new BankException(exception, BankException.LAYER_DAO, BankException.ACTION_INSERT);
    		//log.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}		
	}
}
