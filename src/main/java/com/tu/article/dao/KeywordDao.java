package com.tu.article.dao;

import java.util.List;

import com.tu.article.entity.Keyword;

/**
 * Defining DAO for working with {@link Keyword} entity
 *
 * @author aleksandar.kovachev
 *
 */
public interface KeywordDao {

	/**
	 * Get all keywords as a list
	 *
	 * @return {@link List} of {@link Keyword}
	 */
	public List<Keyword> getAllKeywords();

	/**
	 * Get {@link Keyword} by given name
	 *
	 * @param name
	 *            of {@link Keyword}
	 * @return {@link Keyword}
	 */
	public Keyword getKeywordByName(String name);

}
