package com.tu.article.service;

import com.tu.article.entity.Image;

/**
 * Defining Service for working with {@link Image} entity
 * 
 * @author aleksandar.kovachev
 *
 */
public interface ImageService {

	/**
	 * Get active {@link Image} by {@link User} id
	 * 
	 * @param userId
	 *            - {@link User} id
	 * @return {@link Image}
	 */
	public Image getActiveImageByUser(Long userId);

}
