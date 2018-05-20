package com.tu.article.dao;

import java.util.List;

import com.tu.article.entity.Degree;

/**
 * Defining DAO for working with {@link Degree} entity
 * 
 * @author aleksandar.kovachev
 *
 */
public interface DegreeDao {

	/**
	 * Get all degrees as a list
	 * 
	 * @return {@link List} of {@link Degree}
	 */
	public List<Degree> getAllDegrees();

}
