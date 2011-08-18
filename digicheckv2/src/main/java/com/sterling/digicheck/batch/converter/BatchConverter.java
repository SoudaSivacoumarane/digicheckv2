package com.sterling.digicheck.batch.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sterling.digicheck.bank.entity.BankEntity;
import com.sterling.digicheck.bank.view.BankView;
import com.sterling.digicheck.batch.entity.BatchEntity;
import com.sterling.digicheck.batch.exception.BatchException;
import com.sterling.digicheck.batch.view.BatchView;
import com.sterling.digicheck.branchoffice.entity.BranchOfficeEntity;
import com.sterling.digicheck.branchoffice.view.BranchOfficeView;
import com.sterling.digicheck.currency.entity.CurrencyEntity;
import com.sterling.digicheck.currency.view.CurrencyView;
import com.sterling.digicheck.state.entity.StateEntity;
import com.sterling.digicheck.state.view.StateView;
import com.sterling.digicheck.user.entity.UserEntity;
import com.sterling.digicheck.user.view.UserView;
/**
 * Batch Converter
 * @author Edgar Joao
 *
 */
public class BatchConverter {

	private static final Log logger = LogFactory.getLog(BatchConverter.class);
	/**
	 * 
	 * @param batchEntity
	 * @return
	 * @throws BatchException
	 */
	public BatchView convertViewToEntity(BatchEntity batchEntity) throws BatchException{
		BatchView batchView = null;				
		try{						
			batchView = new BatchView();
			batchView.setBatchId(batchEntity.getBatchId());
			batchView.setBatchAmount(batchEntity.getBatchAmount());
			batchView.setBatchDate(batchEntity.getBatchDate());
			batchView.setBatchDateAdded(batchEntity.getBatchDateAdded());
			batchView.setBatchDocuments(batchEntity.getBatchDocuments());
			batchView.setReference(batchEntity.getReference());			
			batchView.setBankView(new BankView(batchEntity.getBankId().getBankId().toString(), batchEntity.getBankId().getAba(), batchEntity.getBankId().getDescription()));
			batchView.setCurrencyView(new CurrencyView(batchEntity.getCurrencyId().getCurrencyId().toString(), batchEntity.getCurrencyId().getCode(), batchEntity.getCurrencyId().getName(), ""));						
			batchView.setUserView(new UserView(String.valueOf(batchEntity.getUserLogin().getSucursalId()), batchEntity.getUserLogin().getLogin(), batchEntity.getUserLogin().getNombre(), batchEntity.getUserLogin().getPassword()));			
			batchView.setBranchOfficeView(new BranchOfficeView(batchEntity.getBranchOfficeId().getSucId().toString(), batchEntity.getBranchOfficeId().getName(), batchEntity.getBranchOfficeId().getAddress(), batchEntity.getBranchOfficeId().getCommunity(), batchEntity.getBranchOfficeId().getZip(), 
							new StateView(batchEntity.getBranchOfficeId().getStateEntity().getCode(), batchEntity.getBranchOfficeId().getStateEntity().getName()), batchEntity.getBranchOfficeId().getCity()));								
		}catch (Exception exception){
			BatchException batchException = null;
			batchException = new BatchException(exception, BatchException.LAYER_CONVERTER, BatchException.ACTION_SELECT);
			logger.error(batchException);
			exception.printStackTrace(System.out);
			throw batchException;
		}		
		return batchView;
	}
	/**
	 * 
	 * @param batchView
	 * @return
	 * @throws BatchException
	 */
	public BatchEntity convertEntityToView(BatchView batchView) throws BatchException{
		BatchEntity batchEntity = null;	
		try{
			batchEntity = new BatchEntity();			
			batchEntity.setBatchId(batchView.getBatchId());
			batchEntity.setBatchAmount(batchView.getBatchAmount());
			batchEntity.setBatchDate(batchView.getBatchDate());
			batchEntity.setBatchDateAdded(batchView.getBatchDateAdded());
			batchEntity.setBatchDocuments(batchView.getBatchDocuments());
			batchEntity.setLotReference(batchView.getReference());			
			batchEntity.setBankId(new BankEntity(Integer.parseInt(batchView.getBankView().getBankId()), batchView.getBankView().getAba(), batchView.getBankView().getDescription()));			
			batchEntity.setCurrencyId(new CurrencyEntity(Integer.parseInt(batchView.getCurrencyView().getCurrencyId()), batchView.getCurrencyView().getCode(), batchView.getCurrencyView().getName()));
			batchEntity.setUserLogin(new UserEntity(batchView.getUserView().getLogin(), batchView.getUserView().getPassword(), batchView.getUserView().getName(), Integer.parseInt(batchView.getUserView().getSucursalId())));			
			batchEntity.setBranchOfficeId(new BranchOfficeEntity(Integer.parseInt(batchView.getBranchOfficeView().getSucId()), batchView.getBranchOfficeView().getName(), batchView.getBranchOfficeView().getAddress(), batchView.getBranchOfficeView().getCommunity(), batchView.getBranchOfficeView().getZip(), batchView.getBranchOfficeView().getCity(),
								new StateEntity(batchView.getBranchOfficeView().getState().getCode(), batchView.getBranchOfficeView().getState().getName())));						
		}catch (Exception exception){
			BatchException batchException = null;
			batchException = new BatchException(exception, BatchException.LAYER_CONVERTER, BatchException.ACTION_SELECT);
			logger.error(batchException);
			exception.printStackTrace(System.out);
			throw batchException;
		}			
		return batchEntity;
	}	
	/**
	 * 
	 * @param batchEntities
	 * @return
	 * @throws BatchException
	 */
	public List<BatchView> convertViewsToEntities(List<BatchEntity> batchEntities) throws BatchException{
		List<BatchView> batchViewList = null;
		BatchView batchView = null;
		try{
			batchViewList = new ArrayList<BatchView>(0);
			for(BatchEntity be : batchEntities){
				batchView = this.convertViewToEntity(be);
				batchViewList.add(batchView);
			}			
		}catch (Exception exception){
			BatchException batchException = null;
			batchException = new BatchException(exception, BatchException.LAYER_CONVERTER, BatchException.ACTION_SELECT);
			logger.error(batchException);
			exception.printStackTrace(System.out);
			throw batchException;
		}
		return batchViewList;
	}
	/**
	 * 
	 * @param batchViews
	 * @return
	 * @throws BatchException
	 */
	public List<BatchEntity> convertEntitiesToViews(List<BatchView> batchViews) throws BatchException{
		List<BatchEntity> batchEntityList = null;
		BatchEntity batchEntity = null;
		try{
			batchEntityList = new ArrayList<BatchEntity>(0);
			for(BatchView bv : batchViews){
				batchEntity = this.convertEntityToView(bv);
				batchEntityList.add(batchEntity);
			}			
		}catch (Exception exception){
			BatchException batchException = null;
			batchException = new BatchException(exception, BatchException.LAYER_CONVERTER, BatchException.ACTION_SELECT);
			logger.error(batchException);
			exception.printStackTrace(System.out);
			throw batchException;
		}
		return batchEntityList;
	}
}
