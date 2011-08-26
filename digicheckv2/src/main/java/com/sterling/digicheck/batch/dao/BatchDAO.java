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
	public List<BatchEntity> searchBatchEntity(String reference, Date date, Integer branchOfficeId) throws BatchException{
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