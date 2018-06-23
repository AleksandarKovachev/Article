package com.tu.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tu.article.dao.DatabaseManagerDao;
import com.tu.article.entity.Role;
import com.tu.article.entity.Status;
import com.tu.article.service.DatabaseManagerService;

/**
 * Service class that implements base DAO methods
 *
 * @author aleksandar.kovachev
 *
 */
@Service
@Transactional(value = "transactionManager", readOnly = true)
public class DatabaseManagerServiceImpl implements DatabaseManagerService {

	@Autowired
	private DatabaseManagerDao databaseManagerDao;

	@Override
	public Object getObjectById(Class<?> entity, Long id) {
		return databaseManagerDao.getObjectById(entity, id);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public Long addObject(Object entity) {
		return databaseManagerDao.addObject(entity);
	}

	@Override
	public Status getActiveStatus() {
		return databaseManagerDao.getActiveStatus();
	}

	@Override
	public List<Status> getAllStatuses() {
		return databaseManagerDao.getAllStatuses();
	}

	@Override
	public List<Role> getAllRoles() {
		return databaseManagerDao.getAllRoles();
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public void updateObject(Object entity) {
		databaseManagerDao.updateObject(entity);
	}

	@Override
	public Status getInactiveStatus() {
		return databaseManagerDao.getInactiveStatus();
	}

}
