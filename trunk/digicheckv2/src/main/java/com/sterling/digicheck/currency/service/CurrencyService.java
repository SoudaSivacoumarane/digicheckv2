package com.sterling.digicheck.currency.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterling.digicheck.currency.converter.CurrencyConverter;
import com.sterling.digicheck.currency.dao.CurrencyDAO;
import com.sterling.digicheck.currency.entity.CurrencyEntity;
import com.sterling.digicheck.currency.exception.CurrencyException;
import com.sterling.digicheck.currency.view.CurrencyView;

@Service("currencyService")
public class CurrencyService {

	private static final Logger logger = Logger.getLogger(CurrencyService.class);
	@Autowired
	private CurrencyDAO currencyDAO;
	
	public List<CurrencyView> getAllCurrencys()throws CurrencyException{
		List<CurrencyView> currencyViews = null;
		List<CurrencyEntity> currencyEntities = null;
		CurrencyConverter currencyConverter = null;		
		try{
			currencyConverter = new CurrencyConverter();			
			currencyEntities = currencyDAO.getAllCurrencys();
			if(currencyEntities != null){
				currencyViews = currencyConverter.converterEntitiesToViews(currencyEntities);
			}
		}catch (CurrencyException currencyException){
			throw currencyException;
		}catch (Exception exception){
			CurrencyException currencyException = null;
			currencyException = new CurrencyException(exception, CurrencyException.LAYER_SERVICE, CurrencyException.ACTION_LISTS);
    		logger.error(currencyException);
    		exception.printStackTrace(System.out);
    		throw currencyException;
		}
		return currencyViews;		
	}
	
	public void insertCurrency(CurrencyView view)throws CurrencyException{
		CurrencyEntity entity = null;
		CurrencyConverter currencyConverter = null;
		try{
			currencyConverter = new CurrencyConverter();
			entity = currencyConverter.convertViewToEntity(view);
			currencyDAO.insertCurrency(entity);
		}catch (CurrencyException currencyException){
			throw currencyException;
		}catch (Exception exception){
			CurrencyException currencyException = null;
			currencyException = new CurrencyException(exception, CurrencyException.LAYER_SERVICE, CurrencyException.ACTION_INSERT);
    		logger.error(currencyException);
    		exception.printStackTrace(System.out);
    		throw currencyException;
		}
	}
	
	public void updateCurrency(CurrencyView view)throws CurrencyException{
		CurrencyEntity entity = null;
		CurrencyConverter currencyConverter = null;
		try{
			currencyConverter = new CurrencyConverter();
			entity = currencyConverter.convertViewToEntity(view);
			currencyDAO.updateCurrency(entity);
		}catch (CurrencyException currencyException){
			throw currencyException;
		}catch (Exception exception){
			CurrencyException currencyException = null;
			currencyException = new CurrencyException(exception, CurrencyException.LAYER_SERVICE, CurrencyException.ACTION_UPDATE);
    		logger.error(currencyException);
    		exception.printStackTrace(System.out);
    		throw currencyException;
		}
	}
	
	public void deleteCurrency(Integer currencyId)throws CurrencyException{		
		try{										
			currencyDAO.deleteCurrency(currencyId);
		}catch (Exception exception){
			CurrencyException currencyException = null;
			currencyException = new CurrencyException(exception, CurrencyException.LAYER_SERVICE, CurrencyException.ACTION_DELETE);
    		logger.error(currencyException);
    		exception.printStackTrace(System.out);
    		throw currencyException;
		}
	}
	
	public CurrencyView validateCurrency(int domainCurrencyName)throws CurrencyException{
		CurrencyView currencyView = null;
		CurrencyEntity currencyEntity = null;
		CurrencyConverter currencyConverter = null;
		try{
			currencyConverter = new CurrencyConverter();
			currencyEntity = currencyDAO.validateCurrency(domainCurrencyName);
			if(currencyEntity!=null){
				currencyView = currencyConverter.converterEntityToAuthView(currencyEntity);
			}
		} catch (CurrencyException currencyException){
			throw currencyException;
		} catch (Exception exception){
			CurrencyException currencyException = null;
			currencyException = new CurrencyException(exception, CurrencyException.LAYER_SERVICE, CurrencyException.ACTION_SELECT);
    		logger.error(currencyException);
    		exception.printStackTrace(System.out);
    		throw currencyException;
		}
		return currencyView;
	}
	
}
