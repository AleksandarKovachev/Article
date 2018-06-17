package com.tu.article.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tu.article.entity.constant.EntityConstants;

import lombok.Data;

/**
 * Entity representation for article_reviewer table
 *
 * @author aleksandar.kovachev
 *
 */
@Data
@Entity
@Table(name = EntityConstants.ARTICLE_REVIEWER_TABLE_NAME)
public class ArticleReviewer {

	@Id
	@Column(name = EntityConstants.ID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = EntityConstants.ARTICLE_ID, insertable = false, updatable = false)
	private Long articleId;

	@ManyToOne
	@JoinColumn(name = EntityConstants.USER_ID)
	private User user;

	@ManyToOne
	@JoinColumn(name = EntityConstants.REVIEW_ID)
	private Review review;

}
