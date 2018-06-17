package com.tu.article.dao.impl;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.tu.article.dao.BaseDao;
import com.tu.article.dao.ReviewDao;
import com.tu.article.dao.constant.DaoConstants;
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

	@Override
	@SuppressWarnings("rawtypes")
	public void updateArticleReviewer(Long userId, Review review) {
		Query query = getSession()
				.createQuery("update ArticleReviewer as ar set ar.review = :review where ar.user.id = :userId");
		query.setParameter(DaoConstants.REVIEW, review);
		query.setParameter(DaoConstants.USER_ID, userId);
		query.executeUpdate();
	}

}
