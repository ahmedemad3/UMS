package bh.gov.iga.ums.utility.management;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bh.gov.iga.ums.utility.dataaccess.GroupDAO;
import bh.gov.iga.ums.utility.model.Group;
import bh.gov.iga.ums.utility.model.Role;

// TODO: Auto-generated Javadoc
/**
 * The Class GroupService.
 */
public class GroupService {

	/** The Constant logger. */
	public static final Logger logger = LoggerFactory
			.getLogger(GroupService.class);

	/** The group DAO. */
	private GroupDAO groupDAO;

	/**
	 * Gets the group DAO.
	 *
	 * @return the group DAO
	 */
	public GroupDAO getGroupDAO() {
		return groupDAO;
	}

	/**
	 * Sets the group DAO.
	 *
	 * @param groupDAO the new group DAO
	 */
	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
	
	
	/**
	 * Gets the user groups.
	 *
	 * @param userId the user id
	 * @return the user groups
	 */
	public List<Group> getUserGroups(Integer userId) {
		return groupDAO.getUserGroups(userId);
	}

	/**
	 * Save.
	 *
	 * @param group the group
	 * @param createdBy the created by
	 * @param createdOn the created on
	 * @return the integer
	 */
	public Integer save(Group group, String createdBy, Date createdOn) {
		return groupDAO.save(group, createdBy, createdOn);
	}

	/**
	 * Update.
	 *
	 * @param group the group
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void update(Group group, String updatedBy, Date updatedOn) {
		groupDAO.update(group, updatedBy, updatedOn);
	}

	/**
	 * Delete.
	 *
	 * @param groupId the group id
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void delete(Integer groupId, String updatedBy, Date updatedOn) {
		groupDAO.delete(groupId, updatedBy, updatedOn);
	}
	
	/**
	 * Adds the group to user.
	 *
	 * @param userId the user id
	 * @param groupId the group id
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void addGroupToUser(Integer userId , Integer groupId , String updatedBy, Date updatedOn){
		groupDAO.addGroupToUser(userId , groupId , updatedBy , updatedOn);
	}
	
	/**
	 * Adds the groups to user.
	 *
	 * @param userId the user id
	 * @param groupIds the group ids
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void addGroupsToUser(Integer userId , List<Integer> groupIds , String updatedBy, Date updatedOn){
		groupDAO.addGroupsToUser(userId , groupIds , updatedBy , updatedOn);
	}
	
	/**
	 * Gets the groups.
	 *
	 * @return the groups
	 */
	public List<Group> getGroups(){
		return groupDAO.getGroups();
	}
	
	/**
	 * Gets the group roles.
	 *
	 * @param groupId the group id
	 * @return the group roles
	 */
	public List<Role> getGroupRoles(Integer groupId){
		return groupDAO.getGroupRoles(groupId);
	}
	
	/**
	 * Enable.
	 *
	 * @param groupId the group id
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void enable(Integer groupId, String updatedBy, Date updatedOn) {
		groupDAO.enable(groupId, updatedBy, updatedOn);
	}
	
	
}
