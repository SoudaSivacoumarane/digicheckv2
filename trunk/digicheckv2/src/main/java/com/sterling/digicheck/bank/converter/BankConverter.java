/**
 * 
 */
package com.sterling.digicheck.bank.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sterling.digicheck.bank.entity.BankEntity;
import com.sterling.digicheck.bank.exception.BankException;
import com.sterling.digicheck.bank.view.BankView;

/**
 *
 * TODO rioslore:Document this.
 * 
 * @author rioslore
 */
public class BankConverter {

	public List<BankView> converterEntitiesToViews(List<BankEntity> bankEntities)throws BankException{
		List<BankView> bankViews = null;
		BankView bankView = null;
		BankEntity bankEntity = null;
		Iterator<BankEntity> iteraror = null;
		try{
			bankViews = new ArrayList<BankView>(0);
			iteraror = bankEntities.iterator();
			while(iteraror.hasNext()){
				bankEntity = iteraror.next();
				bankView = converterEntityToAuthView(bankEntity);
				bankViews.add(bankView);
			}
		}catch (Exception exception){
			BankException bankException = null;
			bankException = new BankException(exception, BankException.LAYER_CONVERTER, BankException.ACTION_SELECT);
			//log.error(bankException);
			exception.printStackTrace(System.out);
			throw bankException;
		}
		return bankViews;
	}
	public BankView converterEntityToAuthView(BankEntity bankEntity)throws BankException{
		BankView bankView = null;

		try{
			bankView = new BankView();			
			bankView.setBankId(String.valueOf(bankEntity.getBankId()));			
			bankView.setCode(bankEntity.getCode());
			bankView.setName(bankEntity.getName());
			bankView.setAccount(bankEntity.getAccount());
		} catch (Exception exception){
			BankException bankException = null;
			bankException = new BankException(exception, BankException.LAYER_CONVERTER, BankException.ACTION_SELECT);
			//log.error(bankException);
			exception.printStackTrace(System.out);
			throw bankException;
		}
		return bankView;
	}
	public BankEntity convertViewToEntity(BankView view)throws BankException{
		BankEntity entity = null;		
		try{
			entity = new BankEntity();			
			entity.setCode(view.getCode());
			entity.setName(view.getName());
			entity.setAccount(view.getAccount());				
		}catch (Exception exception){
			BankException bankException = null;
			bankException = new BankException(exception, BankException.LAYER_CONVERTER, BankException.ACTION_SELECT);
			//log.error(bankException);
			exception.printStackTrace(System.out);
			throw bankException;
		}
		return entity;
	}
}
