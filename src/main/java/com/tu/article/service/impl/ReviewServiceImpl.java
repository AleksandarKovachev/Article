package com.tu.article.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tu.article.dao.ReviewDao;
import com.tu.article.entity.Review;
import com.tu.article.service.ReviewService;

/**
 * Service class that implements DAO methods for working with {@link Review}
 * entity
 *
 * @author aleksandar.kovachev
 *
 */
@Service
@Transactional(value = "transactionManager", readOnly = true)
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;

	@Override
	public Review getReviewById(Long id) {
		return reviewDao.getReviewById(id);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public void updateArticleReviewer(Long userId, Review review) {
		reviewDao.updateArticleReviewer(userId, review);
	}

}
