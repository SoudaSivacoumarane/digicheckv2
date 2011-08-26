package com.cs.common.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.commons.dbcp.BasicDataSource;

import com.cs.common.pool.beans.DataSourceBean;
import com.cs.common.pool.beans.DataSourceCollection;
import com.cs.common.pool.exception.PoolException;

/**
 * Connection pool
 * @author TCS Team
 * @date 2008-06-06
 */

public class ConnectionPool {
	/** Store connection pool */
	private Hashtable pool = null;
	/** Field for singleton class */
	private static ConnectionPool connectionPool;
	/**
	 * Default constructor for singleton class
	 */
	private ConnectionPool(){
		pool = new Hashtable(0);
		createPool();
	}
	/**
	 * Create pool with data store in DataSourceCollection
	 */
	private void createPool(){
		DataSourceCollection dataSourceCollection = null;
		Iterator iterator = null;
		DataSourceBean dataSourceBean = null;
		try{
			dataSourceCollection = DataSourceCollection.getInstance();
			iterator = dataSourceCollection.iterator();
			while( iterator.hasNext()){
				dataSourceBean = (DataSourceBean)iterator.next();
				pool.put(dataSourceBean.getName(), createDataSource(dataSourceBean) );
			}
		}catch( NumberFormatException numberFormatException ){
			numberFormatException.printStackTrace();
		}
	}
	/**
	 * Creates data source with data source bean
	 * @param dataSourceBean data of data source
	 * @return <BasicDataSource> Data source created
	 */
	private BasicDataSource createDataSource( DataSourceBean dataSourceBean )throws NumberFormatException{
		BasicDataSource basicDataSource = null;
		int int_maxActive = 0;
		int int_maxIdle = 0;
		int int_maxOpenPreparedStatements = 0;
		int int_maxWait = 0;
		boolean bol_poolingStatements = false;
		boolean bol_defaultAutoCommit = false;
		
		int_maxActive = Integer.parseInt(dataSourceBean.getMaxActive());
		int_maxIdle = Integer.parseInt(dataSourceBean.getMaxIdle());
		int_maxOpenPreparedStatements = Integer.parseInt(dataSourceBean.getMaxOpenPreparedStatements());
		int_maxWait = Integer.parseInt(dataSourceBean.getMaxOpenPreparedStatements());
		bol_poolingStatements = Boolean.valueOf(dataSourceBean.getPoolPreparedStatements()).booleanValue();
		bol_defaultAutoCommit = Boolean.valueOf(dataSourceBean.getDefaultAutoCommit()).booleanValue();
		
		basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(dataSourceBean.getDriver());
		basicDataSource.setUrl(dataSourceBean.getUrl());
		basicDataSource.setUsername(dataSourceBean.getUsername());
		basicDataSource.setPassword(dataSourceBean.getPass());
		basicDataSource.setMaxActive(int_maxActive);
		basicDataSource.setMaxIdle(int_maxIdle);
		basicDataSource.setMaxOpenPreparedStatements(int_maxOpenPreparedStatements);
		basicDataSource.setMaxWait(int_maxWait);
		basicDataSource.setPoolPreparedStatements(bol_poolingStatements);
		basicDataSource.setDefaultAutoCommit(bol_defaultAutoCommit);
		basicDataSource.setDefaultCatalog(dataSourceBean.getCatalog());
		
		return basicDataSource;
	}
	/**
	 * Gets instance of ConnectionPool
	 * @return <ConnectionPool> connectionPool
	 */
	public static synchronized ConnectionPool getInstance(){
		if( null == connectionPool ){
			connectionPool = new ConnectionPool();
		}
		return connectionPool;
	}
	/**
	 * Gets connection for a specific data source
	 * @param dsName Data source
	 * @return <Connection> connection
	 */
	public Connection getConnection( String dsName )throws PoolException{
		Connection connection = null;
		BasicDataSource basicDataSource = null;
		try{
			if( pool != null ){
				basicDataSource = (BasicDataSource)pool.get(dsName);
				if( basicDataSource != null ){
					connection = basicDataSource.getConnection();
				}
			}else{
				connectionPool = new ConnectionPool();
				connection = getConnection(dsName);
				System.out.println("No hay conecciones");
			}
		}catch( Exception exception ){
			PoolException poolException = null;
			poolException = new PoolException(exception);
			throw poolException;
		}
		return connection;
	}
	/**
	 * Close data sources
	 */
	public void finalize() {
		ArrayList datasource = null;
		Iterator iterator = null;
		BasicDataSource basicDataSource = null;
		
		datasource = new ArrayList(pool.values());
		iterator = datasource.iterator();
		while(iterator.hasNext()){
			basicDataSource = (BasicDataSource)iterator.next();
			try {
				basicDataSource.close();
			} catch (SQLException sQLException) {
				sQLException.printStackTrace();
			}
		}
		pool = null;
		System.out.println("Pool finalize");
    }
}