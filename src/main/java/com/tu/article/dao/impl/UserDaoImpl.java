package com.tu.article.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.tu.article.dao.BaseDao;
import com.tu.article.dao.UserDao;
import com.tu.article.dao.constant.DaoConstants;
import com.tu.article.entity.Status;
import com.tu.article.entity.User;
import com.tu.article.entity.constant.RoleEnum;
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
	public User getActiveUserByEmail(String email) {
		Query<User> query = getSession().createQuery(
				"from User where email = :" + DaoConstants.EMAIL + " and status.id = :" + DaoConstants.STATUS,
				User.class);
		query.setParameter(DaoConstants.EMAIL, email);
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
	public Number getUsersCount() {
		return (Number) getSession().createQuery("select count(*) from User").uniqueResult();
	}

	@Override
	public boolean updateUserRoleAndStatus(Long userId, Long roleId, Long statusId) {
		Query<User> query = getSession()
				.createQuery("update User set status.id = :status, role.id = :role where id = :id", User.class);
		query.setParameter(DaoConstants.ID, userId);
		query.setParameter(DaoConstants.STATUS, statusId);
		query.setParameter(DaoConstants.ROLE, roleId);
		return query.executeUpdate() == 1;
	}

	@Override
	public List<User> getAuthors() {
		Query<User> query = getSession().createQuery("from User where role.id != :role", User.class);
		query.setParameter(DaoConstants.ROLE, RoleEnum.ROLE_USER.getRoleId());
		return query.list();
	}

	@Override
	public List<User> getReviewers() {
		Query<User> query = getSession().createQuery("from User where role.id not in(:role)", User.class);
		List<Long> roles = new ArrayList<>();
		roles.add(RoleEnum.ROLE_USER.getRoleId());
		roles.add(RoleEnum.ROLE_AUTHOR.getRoleId());
		query.setParameter(DaoConstants.ROLE, roles);
		return query.list();
	}

}
