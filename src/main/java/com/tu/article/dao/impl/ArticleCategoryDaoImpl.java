package com.tu.article.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tu.article.dao.ArticleCategoryDao;
import com.tu.article.dao.BaseDao;
import com.tu.article.entity.ArticleCategory;

/**
 * DAO class for working with {@link ArticleCategory} entity
 *
 * @author aleksandar.kovachev
 *
 */
@Repository
public class ArticleCategoryDaoImpl extends BaseDao implements ArticleCategoryDao {

	@Override
	public List<ArticleCategory> getAllArticleCategories() {
		return getSession().createQuery("from ArticleCategory", ArticleCategory.class).getResultList();
	}

}
