package com.tu.article.service;

import java.util.List;

import com.tu.article.entity.Keyword;

/**
 * Defining Service for working with {@link Keyword} entity
 *
 * @author aleksandar.kovachev
 *
 */
public interface KeywordService {

	/**
	 * Get all keywords as a list
	 *
	 * @return {@link List} of {@link Keyword}
	 */
	public List<Keyword> getAllKeywords();

}
