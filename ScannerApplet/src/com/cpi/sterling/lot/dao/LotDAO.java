package com.cpi.sterling.lot.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cpi.sterling.lot.dto.LotDTO;
import com.cpi.sterling.lot.exception.LotException;
import com.cs.common.pool.DBDAO;
import com.cs.common.pool.exception.PoolException;
import com.cs.common.utils.NumberUtil;

public class LotDAO extends DBDAO {
	
	private static final String SQL_INSERT = "INSERT INTO LOTE (SUC_ID,DIV_ID,LOT_REFERENCIA,LOT_FECHA,LOT_DOCUMENTOS,LOT_IMPORTE,USU_LOGIN,LOT_FECHA_ALTA,LOT_TIPO) VALUES (?,?,?,?,?,?,?,getDate(),?)";
	
	public int insertLot(LotDTO lotDTO)throws LotException{
		int id = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = prepareSQL(SQL_INSERT, dsName);
			preparedStatement.setInt(1, lotDTO.getSucId());
			preparedStatement.setInt(2, lotDTO.getDivId());
			preparedStatement.setString(3, lotDTO.getReference());
			preparedStatement.setDate(4, new Date(lotDTO.getDate().getTimeInMillis()));
			preparedStatement.setInt(5, lotDTO.getNoDocs());
			preparedStatement.setDouble(6, lotDTO.getAmount());
			preparedStatement.setString(7, lotDTO.getUser());
			preparedStatement.setInt(8, lotDTO.getType());
			executeInsert(preparedStatement);
			connection = preparedStatement.getConnection();
			id = NumberUtil.parseInt(getInsertedId(connection));
			connection.close();
		}catch(PoolException poolException){
			LotException lotException = null;
			poolException.printStackTrace(System.out);
			lotException = new LotException(poolException, LotException.LAYER_DAO, LotException.ACTION_INSERT);
			throw lotException;
		}catch(SQLException sqlException){
			LotException lotException = null;
			sqlException.printStackTrace(System.out);
			lotException = new LotException(sqlException, LotException.LAYER_DAO, LotException.ACTION_INSERT);
			throw lotException;
		}
		return id;
	}
}