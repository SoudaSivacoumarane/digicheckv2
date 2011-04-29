package com.sterling.digicheck.state.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sterling.digicheck.state.exception.StateException;
import com.sterling.digicheck.state.view.StateView;
import com.sterling.digicheck.state.entity.StateEntity;

public class StateConverter {

	private static final Log log = LogFactory.getLog(StateConverter.class);
	
	
	public List<StateView> converterEntitiesToViews(List<StateEntity> stateEntities)throws StateException{
		List<StateView> stateViews = null;
		StateView stateView = null;
		StateEntity stateEntity = null;
		Iterator<StateEntity> iteraror = null;
		try{
			stateViews = new ArrayList<StateView>(0);
			iteraror = stateEntities.iterator();
			while(iteraror.hasNext()){
				stateEntity = iteraror.next();
				stateView = converterEntityToAuthView(stateEntity);
				stateViews.add(stateView);
			}
		}catch (Exception exception){
			StateException stateException = null;
			stateException = new StateException(exception, StateException.LAYER_CONVERTER, StateException.ACTION_SELECT);
			log.error(stateException);
			exception.printStackTrace(System.out);
			throw stateException;
		}
		return stateViews;
	}
	
	
	public StateView converterEntityToAuthView(StateEntity stateEntity)throws StateException{
		StateView stateView = null;

		try{
			stateView = new StateView();			
			stateView.setCode(stateEntity.getCode());			
			stateView.setName(stateEntity.getName());
			
		} catch (Exception exception){
			StateException stateException = null;
			stateException = new StateException(exception, StateException.LAYER_CONVERTER, StateException.ACTION_SELECT);
			log.error(stateException);
			exception.printStackTrace(System.out);
			throw stateException;
		}
		return stateView;
	}
	
	
	public List<SelectItem> converterViewsToItems(List<StateView> stateViews)throws StateException{
		List<SelectItem> stateItems= null;
		StateView stateView = null;
		SelectItem stateItem = null;
		Iterator<StateView> iteraror = null;
		try{
			stateItems = new ArrayList<SelectItem>(0);
			iteraror = stateViews.iterator();
			while(iteraror.hasNext()){
				stateView = iteraror.next();
				stateItem = new SelectItem();
				stateItem.setValue(stateView.getCode());
				stateItem.setLabel(stateView.getName());
				stateItems.add(stateItem);
			}
		}catch (Exception exception){
			StateException stateException = null;
			stateException = new StateException(exception, StateException.LAYER_CONVERTER, StateException.ACTION_SELECT);
			log.error(stateException);
			exception.printStackTrace(System.out);
			throw stateException;
		}
		return stateItems;
	}
	
}
