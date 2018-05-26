package com.tu.article.dao.impl;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.tu.article.dao.BaseDao;
import com.tu.article.dao.UserDao;
import com.tu.article.dao.constant.DaoConstants;
import com.tu.article.entity.Status;
import com.tu.article.entity.User;

/**
 * DAO class for working with {@link User} entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public User getActiveUserByUsername(String username) {
		Query<User> query = getSession().createQuery(
				"from User where username = :" + DaoConstants.USERNAME + " and status.id = :" + DaoConstants.STATUS,
				User.class);
		query.setParameter(DaoConstants.USERNAME, username);
		query.setParameter(DaoConstants.STATUS, Status.ACTIVE_STATUS);
		return query.uniqueResult();
	}

}
