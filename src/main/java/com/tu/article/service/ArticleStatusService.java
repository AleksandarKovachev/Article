package com.tu.article.service;

import java.util.List;

import com.tu.article.entity.ArticleStatus;

/**
 * Defining Service for working with {@link ArticleStatus} entity
 *
 * @author aleksandar.kovachev
 *
 */
public interface ArticleStatusService {

	/**
	 * Get all article statuses as a list
	 *
	 * @return {@link List} of {@link ArticleStatus}
	 */
	public List<ArticleStatus> getAllArticleStatuses();

}
