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
	public void updateArticleReviewer(Long userId, Long reviewId) {
		Query query = getSession()
				.createQuery("update ArticleReviewer as ar set ar.reviewId = :reviewId where ar.user.id = :userId");
		query.setParameter(DaoConstants.REVIEW_ID, reviewId);
		query.setParameter(DaoConstants.USER_ID, userId);
		query.executeUpdate();
	}

}
