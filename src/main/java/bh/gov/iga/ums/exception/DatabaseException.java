/**
 * ******************************************************************
 * Copyrighted Material, Confidential, unauthorized review or 
 * reproduction is prohibited, ad so can and will result in 
 * legal action against violators 
 * ******************************************************************
 * ***  Copyright Holders *******************************************
 * ******************************************************************
 * Government of Kingdom Of Bahrain 						(KOB)	
 * Central Informatics Organization 						(CIO) 
 * Governerate Data Network 								(GDN) 
 * National Data Services Project							(NDS) 
 * The General Directorate of Information Technology 		(GDIT) 
 * Application Transformation Project 						(ATP)
 * Civil Registration Service 								(CRS)
 * ________________________________________________________________________________
 * Change Log 
 * ________________________________________________________________________________
 * VER		USER		Timestamp				Activity
 * ________________________________________________________________________________
 * 
 */
package bh.gov.iga.ums.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationException.
 */
public class DatabaseException extends RuntimeException {

	/** The code. */
	private int code;
	
	/** The error. */
	private String error;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/**
	 * Instantiates a new application exception.
	 */
	public DatabaseException() {
		super();
	}


	/**
	 * Instantiates a new application exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}


	/**
	 * Instantiates a new application exception.
	 *
	 * @param message the message
	 */
	public DatabaseException(String message) {
		super(message);
	}


	/**
	 * Instantiates a new application exception.
	 *
	 * @param cause the cause
	 */
	public DatabaseException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Instantiates a new database exception.
	 *
	 * @param code the code
	 * @param error the error
	 */
	public DatabaseException(int code, String error) {
		super(error);
		this.setCode(code);
		this.setError(error);
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(String error) {
		this.error = error;
	}


}
