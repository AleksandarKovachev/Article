package com.tu.article.dao;

import com.tu.article.entity.Review;

/**
 * Defining DAO for working with {@link Review} entity
 *
 * @author aleksandar.kovachev
 *
 */
public interface ReviewDao {

	/**
	 * Get {@link Review} by id
	 *
	 * @param id
	 *            of {@link Review}
	 * @return {@link Review}
	 */
	public Review getReviewById(Long id);

}
