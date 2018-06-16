package com.tu.article.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tu.article.dao.ArticleStatusDao;
import com.tu.article.dao.BaseDao;
import com.tu.article.entity.ArticleStatus;

/**
 * DAO class for working with {@link ArticleStatus} entity
 *
 * @author aleksandar.kovachev
 *
 */
@Repository
public class ArticleStatusDaoImpl extends BaseDao implements ArticleStatusDao {

	@Override
	public List<ArticleStatus> getAllArticleStatuses() {
		return getSession().createQuery("from ArticleStatus", ArticleStatus.class).getResultList();
	}

}
