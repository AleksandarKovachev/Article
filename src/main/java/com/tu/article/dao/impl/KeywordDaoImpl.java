package com.tu.article.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tu.article.dao.BaseDao;
import com.tu.article.dao.KeywordDao;
import com.tu.article.entity.Keyword;

/**
 * DAO class for working with {@link Keyword} entity
 *
 * @author aleksandar.kovachev
 *
 */
@Repository
public class KeywordDaoImpl extends BaseDao implements KeywordDao {

	@Override
	public List<Keyword> getAllKeywords() {
		return getSession().createQuery("from Keyword", Keyword.class).getResultList();
	}

}
