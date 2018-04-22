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
 * Entity representation for role table
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@Entity
@Table(name = EntityConstants.ROLE_TABLE_NAME)
public class Role {

	@Id
	@Column(name = EntityConstants.ID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = EntityConstants.NAME)
	private String name;

	@Column(name = EntityConstants.DESCRIPTION)
	private String description;

	@Column(name = EntityConstants.CODE)
	private String code;

}
