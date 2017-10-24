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
package bh.gov.iga.ums.utility.model;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseTo.
 */
public class UmsBaseTo {
	
	/** The created on. */
	private Date					createdOn;
	
	/** The created by. */
	private String					createdBy;
	
	/** The updated on. */
	private Date					updatedOn;
	
	/** The updated by. */
	private String					updatedBy;
	
	/**
	 * Gets the created on.
	 *
	 * @return the created on
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * Sets the created on.
	 *
	 * @param createdOn the new created on
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the updated on.
	 *
	 * @return the updated on
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * Sets the updated on.
	 *
	 * @param updatedOn the new updated on
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * Gets the updated by.
	 *
	 * @return the updated by
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
}
