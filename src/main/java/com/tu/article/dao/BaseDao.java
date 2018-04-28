package com.tu.article.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.tu.article.hibernate.HibernateUtil;

/**
 * A class defining base DAO stuffs
 * 
 * @author aleksandar.kovachev
 *
 */
public class BaseDao {

	protected Session session = HibernateUtil.getSession();

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
