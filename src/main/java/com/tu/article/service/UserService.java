package com.tu.article.service;

import com.tu.article.entity.User;

/**
 * Defining Service for working with {@link User} entity
 * 
 * @author aleksandar.kovachev
 *
 */
public interface UserService {

	/**
	 * Get active {@link User} by username
	 * 
	 * @param username
	 *            of {@link User}
	 * @return {@link User}
	 */
	public User getActiveUserByUsername(String username);

}
