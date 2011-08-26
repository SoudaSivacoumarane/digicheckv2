package com.cpi.sterling.lot.service;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.stylesheets.DocumentStyle;

import com.cpi.sterling.check.converter.CheckConverter;
import com.cpi.sterling.check.dao.CheckDAO;
import com.cpi.sterling.check.dto.CheckDTO;
import com.cpi.sterling.check.view.CheckView;
import com.cpi.sterling.document.converter.DocumentConverter;
import com.cpi.sterling.document.dao.DocumentDAO;
import com.cpi.sterling.document.view.DocumentView;
import com.cpi.sterling.lot.converter.LotConverter;
import com.cpi.sterling.lot.dao.LotDAO;
import com.cpi.sterling.lot.exception.LotException;
import com.cpi.sterling.lot.view.LotView;

public class LotService {

	public void saveLote(LotView lotView) throws LotException{
		LotDAO lotDAO = null;
		DocumentDAO documentDAO = null;
		DocumentConverter documentConverter = null;
		List<CheckDTO> checkDTOList = null;
		CheckDAO checkDAO = null;
		CheckDTO checkDTO = null;
		CheckConverter checkConverter = null;
		LotConverter lotConverter = null;
		int loteId = 0;
		int checkId = 0;
		try{
			if(lotView != null){
				lotDAO = new LotDAO();
				lotConverter = new LotConverter();			
				loteId = lotDAO.insertLot(lotConverter.convertViewToDTO(lotView));
								
				checkDAO = new CheckDAO();
				checkConverter = new CheckConverter();
				documentConverter = new DocumentConverter();
				checkDTOList = new ArrayList<CheckDTO>(0);
				for(CheckView v : lotView.getChekViewList()){
					v.setLotId(loteId);
					for(DocumentView dv : v.getDocumentList()){
						
					}	
					checkDTO = checkConverter.convertViewToDTO(v);
					checkDTOList.add(checkDTO);
				}
				
				
				for(CheckDTO dto : checkDTOList){				
					checkId = checkDAO.insertCheck(dto);
				}								
								
			}			
		} catch (Exception exception){
			LotException lotException = null;
			lotException = new LotException(exception, LotException.LAYER_SERVICE, LotException.ACTION_INSERT);			
			exception.printStackTrace(System.out);
			throw lotException;
		}		
	}
	
}
