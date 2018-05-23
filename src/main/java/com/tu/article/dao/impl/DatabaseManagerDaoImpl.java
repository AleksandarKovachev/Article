package com.tu.article.dao.impl;

import org.springframework.stereotype.Repository;

import com.tu.article.dao.BaseDao;
import com.tu.article.dao.DatabaseManagerDao;
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
	public Status getActiveStatus() {
		return getSession().get(Status.class, Status.ACTIVE_STATUS);
	}

}
