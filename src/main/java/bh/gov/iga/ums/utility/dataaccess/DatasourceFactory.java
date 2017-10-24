package bh.gov.iga.ums.utility.dataaccess;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;
import javax.naming.spi.NamingManager;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bh.gov.iga.ums.utility.util.Util;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Datasource objects.
 */
public class DatasourceFactory {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(DatasourceFactory.class);
	
	/** The Constant DB_DRIVER. */
	public static final String DB_DRIVER = "db.driverclass";
	
	/** The Constant DB_URL. */
	public static final String DB_URL = "db.url";
	
	/** The Constant DB_USERNAME. */
	public static final String DB_USERNAME = "db.user";
	
	/** The Constant DB_PASSWORD. */
	public static final String DB_PASSWORD = "db.password";
	
	/** The Constant JAVA_LOOKUP. */
	public static final String JAVA_LOOKUP = "java:/comp/env";
	
	/** The Constant JNDI_NAME. */
	public static final String JNDI_NAME = "db.jndi-name";
	
	/** The Constant INITIAL_CONTEXT_FACTORY. */
	public static final String INITIAL_CONTEXT_FACTORY = "java.naming.factory.initial";
	

	/**
	 * Gets the data source.
	 *
	 * @return the data source
	 */
	public static DataSource getDataSource() {
		Properties props = Util.loadPropsFile();
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(props.getProperty(DB_DRIVER));
		dataSource.setUrl(props.getProperty(DB_URL));
		dataSource.setUsername(props.getProperty(DB_USERNAME));
		dataSource.setPassword(props.getProperty(DB_PASSWORD));
		return dataSource;
	}
	
	/**
	 * Gets the data source by JNDI.
	 *
	 * @return the data source by JNDI
	 */
	public static DataSource getDataSourceByJNDI(){
		setupInitialContext();
		Properties props = Util.loadPropsFile();
		InitialContext ctx;
		DataSource ds = null;
		try {
			ctx = new InitialContext();
			ds  = (DataSource) ctx.lookup(props.getProperty(JNDI_NAME));
		} catch (NamingException e) {
			logger.info("cannot get datasource by jndi , error : " + e.getMessage());
			e.printStackTrace();
		}
		return ds;
	}
	
	/**
	 * Setup initial context.
	 */
	private static void setupInitialContext() { 
	    try { 
	        NamingManager.setInitialContextFactoryBuilder(new InitialContextFactoryBuilder() { 
	 
	            public InitialContextFactory createInitialContextFactory(Hashtable<?, ?> environment) throws NamingException {
	                return new InitialContextFactory() { 
	 
	                    public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
	                        return new InitialContext(){ 
	 
	                            private Hashtable<String, DataSource> dataSources = new Hashtable<String, DataSource>();
	                            Properties props = Util.loadPropsFile();
	 
	                            @Override 
	                            public Object lookup(String name) throws NamingException {
	 
	                                if (dataSources.isEmpty()) { //init datasources
	                                	BasicDataSource dataSource = new BasicDataSource();
	                                	dataSource.setDriverClassName(props.getProperty(DB_DRIVER));
	                            		dataSource.setUrl(props.getProperty(DB_URL));
	                            		dataSource.setUsername(props.getProperty(DB_USERNAME));
	                            		dataSource.setPassword(props.getProperty(DB_PASSWORD));
	                                    dataSources.put(props.getProperty(JNDI_NAME), dataSource);
	 
	                                    //add more datasources to the list as necessary 
	                                } 
	 
	                                if (dataSources.containsKey(name)) {
	                                    return dataSources.get(name);
	                                } 
	 
	                                throw new NamingException("Unable to find datasource: "+name);
	                            } 
	                        }; 
	                    } 
	 
	                }; 
	            } 
	 
	        }); 
	    } 
	    catch (NamingException ne) {
	        ne.printStackTrace();
	    } 
	} 
}
