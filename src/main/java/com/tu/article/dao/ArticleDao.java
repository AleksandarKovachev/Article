package com.tu.article.dao;

import java.util.List;

import com.tu.article.entity.Article;
import com.tu.article.filter.ArticleFilter;

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

	/**
	 * Get {@link List} of {@link Article} by reviewer
	 *
	 * @param username
	 *            of {@link User}
	 * @return {@link List} of {@link Article}
	 */
	public List<Article> getArticlesByReviewer(String username);

	/**
	 * Get count of {@link Article} by {@link ArticleFilter} filter
	 *
	 * @param filter
	 *            {@link ArticleFilter}
	 * @return count
	 */
	public Number getCountArticlesByFilter(ArticleFilter filter);

	/**
	 * Get {@link List} of {@link Article} by {@link ArticleFilter} filter
	 *
	 * @param filter
	 *            {@link ArticleFilter}
	 * @return {@link List} of {@link Article}
	 */
	public List<Article> getArticlesByFilter(ArticleFilter filter);

}
