package com.cpi.sterling.document.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cpi.sterling.document.dto.DocumentDTO;
import com.cpi.sterling.document.exception.DocumentException;
import com.cs.common.pool.DBDAO;
import com.cs.common.pool.exception.PoolException;

public class DocumentDAO extends DBDAO {

	private static final String SQL_INSERT = "INSERT INTO DOCUMENTO (DOC_NUMERO, DOC_ARCHIVO, DOT_ID, CHQ_ID) values(?,?,?,?)";
	
	public void insertDocument(DocumentDTO dto) throws DocumentException{
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = prepareSQL(SQL_INSERT, dsName);
			preparedStatement.setInt(1, dto.getNumber());
			//preparedStatement.setb
			preparedStatement.setInt(3, dto.getDocTypeId());
			preparedStatement.setInt(4, dto.getCheckId());								
			executeInsert(preparedStatement);
		}catch(PoolException poolException){
			DocumentException documentException = null;
			poolException.printStackTrace(System.out);
			documentException = new DocumentException(poolException, DocumentException.LAYER_DAO, DocumentException.ACTION_INSERT);
			throw documentException;
		}catch(SQLException sqlException){
			DocumentException documentException = null;
			sqlException.printStackTrace(System.out);
			documentException = new DocumentException(sqlException, DocumentException.LAYER_DAO, DocumentException.ACTION_INSERT);
			throw documentException;
		}
	}
	
}
