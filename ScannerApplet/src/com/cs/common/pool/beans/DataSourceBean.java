package com.cs.common.pool.beans;

/**
 * Data for connection pools
 * @author jdelgado
 * @date 2008-06-05
 */

public class DataSourceBean {
	/** Connection pool name*/
	private String name;
	/** Driver to use for connection pool */
	private String driver;
	/** Database url */
	private String url;
	/** Database user name */
	private String username;
	/** User name password */
	private String pass;
	/** The maximum number of connections that can remain idle in the pool, without extra ones being released, or negative for no limit  */
	private String maxIdle;
	/** The maximum number of active connections that can be allocated from this pool at the same time, or non-positive for no limit  */
	private String maxActive;
	/** The maximum number of milliseconds that the pool will wait (when there are no available connections) for a connection to be returned before throwing an exception, or -1 to wait indefinitely */
	private String maxWait;
	/** The default auto-commit state of connections created by this pool */
	private String defaultAutoCommit;
	/** Prepared statement pooling for this pool */
	private String poolPreparedStatements;
	/** The maximum number of open statements that can be allocated from the statement pool at the same time, or non-positive for no limit  */
	private String maxOpenPreparedStatements;
	/** Default database */
	private String catalog;
	/**
	 * Gets Connection pool name
	 * @return <String> name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets Connection pool name
	 * @param nName new connection pool name
	 */
	public void setName(String nName) {
		this.name = nName;
	}
	/**
	 * Gets driver
	 * @return <String> driver
	 */
	public String getDriver() {
		return driver;
	}
	/**
	 * Sets driver
	 * @param nDriver new driver
	 */
	public void setDriver(String nDriver) {
		this.driver = nDriver;
	}
	/**
	 * Gets url
	 * @return <String> url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Sets url
	 * @param nUrl new url
	 */
	public void setUrl(String nUrl) {
		this.url = nUrl;
	}
	/**
	 * Gets database user name
	 * @return <String> database user name
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Sets database user name
	 * @param nUsername new user name
	 */
	public void setUsername(String nUsername) {
		this.username = nUsername;
	}
	/**
	 * Gets user name password
	 * @return <String> User name password
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * Sets user name password
	 * @param nPass
	 */
	public void setPass(String nPass) {
		this.pass = nPass;
	}
	/**
	 * Gets maximum number of connections  that can remain idle in the pool
	 * @return <String> maxIdle
	 */
	public String getMaxIdle() {
		return maxIdle;
	}
	/**
	 * Sets maximum number of connections  that can remain idle in the pool
	 * @param nMaxIdle
	 */
	public void setMaxIdle(String nMaxIdle) {
		this.maxIdle = nMaxIdle;
	}
	/**
	 * Gets maximum number of active connections
	 * @return <String> maxActive
	 */
	public String getMaxActive() {
		return maxActive;
	}
	/**
	 * Sets maximum number of active connections
	 * @param nMaxActive
	 */
	public void setMaxActive(String nMaxActive) {
		this.maxActive = nMaxActive;
	}
	/**
	 * Gets maximum number of milliseconds that the pool will wait (when there are no available connections)
	 * @return <String> maxWait
	 */
	public String getMaxWait() {
		return maxWait;
	}
	/**
	 * Sets maximum number of milliseconds that the pool will wait (when there are no available connections)
	 * @param nMaxWait
	 */
	public void setMaxWait(String nMaxWait) {
		this.maxWait = nMaxWait;
	}
	/**
	 * Gets default auto-commit
	 * @return <String> defaultAutoCommit
	 */
	public String getDefaultAutoCommit() {
		return defaultAutoCommit;
	}
	/**
	 * Sets default auto-commit
	 * @param nDefaultAutoCommit
	 */
	public void setDefaultAutoCommit(String nDefaultAutoCommit) {
		this.defaultAutoCommit = nDefaultAutoCommit;
	}
	/**
	 * Gets prepared statement pooling
	 * @return <String> poolPreparedStatements
	 */
	public String getPoolPreparedStatements() {
		return poolPreparedStatements;
	}
	/**
	 * Sets prepared statement pooling
	 * @param nPoolPreparedStatements
	 */
	public void setPoolPreparedStatements(String nPoolPreparedStatements) {
		this.poolPreparedStatements = nPoolPreparedStatements;
	}
	/**
	 * Gets maximum number of open statements
	 * @return <String> maxOpenPreparedStatements
	 */
	public String getMaxOpenPreparedStatements() {
		return maxOpenPreparedStatements;
	}
	/**
	 * Sets maximum number of open statements
	 * @param nMaxOpenPreparedStatements
	 */
	public void setMaxOpenPreparedStatements(String nMaxOpenPreparedStatements) {
		this.maxOpenPreparedStatements = nMaxOpenPreparedStatements;
	}
	/**
	 * Sets default database
	 * @param nCatalog
	 */
	public void setCatalog(String nCatalog) {
		this.catalog = nCatalog;
	}
	/**
	 * Gets default database
	 * @return <String> catalog
	 */
	public String getCatalog() {
		return catalog;
	}
}