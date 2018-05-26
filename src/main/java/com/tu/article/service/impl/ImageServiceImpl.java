package com.tu.article.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tu.article.dao.ImageDao;
import com.tu.article.entity.Image;
import com.tu.article.service.ImageService;

/**
 * Service class that implements DAO methods for working with {@link Image}
 * entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Service
@Transactional(value = "transactionManager", readOnly = true)
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDao imageDao;

	@Override
	public Image getActiveImageByUser(Long userId) {
		return imageDao.getActiveImageByUser(userId);
	}

}
