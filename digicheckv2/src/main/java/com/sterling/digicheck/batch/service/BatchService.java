package com.sterling.digicheck.batch.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterling.common.util.NumberUtil;
import com.sterling.common.util.TimeUtils;
import com.sterling.digicheck.batch.converter.BatchConverter;
import com.sterling.digicheck.batch.dao.BatchDAO;
import com.sterling.digicheck.batch.entity.BatchEntity;
import com.sterling.digicheck.batch.exception.BatchException;
import com.sterling.digicheck.batch.view.BatchView;
import com.sterling.digicheck.dailyreport.view.DailyReportView;
import com.sterling.digicheck.monthlyreport.view.MonthlyReportView;

@Service("batchService")
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
	
	public void insertBatch(BatchView batchView) throws BatchException{
		BatchEntity batchEntity = null;
		BatchConverter batchConverter = null;
		try{
			batchConverter = new BatchConverter();
			batchEntity = batchConverter.convertEntityToView(batchView);
			this.batchDAO.insertBatchEntity(batchEntity);			
		}catch (BatchException bankException){
			throw bankException;
		}catch (Exception exception){
			BatchException batchException = null;
			batchException = new BatchException(exception, BatchException.LAYER_SERVICE, BatchException.ACTION_INSERT);
    		logger.error(batchException);
    		exception.printStackTrace(System.out);
    		throw batchException;
		}
	}
	
	public BatchView getBatchViewById(String batchId) throws BatchException{
		BatchView batchView = null;
		BatchConverter batchConverter = null;
		try{
			batchConverter = new BatchConverter();
			batchView = batchConverter.convertViewToEntity(batchDAO.getBatchEntityById(batchId));		
		}catch (BatchException bankException){
			throw bankException;
		}catch (Exception exception){
			BatchException batchException = null;
			batchException = new BatchException(exception, BatchException.LAYER_SERVICE, BatchException.ACTION_INSERT);
    		logger.error(batchException);
    		exception.printStackTrace(System.out);
    		throw batchException;
		}
		return batchView;
	}
	
	public List<MonthlyReportView> searchMonthlyReport(String month, String year, String branchOfficeId) throws BatchException{
		List<MonthlyReportView> monthlyReportViewList = null;
		List<BatchEntity> batchEntities = null;
		try{
			monthlyReportViewList = new ArrayList<MonthlyReportView>(0);
			batchEntities = batchDAO.searchMonthlyReport(month, year, branchOfficeId);
			System.out.println(batchEntities.size());			
			if(batchEntities != null){
				MonthlyReportView view = null;
				monthlyReportViewList = new ArrayList<MonthlyReportView>(0);
				for (BatchEntity b : batchEntities) {					
					view = new MonthlyReportView();	
					view.setDate(TimeUtils.convertJavaDateToString(b.getBatchDate()));
					view.setTotalDocNum(b.getBatchDocuments());
					view.setDocNum(String.valueOf(b.getBatchDocuments()));
					view.setReference(b.getReference());
					view.setTotalAmount(b.getBatchAmount().doubleValue());
					view.setCurrency(b.getCurrencyId().getName());
					view.setAmount(NumberUtil.convertQuantity(b.getBatchAmount().doubleValue()));
					monthlyReportViewList.add(view);
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
		return monthlyReportViewList;
	}
	
	public List<DailyReportView> searchDailyReport(String branchOfficeId, Date day) throws BatchException{
		List<DailyReportView> dailyReportViewList = null;
		List<BatchEntity> batchEntities = null;
		try{
			 batchEntities = batchDAO.searchDailyReport(branchOfficeId, day);
			 if(batchEntities != null){
				 dailyReportViewList = new ArrayList<DailyReportView>(0);
				 DailyReportView view = null;				 
				 for (BatchEntity b : batchEntities) {
					view = new DailyReportView();	
					view.setDate(TimeUtils.convertJavaDateToString(b.getBatchDate()));
					view.setTotalDocNum(b.getBatchDocuments());
					view.setDocNum(String.valueOf(b.getBatchDocuments()));
					view.setReference(b.getReference());
					view.setTotalAmount(b.getBatchAmount().doubleValue());
					view.setCurrency(b.getCurrencyId().getName());
					view.setAmount(NumberUtil.convertQuantity(b.getBatchAmount().doubleValue()));
					dailyReportViewList.add(view);
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
		return dailyReportViewList;
	}
}
