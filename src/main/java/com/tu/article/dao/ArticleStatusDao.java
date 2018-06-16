package com.tu.article.dao;

import java.util.List;

import com.tu.article.entity.ArticleStatus;

/**
 * Defining DAO for working with {@link ArticleStatus} entity
 *
 * @author aleksandar.kovachev
 *
 */
public interface ArticleStatusDao {

	/**
	 * Get all article statuses as a list
	 *
	 * @return {@link List} of {@link ArticleStatus}
	 */
	public List<ArticleStatus> getAllArticleStatuses();

}
