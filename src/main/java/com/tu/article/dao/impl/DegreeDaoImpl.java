package com.tu.article.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tu.article.dao.BaseDao;
import com.tu.article.dao.DegreeDao;
import com.tu.article.entity.Degree;
import com.tu.article.entity.User;

/**
 * DAO class for working with {@link User} entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Repository
public class DegreeDaoImpl extends BaseDao implements DegreeDao {

	@Override
	public List<Degree> getAllDegrees() {
		return getSession().createQuery("from Degree", Degree.class).getResultList();
	}

}
