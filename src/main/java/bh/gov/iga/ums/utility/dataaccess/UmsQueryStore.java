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

package bh.gov.iga.ums.utility.dataaccess;


// TODO: Auto-generated Javadoc
/**
 * The Interface QueryStore.
 * 
 * @author Administrator
 */
public interface UmsQueryStore {

	/** The empty query id. */
	String EMPTY_QUERY_ID = "EMPTY_QUERY_ID";

	/** The query map not initialized. */
	String QUERY_MAP_NOT_INITIALIZED = "QUERY_MAP_NOT_INITIALIZED";

	/** The query not found. */
	String QUERY_NOT_FOUND = "QUERY_NOT_FOUND";

	/**
	 * The Interface UmsQueries.
	 */
	public interface UmsQueries {

		/** The test query. */
		String TEST_QUERY 												= "TEST_QUERY";
		
		/** The get user by username query. */
		String GET_USER_BY_USERNAME_QUERY 								= "GET_USER_BY_USERNAME_QUERY";
		
		/** The is username exists query. */
		String IS_USERNAME_EXISTS_QUERY 								= "IS_USERNAME_EXISTS_QUERY";
		
		/** The get user query. */
		String GET_USER_QUERY 											= "GET_USER_QUERY";
		
		/** The get user by email query. */
		String GET_USER_BY_EMAIL_QUERY 									= "GET_USER_BY_EMAIL_QUERY";
		
		/** The update password query. */
		String UPDATE_PASSWORD_QUERY 									= "UPDATE_PASSWORD_QUERY";
		
		/** The is username active query. */
		String IS_USERNAME_ACTIVE_QUERY 								= "IS_USERNAME_ACTIVE_QUERY";
		
		/** The disable user query. */
		String DISABLE_USER_QUERY 										= "DISABLE_USER_QUERY";
		
		/** The enable user query. */
		String ENABLE_USER_QUERY 										= "ENABLE_USER_QUERY";
		
		/** The get user roles query. */
		String GET_USER_ROLES_QUERY 									= "GET_USER_ROLES_QUERY";
		
		/** The reset user password query. */
		String RESET_USER_PASSWORD_QUERY 								= "RESET_USER_PASSWORD_QUERY";
		
		/** The save new user query. */
		String SAVE_NEW_USER_QUERY 										= "SAVE_NEW_USER_QUERY";
		
		/** The update user query. */
		String UPDATE_USER_QUERY 										= "UPDATE_USER_QUERY";
		
		/** The delete user query. */
		String DELETE_USER_QUERY 										= "DELETE_USER_QUERY";
		
		/** The get user groups query. */
		String GET_USER_GROUPS_QUERY 									= "GET_USER_GROUPS_QUERY";
		
		/** The save new group query. */
		String SAVE_NEW_GROUP_QUERY 									= "SAVE_NEW_GROUP_QUERY";
		
		/** The add group roles query. */
		String ADD_GROUP_ROLES_QUERY 									= "ADD_GROUP_ROLES_QUERY";
		
		/** The update group query. */
		String UPDATE_GROUP_QUERY 										= "UPDATE_GROUP_QUERY";
		
		/** The delete roles by group id query. */
		String DELETE_ROLES_BY_GROUP_ID_QUERY 							= "DELETE_ROLES_BY_GROUP_ID_QUERY";
		
		/** The delete group query. */
		String DELETE_GROUP_QUERY 										= "DELETE_GROUP_QUERY";
		
		/** The add groups to user query. */
		String ADD_GROUPS_TO_USER_QUERY 								= "ADD_GROUPS_TO_USER_QUERY";
		
		/** The get groups query. */
		String GET_GROUPS_QUERY 										= "GET_GROUPS_QUERY";
		
		/** The get group roles. */
		String GET_GROUP_ROLES 											= "GET_GROUP_ROLES";
		
		/** The is user email exist query. */
		String IS_USER_EMAIL_EXIST_QUERY 								= "IS_USER_EMAIL_EXIST_QUERY";
		
		/** The get group users. */
		String GET_GROUP_USERS 											= "GET_GROUP_USERS";
		
		/** The enable group query. */
		String ENABLE_GROUP_QUERY 										= "ENABLE_GROUP_QUERY";
	
		
	}

}
