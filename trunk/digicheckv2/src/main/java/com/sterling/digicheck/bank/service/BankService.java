package com.sterling.digicheck.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterling.digicheck.bank.converter.BankConverter;
import com.sterling.digicheck.bank.dao.BankDAO;
import com.sterling.digicheck.bank.entity.BankEntity;
import com.sterling.digicheck.bank.exception.BankException;
import com.sterling.digicheck.bank.view.BankView;

@Service("bankService")
public class BankService {
	
	@Autowired
	private BankDAO bankDAO;
	
	public List<BankView> getAllBanks() throws BankException{
		List<BankView> bankViews = null;
		List<BankEntity> bankEntities = null;
		BankConverter bankConverter = null;		
		try{
			bankConverter = new BankConverter();
			bankEntities = bankDAO.getAllBanks();
			if(bankEntities != null){
				bankViews = bankConverter.converterEntitiesToViews(bankEntities);
			}			
		}catch (BankException bankException){
			throw bankException;
		}catch (Exception exception){
			BankException bankException = null;
			bankException = new BankException(exception, BankException.LAYER_SERVICE, BankException.ACTION_LISTS);
    		//log.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}		
		return bankViews;
	}
	
	public void insertBank(BankView view)throws BankException{
		BankEntity entity = null;
		BankConverter bankConverter = null;
		try{
			bankConverter = new BankConverter();
			entity = bankConverter.convertViewToEntity(view);
			bankDAO.insertBank(entity);
		}catch (BankException bankException){
			throw bankException;
		}catch (Exception exception){
			BankException bankException = null;
			bankException = new BankException(exception, BankException.LAYER_SERVICE, BankException.ACTION_INSERT);
    		//log.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}
	}
	
	public void updateBank(BankView view)throws BankException{
		BankEntity entity = null;
		BankConverter bankConverter = null;
		try{
			bankConverter = new BankConverter();
			entity = bankConverter.convertViewToEntity(view);			
			bankDAO.updateBank(entity);
		}catch (BankException bankException){
			throw bankException;
		}catch (Exception exception){
			BankException bankException = null;
			bankException = new BankException(exception, BankException.LAYER_SERVICE, BankException.ACTION_UPDATE);
    		//log.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}
	}
	
	public void deleteBank(Integer bankId)throws BankException{		
		try{			
			bankDAO.deleteBank(bankId);						
		}catch (Exception exception){
			BankException bankException = null;
			bankException = new BankException(exception, BankException.LAYER_SERVICE, BankException.ACTION_DELETE);
    		//log.error(bankException);
    		exception.printStackTrace(System.out);
    		throw bankException;
		}
	}
}
