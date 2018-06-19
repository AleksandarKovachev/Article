package com.tu.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tu.article.dao.ArticleDao;
import com.tu.article.entity.Article;
import com.tu.article.filter.ArticleFilter;
import com.tu.article.service.ArticleService;

/**
 * Service class that implements DAO methods for working with {@link Article}
 * entity
 *
 * @author aleksandar.kovachev
 *
 */
@Service
@Transactional(value = "transactionManager", readOnly = true)
public class ArticleServciceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	@Override
	public Article getArticleById(Long id) {
		return articleDao.getArticleById(id);
	}

	@Override
	public List<Article> getArticlesByAuthor(String username) {
		return articleDao.getArticlesByAuthor(username);
	}

	@Override
	public List<Article> getArticlesWithoutReview() {
		return articleDao.getArticlesWithoutReview();
	}

	@Override
	public List<Article> getArticlesByReviewer(String username) {
		return articleDao.getArticlesByReviewer(username);
	}

	@Override
	public Integer getCountArticlesByFilter(ArticleFilter filter) {
		return articleDao.getCountArticlesByFilter(filter).intValue();
	}

	@Override
	public List<Article> getArticlesByFilter(ArticleFilter filter) {
		return articleDao.getArticlesByFilter(filter);
	}

}
