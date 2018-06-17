package com.tu.article.entity;

import java.util.Date;

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
 * Entity representation for review table
 *
 * @author aleksandar.kovachev
 *
 */
@Data
@Entity
@Table(name = EntityConstants.ARTICLE_TABLE_NAME)
public class Review {

	@Id
	@Column(name = EntityConstants.ID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = EntityConstants.CREATE_DATE)
	private Date createDate;

	@ManyToOne
	@JoinColumn(name = EntityConstants.ARTICLE_STATUS_ID)
	private ArticleStatus articleStatus;

	@ManyToOne
	@JoinColumn(name = EntityConstants.REVIEW_FILE_ID_COLUMN)
	private ReviewFile reviewFile;

	@ManyToOne
	@JoinColumn(name = EntityConstants.STATUS_ID)
	private Status status;

}
