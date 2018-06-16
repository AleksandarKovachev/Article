package com.tu.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tu.article.dao.ArticleCategoryDao;
import com.tu.article.entity.ArticleCategory;
import com.tu.article.service.ArticleCategoryService;

/**
 * Service class that implements DAO methods for working with
 * {@link ArticleCategory} entity
 *
 * @author aleksandar.kovachev
 *
 */
@Service
@Transactional(value = "transactionManager", readOnly = true)
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

	@Autowired
	private ArticleCategoryDao articleCategoryDao;

	@Override
	public List<ArticleCategory> getAllArticleCategories() {
		return articleCategoryDao.getAllArticleCategories();
	}

}
