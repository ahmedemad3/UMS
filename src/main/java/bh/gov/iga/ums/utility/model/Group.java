package bh.gov.iga.ums.utility.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Group.
 */
public class Group extends UmsBaseTo  implements FillFromResultSet{

	/** The group id. */
	private Integer groupId;
	
	/** The group name. */
	private String groupName;
	
	/** The role ids. */
	private List<Integer> roleIds;

	/**
	 * Gets the group id.
	 *
	 * @return the group id
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * Sets the group id.
	 *
	 * @param groupId the new group id
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * Gets the group name.
	 *
	 * @return the group name
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Sets the group name.
	 *
	 * @param groupName the new group name
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * Gets the role ids.
	 *
	 * @return the role ids
	 */
	public List<Integer> getRoleIds() {
		return roleIds;
	}

	/**
	 * Sets the role ids.
	 *
	 * @param roleIds the new role ids
	 */
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	/* (non-Javadoc)
	 * @see bh.gov.iga.ums.utility.model.FillFromResultSet#fillFromResultSet(java.sql.ResultSet)
	 */
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {
		
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("GROUP_ID")) {
				this.setGroupId(resultSet.getInt("GROUP_ID"));
			} else if (columnLabel.equalsIgnoreCase("GROUP_NAME")) {
				this.setGroupName(resultSet.getString("GROUP_NAME"));
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupName=" + groupName
				+ ", roleIds=" + roleIds + "]";
	}

	
	

}
