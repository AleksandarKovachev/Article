package com.tu.article.dao;

import com.tu.article.entity.User;

/**
 * Defining DAO for working with {@link User} entity
 * 
 * @author aleksandar.kovachev
 *
 */
public interface UserDao {

	/**
	 * Get active {@link User} by username
	 * 
	 * @param username
	 *            of {@link User}
	 * @return {@link User}
	 */
	public User getActiveUserByUsername(String username);

}
