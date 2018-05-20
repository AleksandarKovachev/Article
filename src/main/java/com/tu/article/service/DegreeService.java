package com.tu.article.service;

import java.util.List;

import com.tu.article.entity.Degree;

/**
 * Defining Service for working with {@link Degree} entity
 * 
 * @author aleksandar.kovachev
 *
 */
public interface DegreeService {

	/**
	 * Get all degrees as a list
	 * 
	 * @return {@link List} of {@link Degree}
	 */
	public List<Degree> getAllDegrees();

}
