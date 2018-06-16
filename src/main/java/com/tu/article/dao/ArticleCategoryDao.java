package com.tu.article.dao;

import java.util.List;

import com.tu.article.entity.ArticleCategory;

/**
 * Defining DAO for working with {@link ArticleCategory} entity
 *
 * @author aleksandar.kovachev
 *
 */
public interface ArticleCategoryDao {

	/**
	 * Get all article categories as a list
	 *
	 * @return {@link List} of {@link ArticleCategory}
	 */
	public List<ArticleCategory> getAllArticleCategories();

}
