package com.tu.article.dao.impl;

import org.springframework.stereotype.Repository;

import com.tu.article.dao.BaseDao;
import com.tu.article.dao.ReviewDao;
import com.tu.article.entity.Review;

/**
 * DAO class for working with {@link Review} entity
 *
 * @author aleksandar.kovachev
 *
 */
@Repository
public class ReviewDaoImpl extends BaseDao implements ReviewDao {

	@Override
	public Review getReviewById(Long id) {
		return getSession().get(Review.class, id);
	}

}
