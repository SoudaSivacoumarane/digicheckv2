package com.sterling.digicheck.currency.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sterling.digicheck.currency.entity.CurrencyEntity;
import com.sterling.digicheck.currency.exception.CurrencyException;
import com.sterling.digicheck.currency.view.CurrencyView;

public class CurrencyConverter {

	private static final Log log = LogFactory.getLog(CurrencyConverter.class);
	public List<CurrencyView> converterEntitiesToViews(List<CurrencyEntity> currencyEntities)throws CurrencyException{
		List<CurrencyView> currencyViews = null;
		CurrencyView currencyView = null;
		CurrencyEntity currencyEntity = null;
		Iterator<CurrencyEntity> iteraror = null;
		try{
			currencyViews = new ArrayList<CurrencyView>(0);
			iteraror = currencyEntities.iterator();
			while(iteraror.hasNext()){
				currencyEntity = iteraror.next();
				currencyView = converterEntityToAuthView(currencyEntity);
				currencyViews.add(currencyView);
			}
		}catch (Exception exception){
			CurrencyException currencyException = null;
			currencyException = new CurrencyException(exception, CurrencyException.LAYER_CONVERTER, CurrencyException.ACTION_SELECT);
			log.error(currencyException);
			exception.printStackTrace(System.out);
			throw currencyException;
		}
		return currencyViews;
	}
	public CurrencyView converterEntityToAuthView(CurrencyEntity currencyEntity)throws CurrencyException{
		CurrencyView currencyView = null;

		try{
			currencyView = new CurrencyView();			
			currencyView.setCurrencyId(String.valueOf(currencyEntity.getCurrencyId()));			
			currencyView.setCode(currencyEntity.getCode());
			currencyView.setName(currencyEntity.getName());
			//currencyView.setImage(currencyEntity.getImage());
		} catch (Exception exception){
			CurrencyException currencyException = null;
			currencyException = new CurrencyException(exception, CurrencyException.LAYER_CONVERTER, CurrencyException.ACTION_SELECT);
			log.error(currencyException);
			exception.printStackTrace(System.out);
			throw currencyException;
		}
		return currencyView;
	}
	public CurrencyEntity convertViewToEntity(CurrencyView view)throws CurrencyException{
		CurrencyEntity entity = null;		
		try{
			entity = new CurrencyEntity();
			if(view.getCurrencyId()!=null)
				entity.setCurrencyId(Integer.parseInt(view.getCurrencyId()));
			entity.setCode(view.getCode());
			entity.setName(view.getName());
			//entity.setImage(view.getImage());
		}catch (Exception exception){
			CurrencyException currencyException = null;
			currencyException = new CurrencyException(exception, CurrencyException.LAYER_CONVERTER, CurrencyException.ACTION_SELECT);
			log.error(currencyException);
			exception.printStackTrace(System.out);
			throw currencyException;
		}
		return entity;
	}
}
