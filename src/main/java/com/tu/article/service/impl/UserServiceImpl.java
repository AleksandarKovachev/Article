package com.tu.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tu.article.dao.UserDao;
import com.tu.article.entity.User;
import com.tu.article.filter.BasePageFilter;
import com.tu.article.service.UserService;

/**
 * Service class that implements DAO methods for working with {@link User}
 * entity
 *
 * @author aleksandar.kovachev
 *
 */
@Service
@Transactional(value = "transactionManager", readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User getActiveUserByUsername(String username) {
		return userDao.getActiveUserByUsername(username);
	}

	@Override
	public List<User> getUsers(BasePageFilter filter) {
		return userDao.getUsers(filter);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public boolean updateUserRoleAndStatus(Long userId, Long roleId, Long statusId) {
		return userDao.updateUserRoleAndStatus(userId, roleId, statusId);
	}

	@Override
	public int getUsersCount() {
		return userDao.getUsersCount().intValue();
	}

	@Override
	public List<User> getAuthors() {
		return userDao.getAuthors();
	}

}
