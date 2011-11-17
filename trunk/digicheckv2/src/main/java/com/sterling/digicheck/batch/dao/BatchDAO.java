package com.sterling.digicheck.batch.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sterling.common.dao.GenericDAO;
import com.sterling.digicheck.batch.entity.BatchEntity;
import com.sterling.digicheck.batch.exception.BatchException;

@Repository
public class BatchDAO extends GenericDAO {

	private static final Logger logger = Logger.getLogger(BatchDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<BatchEntity> searchBatchEntity(String reference, Date date, Integer branchOfficeId, Integer docType) throws BatchException{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder sql = null;
        List<BatchEntity> batchEntityList = null;
        Date afterTime = new Date();
        Date beforeTime = new Date();
        String before = null;
        String after = null;
        if(date != null){
        	before = formatter1.format(date);
        	after = formatter1.format(date);
        }
        Query query = null;   
        try{            
        	sql = new StringBuilder(0);
        	if(date != null){
        		afterTime = formatter.parse(after + " 23:59:59");
            	beforeTime = formatter.parse(before + " 0:00:00");
        	}
            sql.append("SELECT l FROM BatchEntity l WHERE l.branchOfficeId.sucId = :sucId ");
            if(!reference.equals("")){
            	sql.append(" AND UPPER(l.reference) LIKE :reference ");
            }
            if(date != null){
            	sql.append(" AND l.batchDate BETWEEN :beforeDate AND :afterDate ");
            }                      
            em = emf.createEntityManager();
            em.getTransaction().begin();            
            query = em.createQuery(sql.toString());
            if(!reference.trim().equals("")){
            	query.setParameter("reference", "%"+reference+"%".toUpperCase());
            }
            query.setParameter("sucId", branchOfficeId);
            if(date != null){
            	query.setParameter("beforeDate", beforeTime, TemporalType.TIMESTAMP);
            	query.setParameter("afterDate", afterTime, TemporalType.TIMESTAMP);
            }
                        
            batchEntityList = query.getResultList();
            em.getTransaction().commit();
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
	
	public BatchEntity getBatchEntityById(String batchId) throws BatchException{
		BatchEntity batchEntity = null;
		try{
			batchEntity = em.find(BatchEntity.class, Integer.parseInt(batchId));			
		}catch (Exception exception){
			BatchException bankException = null;
			bankException = new BatchException(exception, BatchException.LAYER_DAO, BatchException.ACTION_SELECT);
    		logger.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}
		return batchEntity;
	}
	
	@SuppressWarnings("unchecked")
	public List<BatchEntity> searchMonthlyReport(String month, String year, String branchOfficeId, Integer documentType) throws BatchException{
		List<BatchEntity> batchEntityList = null;		
		Query query = null;
		try{
			em = emf.createEntityManager();
			em.getTransaction().begin();
			query = em.createQuery("SELECT l FROM BatchEntity l WHERE year(l.batchDate) = ?1 AND month(l.batchDate) = ?2 AND l.branchOfficeId.sucId = ?3");
			query.setParameter(1, Integer.parseInt(year));
			query.setParameter(2, Integer.parseInt(month));			
			query.setParameter(3, Integer.parseInt(branchOfficeId));
			batchEntityList = query.getResultList();			
			em.getTransaction().commit();					
		}catch (Exception exception){
			BatchException bankException = null;
			bankException = new BatchException(exception, BatchException.LAYER_DAO, BatchException.ACTION_SELECT);
    		logger.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}		
		return batchEntityList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<BatchEntity> searchDailyReport(String branchOfficeId, Date day, Integer documentType) throws BatchException{
		List<BatchEntity> batchEntityList = null;				        	
		Query query = null;		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
		Date afterTime = new Date();
        Date beforeTime = new Date();
        String before = null;
        String after = null;        
        before = formatter1.format(day);
        after = formatter1.format(day);               
    	    	
		try{												
			afterTime = formatter.parse(after + " 23:59:59");
	        beforeTime = formatter.parse(before + " 0:00:00");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			query = em.createQuery("SELECT l FROM BatchEntity l WHERE l.branchOfficeId.sucId = ?1 AND l.batchDate BETWEEN ?2 AND ?3");
			query.setParameter(1, Integer.parseInt(branchOfficeId));			
			query.setParameter(2, beforeTime, TemporalType.TIMESTAMP);
        	query.setParameter(3, afterTime, TemporalType.TIMESTAMP);
			batchEntityList = query.getResultList();			
			em.getTransaction().commit();					
		}catch (Exception exception){
			BatchException bankException = null;
			bankException = new BatchException(exception, BatchException.LAYER_DAO, BatchException.ACTION_SELECT);
    		logger.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}		
		return batchEntityList;
	}
	
	public void deleteBatch(String batchId) throws BatchException{				
		try{
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em.createNativeQuery("DELETE FROM LOTE WHERE LOT_ID = ?1");
			query.setParameter(1, Integer.parseInt(batchId));
			query.executeUpdate();
		}catch (Exception exception){
			BatchException bankException = null;
			bankException = new BatchException(exception, BatchException.LAYER_DAO, BatchException.ACTION_SELECT);
    		logger.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}finally{
			em.getTransaction().commit();
		}
	}
	
}
