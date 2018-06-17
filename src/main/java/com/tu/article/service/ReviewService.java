package com.tu.article.service;

import com.tu.article.entity.ArticleReviewer;
import com.tu.article.entity.Review;

/**
 * Defining Service for working with {@link Review} entity
 *
 * @author aleksandar.kovachev
 *
 */
public interface ReviewService {

	/**
	 * Get {@link Review} by id
	 *
	 * @param id
	 *            of {@link Review}
	 * @return {@link Review}
	 */
	public Review getReviewById(Long id);

	/**
	 * Update review of {@link ArticleReviewer} by userId
	 *
	 * @param userId
	 *            of {@link User} reviewer
	 * @param review
	 *            {@link Review}
	 */
	public void updateArticleReviewer(Long userId, Review review);

}
