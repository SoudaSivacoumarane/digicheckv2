package com.cpi.sterling.lot.converter;

import java.util.Calendar;

import com.cpi.sterling.lot.dto.LotDTO;
import com.cpi.sterling.lot.exception.LotException;
import com.cpi.sterling.lot.view.LotView;
import com.cs.common.utils.TimeUtils;

public class LotConverter {

	public LotDTO convertViewToDTO(LotView view) throws LotException{
		LotDTO dto = null;
		Calendar date = null;
		try{
			date = Calendar.getInstance();
			if(view != null){
				dto = new LotDTO();
				dto.setUser(view.getUser());
				dto.setSucId(view.getSucId());
				dto.setDivId(view.getDivId());
				dto.setNoDocs(view.getNoDocs());
				dto.setReference(view.getReference());
				dto.setAmount(view.getAmount());
				date.setTime(TimeUtils.convertStringToDate(view.getDate(), TimeUtils.DATE_PATTERN_COMPLETE));
				dto.setDate(date);
				dto.setType(view.getType());
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
