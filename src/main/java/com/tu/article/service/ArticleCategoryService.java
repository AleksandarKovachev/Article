package com.tu.article.service;

import java.util.List;

import com.tu.article.entity.ArticleCategory;

/**
 * Defining Service for working with {@link ArticleCategory} entity
 *
 * @author aleksandar.kovachev
 *
 */
public interface ArticleCategoryService {

	/**
	 * Get all article categories as a list
	 *
	 * @return {@link List} of {@link ArticleCategory}
	 */
	public List<ArticleCategory> getAllArticleCategories();

}
