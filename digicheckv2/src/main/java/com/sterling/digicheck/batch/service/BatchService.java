package com.sterling.digicheck.batch.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterling.digicheck.batch.dao.BatchDAO;
import com.sterling.digicheck.batch.exception.BatchException;
import com.sterling.digicheck.batch.view.BatchView;

@Service("BatchService")
public class BatchService {
	
	@Autowired
	private BatchDAO batchDAO;
	
	private List<BatchView> searchBatchEntity(String reference, Date date, Integer branchOfficeId) throws BatchException{
		List<BatchView> batchViewsList = null;
		
		 //batchDAO.searchBatchEntity(reference, date, branchOfficeId);
		
		return null; 
	}

}
