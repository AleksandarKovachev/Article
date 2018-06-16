package com.tu.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tu.article.dao.ArticleStatusDao;
import com.tu.article.entity.ArticleCategory;
import com.tu.article.entity.ArticleStatus;
import com.tu.article.service.ArticleStatusService;

/**
 * Service class that implements DAO methods for working with
 * {@link ArticleCategory} entity
 *
 * @author aleksandar.kovachev
 *
 */
@Service
@Transactional(value = "transactionManager", readOnly = true)
public class ArticleStatusServiceImpl implements ArticleStatusService {

	@Autowired
	private ArticleStatusDao articleStatusDao;

	@Override
	public List<ArticleStatus> getAllArticleStatuses() {
		return articleStatusDao.getAllArticleStatuses();
	}

}
