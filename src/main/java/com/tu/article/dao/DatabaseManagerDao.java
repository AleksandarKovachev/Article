package com.tu.article.dao;

import com.tu.article.entity.Status;

/**
 * Defining base DAO methods
 * 
 * @author aleksandar.kovachev
 *
 */
public interface DatabaseManagerDao {

	/**
	 * Get database object by given entity and id
	 * 
	 * @param entity
	 *            type
	 * @param id
	 *            of the entity
	 * @return the resulted database object
	 */
	public Object getObjectById(Class<?> entity, Long id);

	/**
	 * Save database object
	 * 
	 * @param entity
	 *            the database object
	 * @return the resulted id from the database
	 */
	public Long addObject(Object entity);

	/**
	 * Returns the active {@link Status}
	 * 
	 * @return active {@link Status}
	 */
	public Status getActiveStatus();

}
