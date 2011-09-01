package com.cpi.sterling.lot.service;

import com.cpi.sterling.check.converter.CheckConverter;
import com.cpi.sterling.check.dao.CheckDAO;
import com.cpi.sterling.check.dto.CheckDTO;
import com.cpi.sterling.check.view.CheckView;
import com.cpi.sterling.document.converter.DocumentConverter;
import com.cpi.sterling.document.dao.DocumentDAO;
import com.cpi.sterling.document.dto.DocumentDTO;
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
		CheckDAO checkDAO = null;
		CheckDTO checkDTO = null;
		DocumentDTO documentDTO = null;
		CheckConverter checkConverter = null;
		LotConverter lotConverter = null;
		int loteId = 0;
		int checkId = 0;
		try{
			lotDAO = new LotDAO();
			checkDAO = new CheckDAO();
			documentDAO = new DocumentDAO();
			
			lotConverter = new LotConverter();
			checkConverter = new CheckConverter();
			documentConverter = new DocumentConverter();
			
			loteId = lotDAO.insertLot(lotConverter.convertViewToDTO(lotView));
			for(CheckView checkView : lotView.getChekViewList()){
				checkView.setLotId(loteId);
				checkDTO = checkConverter.convertViewToDTO(checkView);
				checkId = checkDAO.insertCheck(checkDTO);
				for(DocumentView documentView : checkView.getDocumentList()){
					documentView.setCheckId(checkId);
					documentDTO = documentConverter.convertViewToDTO(documentView);
					documentDAO.insertDocument(documentDTO);
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
