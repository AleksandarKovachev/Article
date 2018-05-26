package com.tu.article.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tu.article.dao.ParameterDao;
import com.tu.article.entity.Parameter;
import com.tu.article.service.ParameterService;

/**
 * Service class that implements DAO methods for working with {@link Parameter}
 * entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Service
@Transactional(value = "transactionManager", readOnly = true)
public class ParameterServiceImpl implements ParameterService {

	@Autowired
	private ParameterDao parameterDao;

	@Override
	public Parameter getParameterByName(String name) {
		return parameterDao.getParameterByName(name);
	}

}
