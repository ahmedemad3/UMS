package bh.gov.iga.ums.utility.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.portable.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import bh.gov.iga.ums.exception.DatabaseException;
import bh.gov.iga.ums.utility.model.Role;
import bh.gov.iga.ums.utility.model.User;
import bh.gov.iga.ums.utility.util.DataBaseUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDAO.
 */
public class UserDAO extends UmsBaseDAO implements UmsQueryStore {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

	/**
	 * Test transaction.
	 *
	 * @param input            the input
	 * @throws DatabaseException the database exception
	 * @throws DataAccessException             the data access exception
	 */

	public void testTransaction(String input) throws DatabaseException {
		logger.debug("testTransaction ({})", input);
		HashMap<String, Object> params = new HashMap<String, Object>();
		String query = queryById(UmsQueryStore.UmsQueries.TEST_QUERY);
		List<Object> result = template.query(query, params,
				new RowMapper<Object>() {
					public Object mapRow(ResultSet rs, int index)
							throws SQLException {
						logger.debug("Found result!");
						return null;
					}
				});

		logger.info("********************************" + (result == null));
	}

	/**
	 * Gets the user by username.
	 *
	 * @param username the username
	 * @return the user by username
	 * @throws DatabaseException the database exception
	 */
	public User getUserByUsername(String username) throws DatabaseException {
		Map<String, Object> params = fillParams(
				DataBaseUtil.paramList(username), new String[] { "USER_NAME" });
		return getObject(UmsQueryStore.UmsQueries.GET_USER_BY_USERNAME_QUERY,
				params, User.class);
	}

	/**
	 * Checks if is user exist.
	 *
	 * @param username the username
	 * @return true, if is user exist
	 */
	public boolean isUserExist(String username) {
		logger.info("isUsernameExists({}) : ", username);
		Map<String, Object> params = fillParams(
				DataBaseUtil.paramList(username), new String[] { "USER_NAME" });
		return isExist(UmsQueryStore.UmsQueries.IS_USERNAME_EXISTS_QUERY,
				params, true);
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<User> getUsers() {
		logger.info("getUsers()");
		return getList(UmsQueryStore.UmsQueries.GET_USER_QUERY, User.class);
	}

	/**
	 * Gets the user by email.
	 *
	 * @param email the email
	 * @return the user by email
	 */
	public User getUserByEmail(String email) {
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(email),
				new String[] { "EMAIL" });
		return getObject(UmsQueryStore.UmsQueries.GET_USER_BY_EMAIL_QUERY,
				params, User.class);
	}

	/**
	 * Change password.
	 *
	 * @param username the username
	 * @param newPasswordHashed the new password hashed
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void changePassword(String username, String newPasswordHashed,
			String updatedBy, Date updatedOn) {
		logger.info("changePassword()");
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				username, newPasswordHashed, updatedBy,
				new Timestamp(updatedOn.getTime())), new String[] {
				"USER_NAME", "PASSWORD", "UPDATED_BY", "UPDATED_ON" });

		executeQuery(UmsQueryStore.UmsQueries.UPDATE_PASSWORD_QUERY, params);
	}

	/**
	 * Checks if is user active.
	 *
	 * @param username the username
	 * @return true, if is user active
	 */
	public boolean isUserActive(String username) {
		logger.info("isUserActive({}) : ", username);
		Map<String, Object> params = fillParams(
				DataBaseUtil.paramList(username), new String[] { "USER_NAME" });
		return isActive(UmsQueryStore.UmsQueries.IS_USERNAME_ACTIVE_QUERY,
				params, true);
	}

	/**
	 * Disable user.
	 *
	 * @param username the username
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void disableUser(String username, String updatedBy, Date updatedOn) {
		logger.info("disableUser()");
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				username, updatedBy, new Timestamp(updatedOn.getTime())),
				new String[] { "USER_NAME", "UPDATED_BY", "UPDATED_ON" });

		executeQuery(UmsQueryStore.UmsQueries.DISABLE_USER_QUERY, params);

	}

	/**
	 * Enable user.
	 *
	 * @param username the username
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void enableUser(String username, String updatedBy, Date updatedOn) {
		logger.info("enableUser()");
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				username, updatedBy, new Timestamp(updatedOn.getTime())),
				new String[] { "USER_NAME", "UPDATED_BY", "UPDATED_ON" });

		executeQuery(UmsQueryStore.UmsQueries.ENABLE_USER_QUERY, params);

	}

	/**
	 * Gets the user roles.
	 *
	 * @param username the username
	 * @return the user roles
	 */
	public List<Role> getUserRoles(String username) {
		Map<String, Object> params = fillParams(
				DataBaseUtil.paramList(username), new String[] { "USER_NAME" });
		return getList(UmsQueryStore.UmsQueries.GET_USER_ROLES_QUERY,
				Role.class, params, 1000);
	}

	/**
	 * Reset password.
	 *
	 * @param username the username
	 * @param newPasswordHashed the new password hashed
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void resetPassword(String username, String newPasswordHashed,
			String updatedBy, Date updatedOn) {

		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				username, newPasswordHashed, updatedBy,
				new Timestamp(updatedOn.getTime())), new String[] {
				"USER_NAME", "PASSWORD", "UPDATED_BY", "UPDATED_ON" });

		executeQuery(UmsQueryStore.UmsQueries.RESET_USER_PASSWORD_QUERY, params);
	}

	/**
	 * Save.
	 *
	 * @param user the user
	 * @param createdBy the created by
	 * @param createdOn the created on
	 * @return the integer
	 */
	public Integer save(User user, String createdBy, Date createdOn) {
		logger.info("saveNewUser({}) , {} , {}" , user.toString() , createdBy , createdOn);
		SqlParameterSource params = fillSqlParams(DataBaseUtil.paramList(
				user.getUsername(), user.getEmail(), user.getPasswordHashed(), createdBy,
				new Timestamp(createdOn.getTime())), new String[] {
				"USER_NAME", "EMAIL", "PASSWORD", "CREATED_BY",
				"CREATED_ON" });

		Integer id = executeQueryReturnInt(
				UmsQueryStore.UmsQueries.SAVE_NEW_USER_QUERY, params);
		return id;
	}

	/**
	 * Update.
	 *
	 * @param user the user
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void update(User user, String updatedBy, Date updatedOn) {
		
		logger.info("updateUser()");
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				user.getUsername(),
				user.getEmail(),
				user.isActive() ? 1 : 0 ,
				updatedBy
				,new Timestamp(updatedOn.getTime())) ,
												new String[] {"USER_NAME","EMAIL","ACTIVE","UPDATED_BY","UPDATED_ON"});
		
		executeQuery(UmsQueryStore.UmsQueries.UPDATE_USER_QUERY, params);
	}

	/**
	 * Delete.
	 *
	 * @param userId the user id
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void delete(Integer userId, String updatedBy, Date updatedOn) {
		logger.info("deleteUser()");
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				userId,
				updatedBy
				,new Timestamp(updatedOn.getTime())) ,
												new String[] {"USER_ID","UPDATED_BY","UPDATED_ON"});
		executeQuery(UmsQueryStore.UmsQueries.DELETE_USER_QUERY, params);
	}
	
	/**
	 * Checks if is user email exist.
	 *
	 * @param email the email
	 * @return true, if is user email exist
	 */
	public boolean isUserEmailExist(String email) {
		logger.info("isUserEmailExist({})", email);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("EMAIL", email);
		return isExist(UmsQueryStore.UmsQueries.IS_USER_EMAIL_EXIST_QUERY, params, true);
	}

}
