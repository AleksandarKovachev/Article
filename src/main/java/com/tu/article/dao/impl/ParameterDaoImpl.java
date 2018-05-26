package com.tu.article.dao.impl;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.tu.article.dao.BaseDao;
import com.tu.article.dao.ParameterDao;
import com.tu.article.dao.constant.DaoConstants;
import com.tu.article.entity.Parameter;

/**
 * DAO class for working with {@link Parameter} entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Repository
public class ParameterDaoImpl extends BaseDao implements ParameterDao {

	@Override
	public Parameter getParameterByName(String name) {
		Query<Parameter> query = getSession().createQuery(
				"from Parameter where name = :" + DaoConstants.NAME,
				Parameter.class);
		query.setParameter(DaoConstants.NAME, name);
		return query.uniqueResult();
	}

}
