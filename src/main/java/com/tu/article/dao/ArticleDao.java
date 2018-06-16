package com.tu.article.dao;

import com.tu.article.entity.Article;

/**
 * Defining DAO for working with {@link Article} entity
 *
 * @author aleksandar.kovachev
 *
 */
public interface ArticleDao {

	/**
	 * Get {@link Article} by id
	 *
	 * @param id
	 *            of {@link Article}
	 * @return {@link Article}
	 */
	public Article getArticleById(Long id);

}
