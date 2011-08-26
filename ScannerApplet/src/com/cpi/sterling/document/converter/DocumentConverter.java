package com.cpi.sterling.document.converter;

import com.cpi.sterling.document.dto.DocumentDTO;
import com.cpi.sterling.document.exception.DocumentException;
import com.cpi.sterling.document.view.DocumentView;

public class DocumentConverter {

	public DocumentDTO convertViewToDTO(DocumentView view) throws DocumentException{
		DocumentDTO dto = null;
		try{
			if(view != null){
				dto = new DocumentDTO();
				dto.setDocId(view.getDocId());
				dto.setCheckId(view.getCheckId());
				dto.setDocTypeId(view.getDocTypeId());
				dto.setNumber(view.getNumber());
				dto.setFile(view.getFile());
			}			
		} catch (Exception exception){
			DocumentException documentException = null;
			documentException = new DocumentException(exception, DocumentException.LAYER_CONVERTER, DocumentException.ACTION_SELECT);			
			exception.printStackTrace(System.out);
			throw documentException;
		}	
		return dto;
	}
		
}
