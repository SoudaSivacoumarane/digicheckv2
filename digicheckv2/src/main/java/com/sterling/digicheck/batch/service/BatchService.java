package com.sterling.digicheck.batch.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterling.digicheck.batch.converter.BatchConverter;
import com.sterling.digicheck.batch.dao.BatchDAO;
import com.sterling.digicheck.batch.entity.BatchEntity;
import com.sterling.digicheck.batch.exception.BatchException;
import com.sterling.digicheck.batch.view.BatchView;

@Service("BatchService")
public class BatchService {
	
	private static final Logger logger = Logger.getLogger(BatchService.class);
	
	@Autowired
	private BatchDAO batchDAO;
	
	public List<BatchView> searchBatchEntity(String reference, Date date, Integer branchOfficeId) throws BatchException{
		List<BatchEntity> batchEntities = null;
		List<BatchView> batchViewsList = null;
		BatchConverter batchConverter = null;
		try{
			batchConverter = new BatchConverter();
			batchEntities = batchDAO.searchBatchEntity(reference, date, branchOfficeId);
			if(batchEntities != null){
				if(!batchEntities.isEmpty()){
					batchViewsList = batchConverter.convertViewsToEntities(batchEntities);
				}
			}						
		}catch (BatchException bankException){
			throw bankException;
		}catch (Exception exception){
			BatchException batchException = null;
			batchException = new BatchException(exception, BatchException.LAYER_SERVICE, BatchException.ACTION_LISTS);
    		logger.error(batchException);
    		exception.printStackTrace(System.out);
    		throw batchException;
		}			
		return batchViewsList; 
	}

}
