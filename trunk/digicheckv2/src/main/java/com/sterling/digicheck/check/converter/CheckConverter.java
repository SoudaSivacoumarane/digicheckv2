package com.sterling.digicheck.check.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sterling.digicheck.batch.exception.BatchException;
import com.sterling.digicheck.check.entity.CheckEntity;
import com.sterling.digicheck.check.exception.CheckException;
import com.sterling.digicheck.check.view.CheckView;
import com.sterling.digicheck.document.entity.DocumentEntity;
import com.sterling.digicheck.document.view.DocumentView;
import com.sterling.digicheck.documenttype.view.DocumentTypeView;

public class CheckConverter {

	private static final Log logger = LogFactory.getLog(CheckConverter.class);
	
	public CheckView convertEntityToView(CheckEntity checkEntity) throws CheckException{
		CheckView checkView = null;
		try{
			if(checkEntity != null){
				checkView = new CheckView();
				checkView.setLotId(checkEntity.getBatch().getBatchId());
				checkView.setAbba(checkEntity.getChqAbba());
				checkView.setAccount(checkEntity.getChqAccount());				
				checkView.setDocumentList(this.convertDocsEntitiesToViews(checkEntity.getDocumentEntityCollection()));
			}			
		}catch (Exception exception){
			CheckException checkException = null;
			checkException = new CheckException(exception, CheckException.LAYER_CONVERTER, CheckException.ACTION_SELECT);
			logger.error(checkException);
			exception.printStackTrace(System.out);
			throw checkException;
		}
		return checkView;
	}

	public List<DocumentView> convertDocsEntitiesToViews(List<DocumentEntity> documentEntities)throws CheckException{
		List<DocumentView> list = null;
		DocumentView documentView = null;
		try{
			list = new ArrayList<DocumentView>(0);
			for(DocumentEntity d : documentEntities){
				documentView = new DocumentView();
				documentView.setDocId(d.getDocId());
				documentView.setDocNumber(d.getDocNumber());
				documentView.setDocumentTypeView(new DocumentTypeView(d.getDocumentType().getDocTypeId(), d.getDocumentType().getDocTypeDescription()));
				list.add(documentView);
			}			
		}catch (Exception exception){
			CheckException checkException = null;
			checkException = new CheckException(exception, BatchException.LAYER_CONVERTER, BatchException.ACTION_SELECT);
			logger.error(checkException);
			exception.printStackTrace(System.out);
			throw checkException;
		}
		return list;
	}
	
	public List<CheckView> convertEntitiesToViews(List<CheckEntity> checkEntities) throws CheckException{
		List<CheckView> list = null;
		CheckView checkView = null;
		try{
			if(checkEntities != null){				
				list = new ArrayList<CheckView>(0);
				for(CheckEntity c : checkEntities){
					checkView = this.convertEntityToView(c);
					list.add(checkView);
				}				
			}			
		}catch (Exception exception){
			CheckException checkException = null;
			checkException = new CheckException(exception, BatchException.LAYER_CONVERTER, BatchException.ACTION_SELECT);
			logger.error(checkException);
			exception.printStackTrace(System.out);
			throw checkException;
		}
		return list;
	}
	
}
