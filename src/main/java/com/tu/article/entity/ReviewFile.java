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
 * Entity representation for review_file table
 *
 * @author aleksandar.kovachev
 *
 */
@Data
@Entity
@Table(name = EntityConstants.REVIEW_FILE_TABLE_NAME)
public class ReviewFile {

	@Id
	@Column(name = EntityConstants.ID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = EntityConstants.NAME)
	private String name;

	@ManyToOne
	@JoinColumn(name = EntityConstants.STATUS_ID)
	private Status status;

}
