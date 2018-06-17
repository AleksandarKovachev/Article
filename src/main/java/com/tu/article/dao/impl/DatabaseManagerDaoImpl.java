package com.tu.article.dao.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.tu.article.dao.BaseDao;
import com.tu.article.dao.DatabaseManagerDao;
import com.tu.article.entity.Role;
import com.tu.article.entity.Status;

/**
 * Base DAO methods
 *
 * @author aleksandar.kovachev
 *
 */
@Repository
public class DatabaseManagerDaoImpl extends BaseDao implements DatabaseManagerDao {

	@Override
	public Object getObjectById(Class<?> entity, Long id) {
		return getSession().get(entity, id);
	}

	@Override
	public Long addObject(Object entity) {
		return (Long) getSession().save(entity);
	}

	@Override
	public void updateObject(Object entity) {
		getSession().update(entity);
	}

	@Override
	public Status getActiveStatus() {
		return getSession().get(Status.class, Status.ACTIVE_STATUS);
	}

	@Override
	public List<Status> getAllStatuses() {
		Query<Status> query = getSession().createQuery("from Status", Status.class);
		return query.list();
	}

	@Override
	public List<Role> getAllRoles() {
		Query<Role> query = getSession().createQuery("from Role", Role.class);
		return query.list();
	}

}
