package bh.gov.iga.ums.utility.dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bh.gov.iga.ums.utility.management.GroupService;
import bh.gov.iga.ums.utility.management.UserService;
import bh.gov.iga.ums.utility.model.Group;
import bh.gov.iga.ums.utility.model.Role;
import bh.gov.iga.ums.utility.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseHandler.
 */
public class DatabaseHandler {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(DatabaseHandler.class);
	
	/** The Constant APPLICATION_CONTEXT. */
	public static final String APPLICATION_CONTEXT = "/META-INF/spring-config.xml";

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public static Connection getConnection() {
		DataSource dataSource = DatasourceFactory.getDataSourceByJNDI();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("Cannot get connection due to : " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					logger.error("Cannot close connection due to : "
							+ e.getMessage());
					e.printStackTrace();
				}
		}
		return connection;
	}

	/**
	 * Close connection.
	 *
	 * @param connection the connection
	 */
	public static void closeConnection(Connection connection) {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("Cannot close connection due to : "
						+ e.getMessage());
				e.printStackTrace();
			}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Throwable the throwable
	 */
	public static void main(String[] args) throws Throwable {
//		DatabaseHandler.getConnection();
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);
		UserService userService= (UserService) context.getBean("userService");  
		GroupService groupService= (GroupService) context.getBean("groupService");  
		User user= userService.getUserByUsername("ahmed.ed@iga.gov.bh");
		 logger.info("user : " + user.toString());
		 List<User> list= userService.getUsers();
		 logger.info("list : " + list.size());
		 List<Role> roles = userService.getUserRoles("ahmed.ed@iga.gov.bh");
		 logger.info("list : " + roles.size());
		 boolean active = userService.isUserActive("ahmed.ed@iga.gov.bh");
		 logger.info("active : " + active);
		 userService.enableUser("ahmed.ed@iga.gov.bh", "ahmed.ed@iga.gov.bh",new Date());
		 //userService.disableUser("ahmed.ed@iga.gov.bh", "ahmed.ed@iga.gov.bh",new Date());
		 userService.isUserEmailExist("ahmed.ed@iga.gov.bh");
		// userService.changePassword("ahmed.ed@iga.gov.bh", "$2a$10$SkenUDLQcsMNFb.fiv3PuObgSHhBVqlhkntop3bu4.5fvRJbKPdle", "ahmed.ed@iga.gov.bh",new Date());
		// userService.resetPassword("ahmed.ed@iga.gov.bh", "$2a$10$JzpZ0zl.Y9yzbEUF4PC.Iu1/D/J5zRl7vph/iGr9vOceGTxX8ChuG", "ahmed.ed@iga.gov.bh",new Date());
		 userService.delete(1, "ahmed.ed@iga.gov.bh",new Date());
		 User user2 = new User();
		 user2.setUsername("xxxx");
		 user2.setEmail("xxxxx");
		 user2.setActive(false);
//		 userService.save(user2, "ahmed.ed@iga.gov.bh",new Date());
		 userService.update(user2, "ahmed.ed@iga.gov.bh",new Date());
/************************************************************************************************************************************************************/
		 List<Role> roleList = groupService.getGroupRoles(1);
		 logger.info("list : " + roleList.size());
		 List<Group> groups= groupService.getGroups();
		 logger.info("groups : " + groups.size());
		 List<Group> userGroups = groupService.getUserGroups(1);
		 logger.info("userGroups : " + userGroups.size());
//		 groupService.delete(1, "ahmed.ed@iga.gov.bh",new Date());
//		 groupService.enable(1, "ahmed.ed@iga.gov.bh",new Date());
		 
//		 Group group = new Group();
//		 group.setGroupName("admin_group");
//		 List<Integer> roleIds = new ArrayList<Integer>();
//		 roleIds.add(1);
//		 group.setRoleIds(roleIds);
//		 groupService.save(group, "ahmed.ed@iga.gov.bh",new Date());
		 
//		 List<Integer> groupIds = new ArrayList<Integer>();
//		 groupIds.add(3);
//		 groupService.addGroupsToUser(2, groupIds, "ahmed.ed@iga.gov.bh",new Date());
		 
//		 groupService.addGroupToUser(1, 3, "ahmed.ed@iga.gov.bh",new Date());
		 
		 Group group = new Group();
		 group.setGroupId(3);
		 group.setGroupName("ADMIN_GROUP");
		 List<Integer> roleIds = new ArrayList<Integer>();
		 roleIds.add(2);
		 group.setRoleIds(roleIds);
		 groupService.update(group, "ahmed.ed@iga.gov.bh",new Date());
	}

}
