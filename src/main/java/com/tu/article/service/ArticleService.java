package com.tu.article.service;

import com.tu.article.entity.Article;

/**
 * Defining Service for working with {@link Article} entity
 *
 * @author aleksandar.kovachev
 *
 */
public interface ArticleService {

	/**
	 * Get {@link Article} by id
	 *
	 * @param id
	 *            of {@link Article}
	 * @return {@link Article}
	 */
	public Article getArticleById(Long id);

}
