package com.cs.common.pool;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

import com.cs.common.pool.exception.PoolException;

/**
 * Base DAO to access to database
 * @date 2008-06-06
 */

public class DBDAO {
	
	/** Error Message when the record cant be inserted */
	protected static final String MSG_NO_INSERT= "error.general.noinsert";//$NON-NLS-1$	
	/** Error Message when the update fails*/
	protected static final String MSG_NO_UPDATE = "error.general.noupdated";//$NON-NLS-1$
	/** Error Message when the deletion fails*/ 
	protected static final String MSG_NO_DELETE = "error.general.nodeleted";//$NON-NLS-1$
	/** Query para el ultimo id insertado en la tabla xxx*/
	protected static String LAST_INSERTED_FOR_TABLE = "SELECT IDENT_CURRENT(''{0}'')";
	protected static final String FOREIGN_KEY_ERROR_CODE = "FOREIGN_KEY_ERROR_CODE";
	protected static final String FOREIGN_KEY_ERROR_KEY = "error.general.foreign.key";
	/**
	 * Nombre del <code>data source</code> dónde se van a realizar las conexiónes
	 */
	protected static final String dsName 	= "cornpp";//$NON-NLS-1$
	/**
	 * Instrucción <code>SQL</code> de inicio para extraer sólo aquellos registros que cumplan con cierta condición
	 */
	protected static final String SQL_WHERE = " WHERE ";//$NON-NLS-1$
	/**
	 * Instrucción <code>SQL</code> que denota el comienzo de la asignación de valores para los campos de una tabla durante una  consulta <code>SQL</code> del tipo <code>UPDATE</code>
	 */
	protected static final String SQL_SET = " SET ";	//$NON-NLS-1$
	/**
	 * Instrucción <code>SQL</code> de ordenamiento
	 */
	protected static final String SQL_ORDER_BY = " ORDER BY ";//$NON-NLS-1$
	protected static final String SQL_ASCENDING_ORDER = " ASC ";
	protected static final String SQL_DESCENDING_ORDER = " DESC ";
	protected static final String SQL_COMMA = " , ";
	/**
	 * Operador condicional <code>SQL</code> incluyente 
	 */
	protected static final String SQL_AND = " AND ";//$NON-NLS-1$
	/**
	 * Operador condicional <code>SQL_BETWEEN</code> incluyente 
	 */
	protected static final String SQL_BETWEEN = " BETWEEN ";//$NON-NLS-1$
	/**
	 * Operador condicional <code>SQL_LIKE</code> incluyente 
	 */
	protected static final String SQL_LIKE = " LIKE ";//$NON-NLS-1$
	/**
	 * Token utilizado para indicar un parámetro en la consulta '?'
	 */
	protected static final String PREPARED_STATEMENT_PARAMETER_TOKEN = "?";//$NON-NLS-1$
	/**
	 * Token utilizado para indicar la asignación de un parámetro en la consulta ' = ? '
	 */
	protected static final String PREPARED_STATEMENT_PARAMETER_TOKEN_ASSIGNMENT = "=?";//$NON-NLS-1$
	
	/**
	 * Gets connection for a specific data source
	 * @param dsName Data source
	 * @return <Connection> connection
	 */
    public Connection getConnection(String dsName) throws PoolException {
        Connection conn = null;
        ConnectionPool connectionPool = null;
        try{
	        connectionPool = ConnectionPool.getInstance();
	        if (connectionPool != null ) {
	            conn = connectionPool.getConnection(dsName);
	        }
        }catch( PoolException poolException ){
        	throw poolException;
        }
        return conn;
    }
    /**
     * Closes connection
     * @param conn connection to close
     * @throws SQLException exception from database
     */
    public void closeConnection(Connection conn) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
        conn = null;
    }
    /**
     * Close connection and prepareStatement
     * @param ps prepareStatement to close
     * @param conn connection to close
     * @throws SQLException exception from database
     */
    public void closeConnection(PreparedStatement ps, Connection conn) throws SQLException {
        if (ps != null) {
            ps.close();
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
        ps = null;
        conn = null;
    }
    /**
     * Close connection and prepareStatement and resultSet
     * @param rs resultSet to close
     * @param ps prepareStatement to close
     * @param conn connection to close
     * @throws SQLException exception from database
     */
    public void closeConnection(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
        rs = null;
        ps = null;
        conn = null;
    }
    /**
     * Creates a CallableStatement
     * @param sql query to execute with this callableStatement
     * @param dsName data source name for the statement
     * @return <CallableStatement> callableStatement created
     * @throws SQLException exception from database
     */
    public CallableStatement prepareCALL(String sql, String dsName) throws PoolException {
        CallableStatement cstmt = null;
        ConnectionPool connectionPool = null;
        try{
	        connectionPool = ConnectionPool.getInstance();
	        if (connectionPool != null) {
	            cstmt = getConnection(dsName).prepareCall(sql);
	        }
        }catch(SQLException sqlException){
        	PoolException poolException = null;
			poolException = new PoolException(sqlException);
			throw poolException;
        }
        return cstmt;
    }
   /**
    * Creates a PreparedStatement
    * @param sql query to execute with this preparedStatement
    * @param dsName data source name for the statement
    * @return<PreparedStatement> preparedStatement created
    * @throws SQLException exception from database
    */
    public PreparedStatement prepareSQL(String sql, String dsName) throws PoolException {
        PreparedStatement pstmt = null;
        Connection connection = null;
        try{
        	connection = getConnection(dsName);
            pstmt = connection.prepareStatement(sql);
        }catch (SQLException sqlException) {
        	PoolException poolException = null;
			poolException = new PoolException(sqlException);
			throw poolException;
        }
        return pstmt;
    }
   /**
    * Execute preparedStatement
    * @param preparedStatement to execute 
    * @throws SQLException exception from database
    */
    public int executeUpdate(PreparedStatement preparedStatement) throws SQLException {
        try {
        	return preparedStatement.executeUpdate();
        }catch (SQLException sqlException) {
        	throw sqlException;
        }
    }
    /**
     * Execute callableStatement
     * @param callableStatement to execute
     * @return <ResultSet> resultSet with result of the query
     * @throws SQLException exception from database
     */
    public ResultSet executeQuery(CallableStatement callableStatement) throws SQLException {
        try {
            return callableStatement.executeQuery();
        }
        catch (SQLException sqle) {
            throw sqle;
        }
    }
    /**
     * Execute preparedStatement
     * @param preparedStatement to execute
     * @return <ResultSet> resultSet with result of the query
     * @throws SQLException exception from database
     */
    public ResultSet executeQuery(PreparedStatement preparedStatement) throws SQLException {
        try {
            return preparedStatement.executeQuery();
        }
        catch (SQLException sqle) {
            throw sqle;
        }
    }
    
    
     public int executeInsert(PreparedStatement preparedStatement) throws SQLException {
        try {
                return preparedStatement.executeUpdate();
            }
        catch (SQLException sqle) {
            throw sqle;
        }
    }
     
     public String getLastInsertIdForTable(String table){    	 
    	 if(table==null)
    		 return "";
    	 String query = null;
    	 Object[] params = {table};
    	 query = MessageFormat.format(LAST_INSERTED_FOR_TABLE,params);
    	 return query;
    	 
     }
     
     protected String getInsertedId(Connection con) throws SQLException, PoolException{
    	 String insertedRowId = null;
    	 PreparedStatement pstm = null;
    	 ResultSet rs = null;
    	 
    	 pstm = con.prepareStatement("SELECT @@IDENTITY AS rowId");
    	 
    	 rs = executeQuery(pstm);
    	 
    	 if (rs.next()){
    		insertedRowId = rs.getString("rowId"); 
    	 } 
    	 
    	 return insertedRowId;
     }
}