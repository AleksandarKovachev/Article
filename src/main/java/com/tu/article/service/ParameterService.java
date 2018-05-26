package com.tu.article.service;

import com.tu.article.entity.Parameter;

/**
 * Defining Service for working with {@link Parameter} entity
 * 
 * @author aleksandar.kovachev
 *
 */
public interface ParameterService {

	/**
	 * Get {@link Parameter} by name
	 * 
	 * @param name
	 *            of the {@link Parameter}
	 * @return {@link Parameter}
	 */
	public Parameter getParameterByName(String name);

}
