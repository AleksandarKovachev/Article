package com.tu.article.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tu.article.entity.constant.EntityConstants;

import lombok.Data;

/**
 * Entity representation for parameter table
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@Entity
@Table(name = EntityConstants.PARAMETER_TABLE_NAME)
public class Parameter {

	@Id
	@Column(name = EntityConstants.ID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = EntityConstants.NAME)
	private String name;

	@Column(name = EntityConstants.PARAMETER_VALUE_COLUMN)
	private String value;

}
