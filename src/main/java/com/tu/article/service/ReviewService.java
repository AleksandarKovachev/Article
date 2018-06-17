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
	 * Update reviewId of {@link ArticleReviewer} by userId
	 *
	 * @param userId
	 *            of {@link User} reviewer
	 * @param reviewId
	 *            of {@link Review}
	 */
	public void updateArticleReviewer(Long userId, Long reviewId);

}
