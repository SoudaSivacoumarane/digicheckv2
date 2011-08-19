package com.sterling.digicheck.batch.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
        formatter.setTimeZone(TimeZone.getDefault());
        List<BatchEntity> batchEntityList = null;
        Date afterTime = new Date();
        Date beforeTime = new Date();
        String before = formatter1.format(date);
        String after = formatter1.format(date);
        Query query = null;   
        try{   
            //SELECT l FROM BatchEntity l WHERE UPPER(l.reference) LIKE :reference AND l.branchOfficeId.sucId = :sucId AND l.batchDate BETWEEN :beforeDate AND :afterDate
            afterTime = formatter.parse(after + " 23:59:59");
            beforeTime = formatter.parse(before + " 0:00:00");                       
            query = em.createNamedQuery("BatchEntity.findBySearchCriteria");
            query.setParameter("reference", "%"+reference+"%".toUpperCase());           
            query.setParameter("sucId", branchOfficeId);
            query.setParameter("beforeDate", beforeTime, TemporalType.TIMESTAMP);
            query.setParameter("afterDate", afterTime, TemporalType.TIMESTAMP);
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
