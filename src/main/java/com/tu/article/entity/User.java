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
 * Entity representation for user table
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@Entity
@Table(name = EntityConstants.USER_TABLE_NAME)
public class User {

	@Id
	@Column(name = EntityConstants.ID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = EntityConstants.USER_USERNAME_COLUMN)
	private String username;

	@Column(name = EntityConstants.USER_PASSWORD_COLUMN)
	private String password;

	@Column(name = EntityConstants.USER_EMAIL_COLUMN)
	private String email;

	@Column(name = EntityConstants.NAME)
	private String name;

	@Column(name = EntityConstants.USER_ORGANIZATION_COLUMN)
	private String organization;

	@ManyToOne
	@JoinColumn(name = EntityConstants.DEGREE_ID)
	private Degree degree;

	@Column(name = EntityConstants.CREATE_DATE)
	private Date createDate;

	@Column(name = EntityConstants.MODIFY_DATE)
	private Date modifyDate;

	@Column(name = EntityConstants.USER_IS_CONFIRMED)
	private Integer isConfirmed;

	@ManyToOne
	@JoinColumn(name = EntityConstants.STATUS_ID)
	private Status status;

	@ManyToOne
	@JoinColumn(name = EntityConstants.ROLE_ID_COLUMN_NAME)
	private Role role;

}
