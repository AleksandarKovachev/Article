package com.tu.article.service;

import java.util.List;

import com.tu.article.entity.Role;
import com.tu.article.entity.Status;

/**
 * Defining base Service methods
 *
 * @author aleksandar.kovachev
 *
 */
public interface DatabaseManagerService {

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

	/**
	 * Returns the inactive {@link Status}
	 *
	 * @return inactive {@link Status}
	 */
	public Status getInactiveStatus();

	/**
	 * Get all statuses
	 *
	 * @return {@link List} of {@link Status}
	 */
	public List<Status> getAllStatuses();

	/**
	 * Get all statuses
	 *
	 * @return {@link List} of {@link Role}
	 */
	public List<Role> getAllRoles();

	/**
	 * Update database object
	 *
	 * @param entity
	 *            the database object
	 */
	void updateObject(Object entity);

}
