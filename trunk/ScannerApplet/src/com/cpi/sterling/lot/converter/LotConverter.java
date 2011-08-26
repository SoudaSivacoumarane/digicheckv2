package com.cpi.sterling.lot.converter;

import com.cpi.sterling.lot.dto.LotDTO;
import com.cpi.sterling.lot.exception.LotException;
import com.cpi.sterling.lot.view.LotView;

public class LotConverter {

	public LotDTO convertViewToDTO(LotView view) throws LotException{
		LotDTO dto = null;
		try{
			if(view != null){
				dto = new LotDTO();
				dto.setUser(view.getUser());
				dto.setSucId(view.getSucId());
				dto.setDivId(view.getDivId());
				dto.setNoDocs(view.getNoDocs());
				dto.setReference(view.getReference());
				dto.setAmount(view.getAmount());
				dto.setDate(view.getDate());
			}			
		} catch (Exception exception){
			LotException lotException = null;
			lotException = new LotException(exception, LotException.LAYER_CONVERTER, LotException.ACTION_SELECT);			
			exception.printStackTrace(System.out);
			throw lotException;
		}				
		return dto;
	}
	
}
