package com.cpi.sterling.check.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cpi.sterling.check.dto.CheckDTO;
import com.cpi.sterling.check.exception.CheckException;
import com.cs.common.pool.DBDAO;
import com.cs.common.pool.exception.PoolException;

public class CheckDAO extends DBDAO {
	
	private static final String SQL_INSERT = "INSERT INTO CHEQUE (LOT_ID, CHQ_ABBA, CHQ_CUENTA,CHQ_IMPORTE) values (?,?,?,?)";
	
	public int insertCheck(CheckDTO dto) throws CheckException{
		int id = 0;
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = prepareSQL(SQL_INSERT, dsName);
			preparedStatement.setInt(1, dto.getLotId());
			preparedStatement.setString(2, dto.getAbba());
			preparedStatement.setString(3, dto.getAccount());
			preparedStatement.setDouble(4, dto.getAmount());
			id = executeInsert(preparedStatement);
		}catch(PoolException poolException){
			CheckException checkException = null;
			poolException.printStackTrace(System.out);
			checkException = new CheckException(poolException, CheckException.LAYER_DAO, CheckException.ACTION_INSERT);
			throw checkException;
		}catch(SQLException sqlException){
			CheckException checkException = null;
			sqlException.printStackTrace(System.out);
			checkException = new CheckException(sqlException, CheckException.LAYER_DAO, CheckException.ACTION_INSERT);
			throw checkException;
		}
		return id;
	}

}
