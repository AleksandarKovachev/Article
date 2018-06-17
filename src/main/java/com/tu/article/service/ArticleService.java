package com.tu.article.service;

import java.util.List;

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

	/**
	 * Get {@link List} of {@link Article} by author
	 *
	 * @param username
	 *            of {@link User}
	 * @return {@link List} of {@link Article}
	 */
	public List<Article> getArticlesByAuthor(String username);

	/**
	 * Get {@link List} of {@link Article} without review
	 *
	 * @return {@link List} of {@link Article}
	 */
	public List<Article> getArticlesWithoutReview();

}
