package bh.gov.iga.ums.utility.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import bh.gov.iga.ums.exception.DatabaseException;
import bh.gov.iga.ums.utility.model.FillFromResultSet;


// TODO: Auto-generated Javadoc
/**
 * The Class BaseDAO.
 */

public abstract class UmsBaseDAO implements UmsQueryStore {
	
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(UmsBaseDAO.class);
	/** The dao support. */
	protected NamedParameterJdbcDaoSupport daoSupport = null;

	/** The template. */
	protected NamedParameterJdbcTemplate template = null;

	/** The jdbc template. */
	protected JdbcTemplate jdbcTemplate = null;
	
//	protected PasswordEncoder passwordEncoder;

	/** The queries. */
	private Map<String, String> umsQueries = null;

	/**
	 * Gets the dao support.
	 * 
	 * @return the dao support
	 */
	public NamedParameterJdbcDaoSupport getDaoSupport() {
		return daoSupport;
	}

	/**
	 * Sets the dao support.
	 * 
	 * @param daoSupport
	 *            the new dao support
	 */
	public void setDaoSupport(NamedParameterJdbcDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
		this.template = daoSupport.getNamedParameterJdbcTemplate();
		this.jdbcTemplate = daoSupport.getJdbcTemplate();
	}

//	public PasswordEncoder getPasswordEncoder() {
//		return passwordEncoder;
//	}
//
//	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
//		this.passwordEncoder = passwordEncoder;
//	}

	/**
 * Gets the ums queries.
 *
 * @return the ums queries
 */
public Map<String, String> getUmsQueries() {
		return umsQueries;
	}

	/**
	 * Sets the ums queries.
	 *
	 * @param umsQueries the ums queries
	 */
	public void setUmsQueries(Map<String, String> umsQueries) {
		this.umsQueries = umsQueries;
		for (Iterator<String> iterator = this.umsQueries.keySet().iterator(); iterator
				.hasNext();) {
			String query = (String) iterator.next();
			logger.debug("Query<{}> loaded", query);
		}
	}


	/**
	 * Query by id.
	 *
	 * @param queryId the query id
	 * @return String
	 * @throws DatabaseException also you can change with your exception
	 * @since Feb 24, 2014 queryById
	 */
	protected String queryById(String queryId) throws DatabaseException {
		if (StringUtils.isBlank(queryId)) {
			throw new DatabaseException(UmsQueryStore.EMPTY_QUERY_ID);
		}
		String result = null;
		if (umsQueries == null) {
			throw new DatabaseException(
					UmsQueryStore.QUERY_MAP_NOT_INITIALIZED);
		}
		result = umsQueries.get(queryId);
		if (StringUtils.isBlank(result)) {
			throw new DatabaseException(UmsQueryStore.QUERY_NOT_FOUND + "<"
					+ queryId + ">");
		}
		return result;
	}


	
	/**
	 * Gets the long.
	 *
	 * @param queryId            the query id
	 * @param params            the params
	 * @return the long
	 * @throws DatabaseException             the application exception
	 * @throws DataAccessException             the data access exception
	 */
	protected Long getLong(String queryId, Map<String, Object> params)
			throws DatabaseException {
		logger.debug("getLong({})", queryId, params);
		Long value = template.query(queryById(queryId), params,
				new ResultSetExtractor<Long>() {
					public Long extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						try {
							return rs.next() ? rs.getLong(1) : null;
						} catch (Throwable e) {
							throw new DatabaseException(e);
						}

					}

				});
		return value;
	}
	
	/**
	 * Gets the int.
	 *
	 * @param queryId the query id
	 * @param params the params
	 * @return the int
	 * @throws DatabaseException the database exception
	 */
	protected Integer getInt(String queryId, Map<String, Object> params)
			throws DatabaseException {
		logger.debug("getLong({})", queryId, params);
		Integer value = template.query(queryById(queryId), params,
				new ResultSetExtractor<Integer>() {
					public Integer extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						try {
							return rs.next() ? rs.getInt(1) : null;
						} catch (Throwable e) {
							throw new DatabaseException(e);
						}

					}

				});
		return value;
	}

	/**
	 * Gets the string.
	 *
	 * @param queryId            the query id
	 * @param params            the params
	 * @return the string
	 * @throws DatabaseException             the application exception
	 * @throws DataAccessException             the data access exception
	 */
	protected String getString(String queryId, Map<String, Object> params)
			throws DatabaseException {
		logger.debug("getString({})", queryId, params);
		String value = template.query(queryById(queryId), params,
				new ResultSetExtractor<String>() {
					public String extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						try {
							return rs.next() ? rs.getString(1) : null;
						} catch (Throwable e) {
							throw new DatabaseException(e);
						}

					}
				});
		return value;
	}

	/**
	 * Execute query.
	 *
	 * @param queryId            the query id
	 * @param params            the params
	 * @return the int
	 * @throws DatabaseException             the application exception
	 * @throws DataAccessException             the data access exception
	 */
	protected int executeQuery(String queryId, Map<String, Object> params)
			throws DatabaseException {
		logger.debug("executeQuery({})", queryId, params);
		int noOfAffectedRows = template.update(queryById(queryId), params);
		return noOfAffectedRows;
	}

	/**
	 * Gets the object.
	 *
	 * @param <T>            the generic type
	 * @param queryId            the query id
	 * @param params            the params
	 * @param objType            the obj type
	 * @return the object
	 * @throws DatabaseException             the application exception
	 * @throws DataAccessException             the data access exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getObject(String queryId, Map<String, Object> params,
			final Class<T> objType) throws DatabaseException {
		logger.debug("getObject({})", queryId, params, objType.getName());
		T entity = template.query(queryById(queryId), params,
				new ResultSetExtractor<T>() {
					public T extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						if (rs.next()) {
							FillFromResultSet obj = null;
							try {
								obj = (FillFromResultSet) objType.newInstance();
								obj.fillFromResultSet(rs);
							} catch (Throwable e) {
								logger.error("Failed to getObject (" + objType
										+ ")", e);
								throw new DatabaseException(e);
							}
							return (T) obj;
						}
						return null;
					}
				});
		return entity;
	}

	/**
	 * Gets the list.
	 *
	 * @param <T>            the generic type
	 * @param queryId            the query id
	 * @param objType            the obj type
	 * @param fetchSize            the fetch size
	 * @return the list
	 * @throws DatabaseException             the application exception
	 * @throws DataAccessException             the data access exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(String queryId, final Class<T> objType,
			int fetchSize) throws DatabaseException {
		logger.debug("getList({})", queryId, objType.getName(), fetchSize);
		jdbcTemplate.setFetchSize(fetchSize);
		List<T> list = jdbcTemplate.query(queryById(queryId),
				new RowMapper<T>() {
					public T mapRow(ResultSet resultSet, int index)
							throws SQLException {
						FillFromResultSet obj = null;
						try {
							obj = (FillFromResultSet) objType.newInstance();
							obj.fillFromResultSet(resultSet);
						} catch (Throwable e) {
							logger.error("Failed to getList of (" + objType
									+ ")", e);
							throw new DatabaseException(e);
						}
						return (T) obj;
					}
				});
		return list;
	}

	/**
	 * Gets the list.
	 *
	 * @param <T>            the generic type
	 * @param queryId            the query id
	 * @param objType            the obj type
	 * @return the list
	 * @throws DatabaseException             the application exception
	 * @throws DataAccessException             the data access exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(String queryId, final Class<T> objType)
			throws DatabaseException {
		logger.debug("getList({})", queryId, objType.getName());
		List<T> list = jdbcTemplate.query(queryById(queryId),
				new RowMapper<T>() {
					public T mapRow(ResultSet resultSet, int index)
							throws SQLException {
						FillFromResultSet obj = null;
						try {
							obj = (FillFromResultSet) objType.newInstance();
							obj.fillFromResultSet(resultSet);
						} catch (Throwable e) {
							logger.error("Failed to getList of (" + objType
									+ ")", e);
							throw new DatabaseException(e);
						}
						return (T) obj;
					}
				});
		return list;
	}
	
	/**
	 * Gets the primitive list.
	 *
	 * @param <T> the generic type
	 * @param queryId the query id
	 * @param objType the obj type
	 * @param params the params
	 * @return the primitive list
	 * @throws DatabaseException the database exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> getPrimitiveList(String queryId, final Class<T> objType, Object[] params)
			throws DatabaseException {
		logger.debug("getList({})", queryId, objType.getName());
		List<T> list = jdbcTemplate.query(queryById(queryId), params,
				new RowMapper<T>() {
					public T mapRow(ResultSet resultSet, int index)
							throws SQLException {
						T obj = null;
						try {
							obj = (T) resultSet.getObject(1);
						} catch (Throwable e) {
							logger.error("Failed to getList of (" + objType
									+ ")", e);
							throw new DatabaseException(e);
						}
						return (T) obj;
					}
				});
		return list;
	}

	/**
	 * Gets the list.
	 *
	 * @author Ahmed Emad Gets the list.
	 * @param <T>            the generic type
	 * @param queryId            the query id
	 * @param objType            the obj type
	 * @param params            the params
	 * @param fetchSize            the fetch size
	 * @return the list
	 * @throws DatabaseException             the application exception
	 * @throws DataAccessException             the data access exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(String queryId, final Class<T> objType,
			Map<String, Object> params, int fetchSize)
			throws DatabaseException {
		logger.debug("getList({})", queryId, objType.getName(), params,
				fetchSize);
		jdbcTemplate.setFetchSize(fetchSize);
		List<T> list = template.query(queryById(queryId), params,
				new RowMapper<T>() {
					public T mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						FillFromResultSet obj = null;
						try {
							obj = (FillFromResultSet) objType.newInstance();
							obj.fillFromResultSet(rs);
						} catch (Throwable e) {
							logger.error("Failed to getList of (" + objType
									+ ")", e);
							throw new DatabaseException(e);
						}
						return (T) obj;
					}
				});
		return list;
	}

	/**
	 * Gets the list.
	 *
	 * @author csdvedd Gets the list.
	 * @param <T>            the generic type
	 * @param queryId            the query id
	 * @param objType            the obj type
	 * @param params            the params
	 * @return the list
	 * @throws DatabaseException             the application exception
	 * @throws DataAccessException             the data access exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(String queryId, final Class<T> objType,
			Map<String, Object> params) throws DatabaseException {
		logger.debug("getList({})", queryId, objType.getName(), params);
		List<T> list = template.query(queryById(queryId), params,
				new RowMapper<T>() {
					public T mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						FillFromResultSet obj = null;
						try {
							obj = (FillFromResultSet) objType.newInstance();
							obj.fillFromResultSet(rs);
						} catch (Throwable e) {
							logger.error(
									"Failed to getList of ("
											+ objType.getName() + ")", e);
							throw new DatabaseException(e);
						}
						return (T) obj;
					}
				});
		return list;
	}

	/**
	 * Checks if is exist.
	 *
	 * @param queryId the query id
	 * @param params the params
	 * @param useQueryById the use query by id
	 * @return true, if is exist
	 * @throws DatabaseException             boolean
	 * @since Feb 24, 2014 isExist
	 */
	protected boolean isExist(final String queryId, Map<String, Object> params,
			boolean useQueryById) throws DatabaseException {
		logger.debug("isExist({})", queryId, useQueryById, params);
		Long value = template.query(
				useQueryById ? queryById(queryId) : queryId, params,
				new ResultSetExtractor<Long>() {
					public Long extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						try {
							return rs.next() ? rs.getLong(1) : null;
						} catch (Throwable e) {
							logger.error("Failed to check is exist (" + queryId
									+ ")", e);
							throw new DatabaseException(e);
						}
					}
				});
		if (value == null)
			return false;
		return true;
	}
	
	/**
	 * Checks if is active.
	 *
	 * @param queryId the query id
	 * @param params the params
	 * @param useQueryById the use query by id
	 * @return true, if is active
	 * @throws DatabaseException the database exception
	 */
	protected boolean isActive(final String queryId, Map<String, Object> params,
			boolean useQueryById) throws DatabaseException {
		logger.debug("isActive({})", queryId, useQueryById, params);
		Short value = template.query(
				useQueryById ? queryById(queryId) : queryId, params,
				new ResultSetExtractor<Short>() {
					public Short extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						try {
							return rs.next() ? rs.getShort(1) : null;
						} catch (Throwable e) {
							logger.error("Failed to check is exist (" + queryId
									+ ")", e);
							throw new DatabaseException(e);
						}
					}
				});
		if (value == null || value == 0)
			return false;
		return true;
	}

	/**
	 * Fill params.
	 *
	 * @author csdvedd Fill params.
	 * @param values            the values
	 * @param params            the params
	 * @return the hash map
	 */
	protected Map<String, Object> fillParams(List<Object> values,
			String... params) {
		logger.debug("fillParams({})", values, params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < params.length; i++) {
			map.put(params[i], values.get(i));
		}
		return map;
	}

	/**
	 * Fill sql params.
	 *
	 * @param values the values
	 * @param params the params
	 * @return the sql parameter source
	 */
	protected SqlParameterSource fillSqlParams(List<Object> values,
			String... params) {
		logger.debug("fillSqlParams({})", values, params);
		MapSqlParameterSource map = new MapSqlParameterSource();
		for (int i = 0; i < params.length; i++) {
			map.addValue(params[i], values.get(i));
		}
		return map;
	}

	/**
	 * Execute query return long.
	 *
	 * @author 
	 * @param queryId the query id
	 * @param paramSource the param source
	 * @return the long
	 * @throws DatabaseException             Long
	 * @since Feb 24, 2014 executeQueryReturnLong
	 */
	protected Long executeQueryReturnLong(String queryId,
			SqlParameterSource paramSource) throws DatabaseException {
		logger.debug("executeQueryReturnLong({})", queryId, paramSource);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(queryById(queryId), paramSource, keyHolder);
		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Execute query return int.
	 *
	 * @param queryId the query id
	 * @param paramSource the param source
	 * @return the integer
	 * @throws DatabaseException the database exception
	 */
	protected Integer executeQueryReturnInt(String queryId,
			SqlParameterSource paramSource) throws DatabaseException {
		logger.debug("executeQueryReturnLong({})", queryId, paramSource);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(queryById(queryId), paramSource, keyHolder);
		return keyHolder.getKey().intValue();
	}

	/**
	 * Execute batch update.
	 *
	 * @author 
	 * @param queryId the query id
	 * @param batchArgs the batch args
	 * @return the int[]
	 * @throws DatabaseException             int[]
	 * @since Feb 24, 2014 executeBatchUpdate
	 */
	protected int[] executeBatchUpdate(String queryId, List<Object[]> batchArgs)
			throws DatabaseException {
		logger.info("executeBatchUpdate({})", queryId, batchArgs);
		return jdbcTemplate.batchUpdate(queryById(queryId), batchArgs);
	}

	/**
	 * Gets the list.
	 *
	 * @param <T>            the generic type
	 * @param queryId            the query id
	 * @param objType            the obj type
	 * @param usingXmlFile the using xml file
	 * @return the list
	 * @throws DatabaseException             the application exception
	 * @throws DataAccessException             the data access exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(String queryId, final Class<T> objType , boolean usingXmlFile)
			throws DatabaseException {
		logger.debug("getList({})", queryId, objType.getName());
		List<T> list = jdbcTemplate.query(usingXmlFile?queryById(queryId):queryId,
				new RowMapper<T>() {
					public T mapRow(ResultSet resultSet, int index)
							throws SQLException {
						FillFromResultSet obj = null;
						try {
							obj = (FillFromResultSet) objType.newInstance();
							obj.fillFromResultSet(resultSet);
						} catch (Throwable e) {
							logger.error("Failed to getList of (" + objType
									+ ")", e);
							throw new DatabaseException(e);
						}
						return (T) obj;
					}
				});
		return list;
	}
	
	/**
	 * Gets the count.
	 *
	 * @param queryId the query id
	 * @param params the params
	 * @return count
	 */
	
	public int getCount(String queryId, Object[] params) {
		int count = jdbcTemplate.queryForInt(queryById(queryId), params);
		return count;
	}
}
