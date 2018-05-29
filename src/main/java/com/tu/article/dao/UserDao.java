package com.tu.article.dao;

import java.util.List;

import com.tu.article.entity.User;
import com.tu.article.filter.BasePageFilter;

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

	/**
	 * Get list of all users
	 * 
	 * @return {@link List} of {@link User}
	 */
	public List<User> getUsers(BasePageFilter filter);

}
