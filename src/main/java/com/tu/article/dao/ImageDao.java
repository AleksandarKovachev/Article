package com.tu.article.dao;

import com.tu.article.entity.Image;

/**
 * Defining DAO for working with {@link Image} entity
 * 
 * @author aleksandar.kovachev
 *
 */
public interface ImageDao {

	/**
	 * Get active {@link Image} by {@link User} id
	 * 
	 * @param userId
	 *            - {@link User} id
	 * @return {@link Image}
	 */
	public Image getActiveImageByUser(Long userId);

}
