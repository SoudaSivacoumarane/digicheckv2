package com.sterling.digicheck.document.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.sterling.common.dao.GenericDAO;
import com.sterling.digicheck.batch.exception.BatchException;
import com.sterling.digicheck.document.entity.DocumentEntity;

@Repository
public class DocumentDAO extends GenericDAO {

	public DocumentEntity getDocument(String docId) throws BatchException{
		DocumentEntity documentEntity = null;
		Query query = null;
		try{
			query = em.createNamedQuery("DocumentEntity.findByDocId");
			query.setParameter("docId", Integer.parseInt(docId));			
			documentEntity = (DocumentEntity) query.getSingleResult();			
		}catch (Exception exception){			
			BatchException bankException = null;
			bankException = new BatchException(exception, BatchException.LAYER_DAO, BatchException.ACTION_SELECT);    		
    		exception.printStackTrace(System.out);
    		throw bankException;
		}					
		return documentEntity;
	}
}
