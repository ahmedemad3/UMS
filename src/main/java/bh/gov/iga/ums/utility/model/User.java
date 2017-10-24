package bh.gov.iga.ums.utility.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User extends UmsBaseTo implements FillFromResultSet {

	/** The id. */
	private Integer id;
	
	/** The username. */
	private String username;
	
	/** The email. */
	private String email;
	
	/** The deleted. */
	private short deleted;
	
	/** The password hashed. */
	private String passwordHashed;
	
	/** The active. */
	private boolean active;
	
	/** The password. */
	private String password;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the deleted.
	 *
	 * @return the deleted
	 */
	public short getDeleted() {
		return deleted;
	}

	/**
	 * Sets the deleted.
	 *
	 * @param deleted the new deleted
	 */
	public void setDeleted(short deleted) {
		this.deleted = deleted;
	}

	/**
	 * Gets the password hashed.
	 *
	 * @return the password hashed
	 */
	public String getPasswordHashed() {
		return passwordHashed;
	}

	/**
	 * Sets the password hashed.
	 *
	 * @param passwordHashed the new password hashed
	 */
	public void setPasswordHashed(String passwordHashed) {
		this.passwordHashed = passwordHashed;
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see bh.gov.iga.ums.utility.model.FillFromResultSet#fillFromResultSet(java.sql.ResultSet)
	 */
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {

		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("USER_ID")) {
				this.setId(resultSet.getInt("USER_ID"));
			} else if (columnLabel.equalsIgnoreCase("USER_NAME")) {
				this.setUsername(resultSet.getString("USER_NAME"));
			} else if (columnLabel.equalsIgnoreCase("EMAIL")) {
				this.setEmail(resultSet.getString("EMAIL"));
			} else if (columnLabel.equalsIgnoreCase("DELETED")) {
				this.setDeleted(resultSet.getShort("DELETED"));
			} else if (columnLabel.equalsIgnoreCase("ACTIVE")) {
				short active = resultSet.getShort("ACTIVE");
				this.setActive(active == 0 ? false : true);
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email
				+ ", deleted=" + deleted + "]";
	}

}
