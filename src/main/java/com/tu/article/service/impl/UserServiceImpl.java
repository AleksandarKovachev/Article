package com.tu.article.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tu.article.dao.UserDao;
import com.tu.article.entity.User;
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

}
