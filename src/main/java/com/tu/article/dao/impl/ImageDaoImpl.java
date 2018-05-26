package com.tu.article.dao.impl;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.tu.article.dao.BaseDao;
import com.tu.article.dao.ImageDao;
import com.tu.article.dao.constant.DaoConstants;
import com.tu.article.entity.Image;
import com.tu.article.entity.Status;

/**
 * DAO class for working with {@link Image} entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Repository
public class ImageDaoImpl extends BaseDao implements ImageDao {

	@Override
	public Image getActiveImageByUser(Long userId) {
		Query<Image> query = getSession().createQuery(
				"from Image where user.id = :" + DaoConstants.USER_ID + " and status.id = :" + DaoConstants.STATUS,
				Image.class);
		query.setParameter(DaoConstants.USER_ID, userId);
		query.setParameter(DaoConstants.STATUS, Status.ACTIVE_STATUS);
		return query.uniqueResult();
	}

}
