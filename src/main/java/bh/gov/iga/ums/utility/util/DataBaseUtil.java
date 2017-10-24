package bh.gov.iga.ums.utility.util;

import java.util.ArrayList;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Class DataBaseUtil.
 */
public class DataBaseUtil {
	
	
	/** The Constant AND_OPERATOR. */
	public static final  String AND_OPERATOR = " AND ";
	
	/**
	 * Param list.
	 *
	 * @param values the values
	 * @return the list
	 */
	public static List<Object> paramList(Object... values) {
		List<Object> params = new ArrayList<Object>();
		for (Object value : values) {
			if (value == null)
				params.add(null);
			else
				params.add(value);
		}
		return params;
	}

	/**
	 * Query param like.
	 *
	 * @param queryParam the query param
	 * @return the string
	 */
	public static String queryParamLike(String queryParam) {
		return "%" + queryParam.replaceAll(" ", "%") + "%";
	}
	
}
