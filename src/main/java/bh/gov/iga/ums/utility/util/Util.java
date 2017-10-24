package bh.gov.iga.ums.utility.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class Util.
 */
public class Util {
	
	/** The Constant PROPERTIES_FILE_NAME. */
	public static final String  PROPERTIES_FILE_NAME="/db.properties";
	
	/**
	 * Load props file.
	 *
	 * @return the properties
	 */
	public static Properties loadPropsFile() {
 		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = Util.class.getResourceAsStream(PROPERTIES_FILE_NAME);
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Throwable the throwable
	 */
	public static void main(String[] args) throws Throwable {
		Util.loadPropsFile();
	}

}
