package com.tu.article.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@Column(name = EntityConstants.USER_DEGREE_COLUMN)
	private String degree;

	@Column(name = EntityConstants.CREATE_DATE)
	private Date createDate;

	@Column(name = EntityConstants.MODIFY_DATE)
	private Date modifyDate;

	@Column(name = EntityConstants.USER_IS_CONFIRMED)
	private Integer isConfirmed;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinTable(name = EntityConstants.USER_ROLES_TABLE_NAME, joinColumns = {
			@JoinColumn(name = EntityConstants.USER_ID_COLUMN_NAME) }, inverseJoinColumns = {
					@JoinColumn(name = EntityConstants.ROLE_ID_COLUMN_NAME) })
	private Set<Role> roles;

}
