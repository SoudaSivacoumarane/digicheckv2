package com.sterling.digicheck.branchoffice.service;

import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterling.digicheck.branchoffice.converter.BranchOfficeConverter;
import com.sterling.digicheck.branchoffice.dao.BranchOfficeDAO;
import com.sterling.digicheck.branchoffice.entity.BranchOfficeEntity;
import com.sterling.digicheck.branchoffice.exception.BranchOfficeException;
import com.sterling.digicheck.branchoffice.view.BranchOfficeView;
import com.sterling.digicheck.state.converter.StateConverter;
import com.sterling.digicheck.state.dao.StateDAO;
import com.sterling.digicheck.state.exception.StateException;
import com.sterling.digicheck.state.view.StateView;

@Service("branchOfficeService")
public class BranchOfficeService {

	private static final Logger logger = Logger.getLogger(BranchOfficeService.class);
	
	@Autowired
	private BranchOfficeDAO branchOfficeDAO;
	@Autowired
	private StateDAO stateDAO;
	
	public List<BranchOfficeView> getAllBranchOffices()throws BranchOfficeException{
		List<BranchOfficeView> branchOfficeViews = null;
		List<BranchOfficeEntity> branchOfficeEntities = null;
		BranchOfficeConverter branchOfficeConverter = null;		
		try{
			branchOfficeConverter = new BranchOfficeConverter();			
			branchOfficeEntities = branchOfficeDAO.getAllBranchOffices();
			if(branchOfficeEntities != null){
				branchOfficeViews = branchOfficeConverter.converterEntitiesToViews(branchOfficeEntities);
			}
		}catch (BranchOfficeException branchOfficeException){
			throw branchOfficeException;
		}catch (Exception exception){
			BranchOfficeException branchOfficeException = null;
			branchOfficeException = new BranchOfficeException(exception, BranchOfficeException.LAYER_SERVICE, BranchOfficeException.ACTION_SELECT);
    		logger.error(branchOfficeException);
    		exception.printStackTrace(System.out);
    		throw branchOfficeException;
		}
		return branchOfficeViews;		
	}
	
	public void insertBranchOffice(BranchOfficeView view)throws BranchOfficeException{
		BranchOfficeEntity entity = null;
		BranchOfficeConverter branchOfficeConverter = null;
		try{
			branchOfficeConverter = new BranchOfficeConverter();
			entity = branchOfficeConverter.convertViewToEntity(view);
			branchOfficeDAO.insertBranchOffice(entity);
		}catch (BranchOfficeException branchOfficeException){
			throw branchOfficeException;
		}catch (Exception exception){
			BranchOfficeException branchOfficeException = null;
			branchOfficeException = new BranchOfficeException(exception, BranchOfficeException.LAYER_SERVICE, BranchOfficeException.ACTION_SELECT);
    		logger.error(branchOfficeException);
    		exception.printStackTrace(System.out);
    		throw branchOfficeException;
		}
	}
	
	public void updateBranchOffice(BranchOfficeView view)throws BranchOfficeException{
		BranchOfficeEntity entity = null;
		BranchOfficeConverter branchOfficeConverter = null;
		try{
			branchOfficeConverter = new BranchOfficeConverter();
			entity = branchOfficeConverter.convertViewToEntity(view);
			branchOfficeDAO.updateBranchOffice(entity);
		}catch (BranchOfficeException branchOfficeException){
			throw branchOfficeException;
		}catch (Exception exception){
			BranchOfficeException branchOfficeException = null;
			branchOfficeException = new BranchOfficeException(exception, BranchOfficeException.LAYER_SERVICE, BranchOfficeException.ACTION_SELECT);
    		logger.error(branchOfficeException);
    		exception.printStackTrace(System.out);
    		throw branchOfficeException;
		}
	}
	
	public void deleteBranchOffice(Integer sucId)throws BranchOfficeException{			
		try{				
			branchOfficeDAO.deleteBranchOffice(sucId);
		}catch (Exception exception){
			BranchOfficeException branchOfficeException = null;
			branchOfficeException = new BranchOfficeException(exception, BranchOfficeException.LAYER_SERVICE, BranchOfficeException.ACTION_DELETE);
    		logger.error(branchOfficeException);
    		exception.printStackTrace(System.out);
    		throw branchOfficeException;
		}
	}
	
	public BranchOfficeView validateBranchOffice(int branchOfficeId)throws BranchOfficeException{
		BranchOfficeView branchOfficeView = null;
		BranchOfficeEntity branchOfficeEntity = null;
		BranchOfficeConverter branchOfficeConverter = null;
		try{
			branchOfficeConverter = new BranchOfficeConverter();
			branchOfficeEntity = branchOfficeDAO.validateBranchOffice(branchOfficeId);
			if(branchOfficeEntity != null){
				branchOfficeView = branchOfficeConverter.converterEntityToAuthView(branchOfficeEntity);
			}
		} catch (BranchOfficeException branchOfficeException){
			throw branchOfficeException;
		} catch (Exception exception){
			BranchOfficeException branchOfficeException = null;
			branchOfficeException = new BranchOfficeException(exception, BranchOfficeException.LAYER_SERVICE, BranchOfficeException.ACTION_SELECT);
    		logger.error(branchOfficeException);
    		exception.printStackTrace(System.out);
    		throw branchOfficeException;
		}
		return branchOfficeView;
	}
	
	public List<SelectItem> getSelectStates(){
		StateConverter stateConverter = null;
		List<SelectItem> states = null;
		try {				
			stateConverter = new StateConverter();
			List<StateView> views = stateConverter.converterEntitiesToViews(stateDAO.getStates());
			states = stateConverter.converterViewsToItems(views);
		} catch (StateException e) {			
			e.printStackTrace();
		}	
		return states;
	}
}
