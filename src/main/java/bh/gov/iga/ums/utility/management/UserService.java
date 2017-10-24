package bh.gov.iga.ums.utility.management;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bh.gov.iga.ums.utility.dataaccess.UserDAO;
import bh.gov.iga.ums.utility.model.Role;
import bh.gov.iga.ums.utility.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 */
public class UserService {

	/** The Constant logger. */
	public static final Logger logger = LoggerFactory
			.getLogger(UserService.class);

	/** The user DAO. */
	private UserDAO userDAO;

	/**
	 * Gets the user DAO.
	 *
	 * @return the user DAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * Sets the user DAO.
	 *
	 * @param userDAO the new user DAO
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * Gets the user by username.
	 *
	 * @param username the username
	 * @return the user by username
	 */
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}

	/**
	 * Gets the user by email.
	 *
	 * @param email the email
	 * @return the user by email
	 */
	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}

	/**
	 * Change password.
	 *
	 * @param username the username
	 * @param newPasswordHashed the new password hashed
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void changePassword(String username, String newPasswordHashed , String updatedBy , Date updatedOn) {
		userDAO.changePassword(username, newPasswordHashed , updatedBy , updatedOn);
	}

	/**
	 * Checks if is user exist.
	 *
	 * @param username the username
	 * @return true, if is user exist
	 */
	public boolean isUserExist(String username) {
		return userDAO.isUserExist(username);
	}

	/**
	 * Checks if is user active.
	 *
	 * @param username the username
	 * @return true, if is user active
	 */
	public boolean isUserActive(String username) {
		return userDAO.isUserActive(username);
	}

	/**
	 * Disable user.
	 *
	 * @param username the username
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void disableUser(String username , String updatedBy , Date updatedOn) {
		userDAO.disableUser(username , updatedBy , updatedOn);
	}

	/**
	 * Enable user.
	 *
	 * @param username the username
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void enableUser(String username , String updatedBy , Date updatedOn) {
		userDAO.enableUser(username , updatedBy , updatedOn);
	}

	/**
	 * Gets the user roles.
	 *
	 * @param username the username
	 * @return the user roles
	 */
	public List<Role> getUserRoles(String username) {
		return userDAO.getUserRoles(username);
	}

	/**
	 * Reset password.
	 *
	 * @param username the username
	 * @param newPasswordHashed the new password hashed
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void resetPassword(String username , String newPasswordHashed , String updatedBy , Date updatedOn) {
		userDAO.resetPassword(username , newPasswordHashed , updatedBy , updatedOn);
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	/**
	 * Save.
	 *
	 * @param user the user
	 * @param createdBy the created by
	 * @param createdOn the created on
	 * @return the integer
	 */
	public Integer save(User user , String createdBy , Date createdOn) {
		return userDAO.save(user , createdBy , createdOn);
	}

	/**
	 * Update.
	 *
	 * @param user the user
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void update(User user , String updatedBy , Date updatedOn) {
		userDAO.update(user , updatedBy , updatedOn);
	}

	/**
	 * Delete.
	 *
	 * @param userId the user id
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void delete(Integer userId , String updatedBy , Date updatedOn) {
		userDAO.delete(userId , updatedBy , updatedOn);
	}
	
	/**
	 * Checks if is user email exist.
	 *
	 * @param email the email
	 * @return true, if is user email exist
	 */
	public boolean isUserEmailExist(String email){
		return userDAO.isUserEmailExist(email);
	}

}
