package com.tu.article.dao;

import com.tu.article.entity.Parameter;

/**
 * Defining DAO for working with {@link Parameter} entity
 * 
 * @author aleksandar.kovachev
 *
 */
public interface ParameterDao {

	/**
	 * Get {@link Parameter} by name
	 * 
	 * @param name
	 *            of the {@link Parameter}
	 * @return {@link Parameter}
	 */
	public Parameter getParameterByName(String name);

}
