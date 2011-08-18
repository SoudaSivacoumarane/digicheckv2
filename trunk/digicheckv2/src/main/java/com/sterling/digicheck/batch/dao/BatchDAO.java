package com.sterling.digicheck.batch.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sterling.common.dao.GenericDAO;
import com.sterling.digicheck.batch.entity.BatchEntity;
import com.sterling.digicheck.batch.exception.BatchException;

@Repository
public class BatchDAO extends GenericDAO {

	private static final Logger logger = Logger.getLogger(BatchDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<BatchEntity> searchBatchEntity(String reference, Date date, Integer branchOfficeId) throws BatchException{
		List<BatchEntity> batchEntityList = null;		
		Query query = null;
		try{			
			query = em.createNamedQuery("BatchEntity.findBySearchCriteria");
			query.setParameter("reference", "%"+reference+"%".toUpperCase());
			//query.setParameter("batchDate", date, TemporalType.DATE);
			query.setParameter("sucId", branchOfficeId);
			batchEntityList = query.getResultList();
			
		}catch (Exception exception){
			BatchException bankException = null;
			bankException = new BatchException(exception, BatchException.LAYER_DAO, BatchException.ACTION_LISTS);
    		logger.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}
		return batchEntityList;
	}
	
	public void insertBatchEntity(BatchEntity batchEntity) throws BatchException{
		try{
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(batchEntity);
			em.getTransaction().commit();
		}catch (Exception exception){
			BatchException bankException = null;
			bankException = new BatchException(exception, BatchException.LAYER_DAO, BatchException.ACTION_INSERT);
    		logger.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}		
	}
	
}
