package com.tu.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tu.article.dao.KeywordDao;
import com.tu.article.entity.Degree;
import com.tu.article.entity.Keyword;
import com.tu.article.service.KeywordService;

/**
 * Service class that implements DAO methods for working with {@link Degree}
 * entity
 *
 * @author aleksandar.kovachev
 *
 */
@Service
@Transactional(value = "transactionManager", readOnly = true)
public class KeywordServiceImpl implements KeywordService {

	@Autowired
	private KeywordDao keywordDao;

	@Override
	public List<Keyword> getAllKeywords() {
		return keywordDao.getAllKeywords();
	}

	@Override
	public Keyword getKeywordByName(String name) {
		return keywordDao.getKeywordByName(name);
	}

}
