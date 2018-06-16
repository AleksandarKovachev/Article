package com.tu.article.dao.impl;

import org.springframework.stereotype.Repository;

import com.tu.article.dao.ArticleDao;
import com.tu.article.dao.BaseDao;
import com.tu.article.entity.Article;
import com.tu.article.entity.Image;

/**
 * DAO class for working with {@link Image} entity
 *
 * @author aleksandar.kovachev
 *
 */
@Repository
public class ArticleDaoImpl extends BaseDao implements ArticleDao {

	@Override
	public Article getArticleById(Long id) {
		return getSession().get(Article.class, id);
	}

}
