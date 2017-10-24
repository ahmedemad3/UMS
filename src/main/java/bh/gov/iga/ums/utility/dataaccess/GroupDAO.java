package bh.gov.iga.ums.utility.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import bh.gov.iga.ums.utility.model.Group;
import bh.gov.iga.ums.utility.model.Role;
import bh.gov.iga.ums.utility.model.User;
import bh.gov.iga.ums.utility.util.DataBaseUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class GroupDAO.
 */
public class GroupDAO extends UmsBaseDAO implements UmsQueryStore {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(GroupDAO.class);

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
	 * Gets the user groups.
	 *
	 * @param userId the user id
	 * @return the user groups
	 */
	public List<Group> getUserGroups(Integer userId) {
		Map<String, Object> params = fillParams(
				DataBaseUtil.paramList(userId), new String[] { "USER_ID" });
		return getList(UmsQueryStore.UmsQueries.GET_USER_GROUPS_QUERY,
				Group.class, params, 1000);
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
		logger.info("saveNewGroup({}) , {} , {}" , group.toString() , createdBy , createdOn);
		SqlParameterSource params = fillSqlParams(DataBaseUtil.paramList(
				group.getGroupName(), createdBy,
				new Timestamp(createdOn.getTime())), new String[] {
				"GROUP_NAME", "CREATED_BY", "CREATED_ON" });

		Integer groupId = executeQueryReturnInt(
				UmsQueryStore.UmsQueries.SAVE_NEW_GROUP_QUERY, params);
		if (group.getRoleIds() != null && group.getRoleIds().size() > 0)
			addGroupRoles(groupId.intValue(), group.getRoleIds());
		
		return groupId;
	}

	/**
	 * Adds the group roles.
	 *
	 * @param groupId the group id
	 * @param roleIds the role ids
	 */
	private void addGroupRoles(int groupId, List<Integer> roleIds) {
		
		logger.debug("addGroupRoles({})", groupId, roleIds);
		List<Object[]> parameters = new ArrayList<Object[]>();
		for (int i = 0; i < roleIds.size(); i++) {
			parameters.add(new Object[] { groupId, roleIds.get(i) });
		}
		executeBatchUpdate(UmsQueryStore.UmsQueries.ADD_GROUP_ROLES_QUERY, parameters);
		
	}

	/**
	 * Update.
	 *
	 * @param group the group
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void update(Group group, String updatedBy, Date updatedOn) {
		
		updateGroup(group, updatedBy, updatedOn);
		deleteRolesOfGroup(group.getGroupId());
		if (group.getRoleIds() != null && group.getRoleIds().size() > 0)
			addGroupRoles(group.getGroupId(), group.getRoleIds());
	}

	/**
	 * Delete roles of group.
	 *
	 * @param groupId the group id
	 */
	private void deleteRolesOfGroup(Integer groupId) {
		Map<String, Object> params = fillParams(
				DataBaseUtil.paramList(groupId),
				new String[] { "GROUP_ID" });
		executeQuery(UmsQueryStore.UmsQueries.DELETE_ROLES_BY_GROUP_ID_QUERY, params);
	}

	/**
	 * Update group.
	 *
	 * @param group the group
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	private void updateGroup(Group group, String updatedBy, Date updatedOn) {
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				group.getGroupName(), group.getGroupId(), updatedBy,
				new Timestamp(updatedOn.getTime())), new String[] {
				"GROUP_NAME", "GROUP_ID", "UPDATED_BY", "UPDATED_ON" });
		executeQuery(UmsQueryStore.UmsQueries.UPDATE_GROUP_QUERY, params);
	}

	/**
	 * Delete.
	 *
	 * @param groupId the group id
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void delete(Integer groupId, String updatedBy, Date updatedOn) {
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				groupId, updatedBy, new Timestamp(updatedOn.getTime())),
				new String[] { "GROUP_ID", "UPDATED_BY", "UPDATED_ON" });
		executeQuery(UmsQueryStore.UmsQueries.DELETE_GROUP_QUERY, params);
	}

	/**
	 * Adds the group to user.
	 *
	 * @param userId the user id
	 * @param groupId the group id
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void addGroupToUser(Integer userId, Integer groupId,
			String updatedBy, Date updatedOn) {
		
		logger.debug("addGroupToUser({})", userId, groupId);
		List<Object[]> parameters = new ArrayList<Object[]>();
		parameters.add(new Object[] { userId, groupId });
		executeBatchUpdate(UmsQueryStore.UmsQueries.ADD_GROUPS_TO_USER_QUERY, parameters);
		
	}

	/**
	 * Adds the groups to user.
	 *
	 * @param userId the user id
	 * @param groupIds the group ids
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void addGroupsToUser(Integer userId, List<Integer> groupIds,
			String updatedBy, Date updatedOn) {
		
		logger.debug("addGroupsToUser({})", userId, groupIds);
		List<Object[]> parameters = new ArrayList<Object[]>();
		for (int i = 0; i < groupIds.size(); i++) {
			parameters.add(new Object[] { userId, groupIds.get(i) });
		}
		executeBatchUpdate(UmsQueryStore.UmsQueries.ADD_GROUPS_TO_USER_QUERY, parameters);
	}

	/**
	 * Gets the groups.
	 *
	 * @return the groups
	 */
	public List<Group> getGroups() {
		logger.debug("getGroups()");
		return getList(UmsQueryStore.UmsQueries.GET_GROUPS_QUERY, Group.class, 1000);
	}

	/**
	 * Gets the group roles.
	 *
	 * @param groupId the group id
	 * @return the group roles
	 */
	public List<Role> getGroupRoles(Integer groupId) {
		logger.debug("getGroupRoles()");
		
		Map<String, Object> params = fillParams(
				DataBaseUtil.paramList(groupId),
				new String[] { "GROUP_ID" });
		return getList(UmsQueryStore.UmsQueries.GET_GROUP_ROLES, Role.class, params,
				1000);
	}
	
	
	/**
	 * Gets the group users.
	 *
	 * @param groupId the group id
	 * @return the group users
	 */
	public List<User> getGroupUsers(Integer groupId) {
		Map<String, Object> params = fillParams(
				DataBaseUtil.paramList(groupId),
				new String[] { "GROUP_ID" });
		return getList(UmsQueryStore.UmsQueries.GET_GROUP_USERS, User.class, params,
				1000);

	}

	/**
	 * Enable.
	 *
	 * @param groupId the group id
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 */
	public void enable(Integer groupId, String updatedBy, Date updatedOn) {
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				groupId, updatedBy, new Timestamp(updatedOn.getTime())),
				new String[] { "GROUP_ID", "UPDATED_BY", "UPDATED_ON" });
		executeQuery(UmsQueryStore.UmsQueries.ENABLE_GROUP_QUERY, params);
	}

}
