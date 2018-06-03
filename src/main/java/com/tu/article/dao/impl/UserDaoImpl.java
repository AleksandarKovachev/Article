package com.tu.article.dao.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.tu.article.dao.BaseDao;
import com.tu.article.dao.UserDao;
import com.tu.article.dao.constant.DaoConstants;
import com.tu.article.entity.Status;
import com.tu.article.entity.User;
import com.tu.article.filter.BasePageFilter;

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

	@Override
	public List<User> getUsers(BasePageFilter filter) {
		Query<User> query = getSession().createQuery("from User", User.class);
		query.setFirstResult(filter.getPageNumber() * filter.getPageSize());
		query.setMaxResults(filter.getPageSize());
		return query.list();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean updateUserRoleAndStatus(Long userId, Long roleId, Long statusId) {
		Query query = getSession()
				.createQuery("update User set status.id = :status, role.id = :role where id = :id");
		query.setParameter(DaoConstants.ID, userId);
		query.setParameter(DaoConstants.STATUS, statusId);
		query.setParameter(DaoConstants.ROLE, roleId);
		return query.executeUpdate() == 1;
	}

}
