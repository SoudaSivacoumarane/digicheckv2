package com.cs.common.pool.beans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Collection for load DataSourceBean
 * @author jdelgado
 * @date 2008-06-05
 */

public class DataSourceCollection  extends ArrayList<DataSourceBean>{
	/** Necessary field for serialization*/
	private static final long serialVersionUID = -1886098823901152293L;
	/** Rules file for data source */
	private static final String DATASOURCES_RULES_FILE = "datasources-rules.xml";//$NON-NLS-1$
	/** Configuration file for data source */
	private static final String DATASOURCES_CONFIG_FILE = "datasources-config.xml";//$NON-NLS-1$
	/** Instance for singleton */
	private static DataSourceCollection dataSourceCollection = null;
	/**
	 * Default constructor for singleton class
	 */
	private DataSourceCollection(){
		loadDatasource();
	}
	/**
	 * Load data sources in array list
	 */
	private void loadDatasource(){
		StringBuffer buffer = null;
		BufferedReader reader = null;
		String line = null;
		InputSource source = null;
		Digester digester = null;
		InputStream input = null;
		try {
			buffer = new StringBuffer(0);
			//url = this.getClass().getResource(DATASOURCES_RULES_FILE);
			//url =  new  URL(DATASOURCES_RULES_FILE);
			input = DataSourceCollection.class.getResourceAsStream(DATASOURCES_RULES_FILE);
			//input = url.openStream();
			reader = new BufferedReader(new InputStreamReader(input));
			while ( null != (line = reader.readLine())) {
				buffer.append(line);
			}
			source = new InputSource(new StringReader(buffer.toString()));
			digester = DigesterLoader.createDigester(source);
			digester.push( this );
			//url = this.getClass().getResource(DATASOURCES_CONFIG_FILE);
			//url =  new  URL(DATASOURCES_CONFIG_FILE);
			input = DataSourceCollection.class.getResourceAsStream(DATASOURCES_CONFIG_FILE);
			//input = url.openStream();
			digester.parse( input );
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} catch(IOException iOException){
			iOException.printStackTrace();
		}catch(SAXException sAXException){
			sAXException.printStackTrace();
		}catch(Exception exception){
			exception.printStackTrace();
		}finally{
			if (reader != null) {
				try {
					reader.close();
				}catch (IOException iOException){
					iOException.printStackTrace();
				}
			}
		}
	}
	/**
	 * Gets instance of collection
	 * @return <DataSourceCollection> data sources
	 */
	public static synchronized DataSourceCollection getInstance(){
		if( null == dataSourceCollection ){
			dataSourceCollection = new DataSourceCollection();
		}
		return dataSourceCollection;
	}
	/**
	 * Add data source configurations to collection
	 * @param datasource data to add
	 */
	public void addDataSource( DataSourceBean datasource ){
		this.add(datasource);
	}
}