package com.tu.article.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tu.article.dao.ArticleDao;
import com.tu.article.entity.Article;
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

}
