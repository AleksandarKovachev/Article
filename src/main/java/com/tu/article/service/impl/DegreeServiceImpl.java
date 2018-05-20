package com.tu.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tu.article.dao.DegreeDao;
import com.tu.article.entity.Degree;
import com.tu.article.service.DegreeService;

/**
 * Service class that implements DAO methods for working with {@link Degree}
 * entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Service
@Transactional(value = "transactionManager", readOnly = true)
public class DegreeServiceImpl implements DegreeService {

	@Autowired
	private DegreeDao degreeDao;

	@Override
	public List<Degree> getAllDegrees() {
		return degreeDao.getAllDegrees();
	}

}
