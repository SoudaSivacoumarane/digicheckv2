package com.sterling.digicheck.branchoffice.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sterling.digicheck.branchoffice.entity.BranchOfficeEntity;
import com.sterling.digicheck.branchoffice.exception.BranchOfficeException;
import com.sterling.digicheck.branchoffice.view.BranchOfficeView;
import com.sterling.digicheck.state.converter.StateConverter;
import com.sterling.digicheck.state.entity.StateEntity;
import com.sterling.digicheck.state.view.StateView;

public class BranchOfficeConverter {

	private static final Log log = LogFactory.getLog(BranchOfficeConverter.class);
	
	public List<BranchOfficeView> converterEntitiesToViews(List<BranchOfficeEntity> branchOfficeEntities)throws BranchOfficeException{
		List<BranchOfficeView> branchOfficeViews = null;
		BranchOfficeView branchOfficeView = null;
		BranchOfficeEntity branchOfficeEntity = null;
		Iterator<BranchOfficeEntity> iteraror = null;
		try{
			branchOfficeViews = new ArrayList<BranchOfficeView>(0);
			iteraror = branchOfficeEntities.iterator();
			while(iteraror.hasNext()){
				branchOfficeEntity = iteraror.next();
				branchOfficeView = converterEntityToAuthView(branchOfficeEntity);
				branchOfficeViews.add(branchOfficeView);
			}
		}catch (Exception exception){
			BranchOfficeException branchOfficeException = null;
			branchOfficeException = new BranchOfficeException(exception, BranchOfficeException.LAYER_CONVERTER, BranchOfficeException.ACTION_SELECT);
			log.error(branchOfficeException);
			exception.printStackTrace(System.out);
			throw branchOfficeException;
		}
		return branchOfficeViews;
	}
	public BranchOfficeView converterEntityToAuthView(BranchOfficeEntity branchOfficeEntity)throws BranchOfficeException{
		BranchOfficeView branchOfficeView = null;
		StateConverter stateConverter = null;

		try{
			branchOfficeView = new BranchOfficeView();
			stateConverter = new StateConverter();
			
			branchOfficeView.setSucId(String.valueOf(branchOfficeEntity.getSucId()));
			branchOfficeView.setName(branchOfficeEntity.getName());
			branchOfficeView.setAddress(branchOfficeEntity.getAddress());
			branchOfficeView.setCity(branchOfficeEntity.getCity());
			branchOfficeView.setCommunity(branchOfficeEntity.getCommunity());
			branchOfficeView.setState(stateConverter.converterEntityToAuthView(branchOfficeEntity.getStateEntity()));
			branchOfficeView.setZip(branchOfficeEntity.getZip());
		} catch (Exception exception){
			BranchOfficeException branchOfficeException = null;
			branchOfficeException = new BranchOfficeException(exception, BranchOfficeException.LAYER_CONVERTER, BranchOfficeException.ACTION_SELECT);
			log.error(branchOfficeException);
			exception.printStackTrace(System.out);
			throw branchOfficeException;
		}
		return branchOfficeView;
	}
	public BranchOfficeEntity convertViewToEntity(BranchOfficeView branchOfficeView)throws BranchOfficeException{
		BranchOfficeEntity branchOfficeEntity = null;
		StateConverter stateConverter = null;
		try{
			branchOfficeEntity = new BranchOfficeEntity();			
			stateConverter = new StateConverter();
			branchOfficeEntity.setSucId(Integer.parseInt(branchOfficeView.getSucId()));
			branchOfficeEntity.setName(branchOfficeView.getName());
			branchOfficeEntity.setAddress(branchOfficeView.getAddress());
			branchOfficeEntity.setCity(branchOfficeView.getCity());
			branchOfficeEntity.setCommunity(branchOfficeView.getCommunity());
			branchOfficeEntity.setStateEntity(stateConverter.convertViewToEntity(branchOfficeView.getState()));
			branchOfficeEntity.setZip(branchOfficeView.getZip());
		}catch (Exception exception){
			BranchOfficeException branchOfficeException = null;
			branchOfficeException = new BranchOfficeException(exception, BranchOfficeException.LAYER_CONVERTER, BranchOfficeException.ACTION_SELECT);
			log.error(branchOfficeException);
			exception.printStackTrace(System.out);
			throw branchOfficeException;
		}
		return branchOfficeEntity;
	}
	

	public List<SelectItem> converterViewsToItems(List<BranchOfficeView> branchOfficeViews)throws BranchOfficeException{
		List<SelectItem> branchOfficeItems= null;
		BranchOfficeView branchOfficeView = null;
		SelectItem branchOfficeItem = null;
		Iterator<BranchOfficeView> iteraror = null;
		try{
			branchOfficeItems = new ArrayList<SelectItem>(0);
			iteraror = branchOfficeViews.iterator();
			while(iteraror.hasNext()){
				branchOfficeView = iteraror.next();
				branchOfficeItem = new SelectItem();
				branchOfficeItem.setValue(branchOfficeView.getSucId());
				branchOfficeItem.setLabel(branchOfficeView.getName());
				branchOfficeItems.add(branchOfficeItem);
			}
		}catch (Exception exception){
			BranchOfficeException branchOfficeException = null;
			branchOfficeException = new BranchOfficeException(exception, BranchOfficeException.LAYER_CONVERTER, BranchOfficeException.ACTION_SELECT);
			log.error(branchOfficeException);
			exception.printStackTrace(System.out);
			throw branchOfficeException;
		}
		return branchOfficeItems;
	}
	
}
