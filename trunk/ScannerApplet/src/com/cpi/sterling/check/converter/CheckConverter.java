package com.cpi.sterling.check.converter;

import com.cpi.sterling.check.dto.CheckDTO;
import com.cpi.sterling.check.exception.CheckException;
import com.cpi.sterling.check.view.CheckView;

public class CheckConverter {

	public CheckDTO convertViewToDTO(CheckView view) throws CheckException{
		CheckDTO dto = null;
		try{
			if(view != null){
				dto = new CheckDTO();
				dto.setChqId(view.getChqId());
				dto.setLotId(view.getLotId());
				dto.setAbba(view.getAbba());
				dto.setAccount(view.getAccount());
				dto.setAmount(view.getAmount());				
			}			
		} catch (Exception exception){
			CheckException checkException = null;
			checkException = new CheckException(exception, CheckException.LAYER_CONVERTER, CheckException.ACTION_SELECT);			
			exception.printStackTrace(System.out);
			throw checkException;
		}	
		return dto;
	}
}
