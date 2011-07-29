package com.sterling.digicheck.branchoffice.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sterling.common.dao.GenericDAO;
import com.sterling.digicheck.branchoffice.entity.BranchOfficeEntity;
import com.sterling.digicheck.branchoffice.exception.BranchOfficeException;

@Repository
public class BranchOfficeDAO extends GenericDAO {
	
	private static final Logger logger = Logger.getLogger(BranchOfficeDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<BranchOfficeEntity> getAllBranchOffices()throws BranchOfficeException{		
		List<BranchOfficeEntity> branchOfficeEntities = null;		
		Query query = null;
		try{
			query = em.createNamedQuery("BranchOfficeEntity.findAll");
			branchOfficeEntities = query.getResultList();				
		}catch (Exception exception){
			BranchOfficeException branchOfficeException = null;
			branchOfficeException = new BranchOfficeException(exception, BranchOfficeException.ACTION_DAO, BranchOfficeException.ACTION_SELECT);
    		logger.error(branchOfficeException);
    		exception.printStackTrace(System.out);
    		throw branchOfficeException;
		}
		return branchOfficeEntities;		
	}
	
	public void insertBranchOffice(BranchOfficeEntity branchOfficeEntity)throws BranchOfficeException{				
		try{
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(branchOfficeEntity);
			em.getTransaction().commit();
		}catch (Exception exception){
			BranchOfficeException branchOfficeException = null;
			branchOfficeException = new BranchOfficeException(exception, BranchOfficeException.ACTION_DAO, BranchOfficeException.ACTION_SELECT);
    		logger.error(branchOfficeException);
    		exception.printStackTrace(System.out);
    		throw branchOfficeException;
		}
	}
	
	public void updateBranchOffice(BranchOfficeEntity branchOfficeEntity)throws BranchOfficeException{		
		try{						
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(branchOfficeEntity);
			em.getTransaction().commit();		
		}catch (Exception exception){
			BranchOfficeException branchOfficeException = null;
			branchOfficeException = new BranchOfficeException(exception, BranchOfficeException.LAYER_DAO, BranchOfficeException.ACTION_SELECT);
    		logger.error(branchOfficeException);
    		exception.printStackTrace(System.out);
    		throw branchOfficeException;
		}
	}

	public void deleteBranchOffice(Integer sucId)throws BranchOfficeException{
		BranchOfficeEntity entity = null;		
		try{			
			em = emf.createEntityManager();
			em.getTransaction().begin();
			entity = em.find(BranchOfficeEntity.class, sucId);
			em.remove(entity);
			em.getTransaction().commit();
		}catch (Exception exception){
			BranchOfficeException branchOfficeException = null;
			branchOfficeException = new BranchOfficeException(exception, BranchOfficeException.LAYER_DAO, BranchOfficeException.ACTION_DELETE);
    		logger.error(branchOfficeException);
    		exception.printStackTrace(System.out);
    		throw branchOfficeException;
		}
	}
	
	public BranchOfficeEntity validateBranchOffice(int branchOfficeId)throws BranchOfficeException{		
		BranchOfficeEntity branchOfficeEntity = null;		
		try{			
			branchOfficeEntity = em.find(BranchOfficeEntity.class, branchOfficeId);				
		} catch (Exception exception){
			BranchOfficeException branchOfficeException = null;
			branchOfficeException = new BranchOfficeException(exception, BranchOfficeException.LAYER_DAO, BranchOfficeException.ACTION_SELECT);
    		logger.error(branchOfficeException);
    		exception.printStackTrace(System.out);
    		throw branchOfficeException;
		}
		return branchOfficeEntity;
	}
	
}
