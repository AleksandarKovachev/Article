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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.tu.article.entity.constant.EntityConstants;

import lombok.Data;

/**
 * Entity representation for article table
 *
 * @author aleksandar.kovachev
 *
 */
@Data
@Entity
@Table(name = EntityConstants.ARTICLE_TABLE_NAME)
public class Article {

	@Id
	@Column(name = EntityConstants.ID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = EntityConstants.ARTICLE_TITLE_COLUMN)
	private String title;

	@Column(name = EntityConstants.ARTICLE_ABSTRACT_COLUMN)
	private String abstractColumn;

	@Column(name = EntityConstants.CREATE_DATE)
	private Date createDate;

	@ManyToOne
	@JoinColumn(name = EntityConstants.ARTICLE_CATEGORY_ID_COLUMN)
	private ArticleCategory articleCategory;

	@ManyToOne
	@JoinColumn(name = EntityConstants.ARTICLE_FILE_ID_COLUMN)
	private ArticleFile articleFile;

	@Column(name = EntityConstants.USER_ID)
	private Long userId;

	@Fetch(value = FetchMode.JOIN)
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = EntityConstants.ARTICLE_AUTHOR_TABLE_NAME, joinColumns = {
			@JoinColumn(name = EntityConstants.ARTICLE_ID) }, inverseJoinColumns = {
					@JoinColumn(name = EntityConstants.USER_ID) })
	private Set<User> authors;

	@Fetch(value = FetchMode.JOIN)
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = EntityConstants.ARTICLE_KEYWORD_TABLE_NAME, joinColumns = {
			@JoinColumn(name = EntityConstants.ARTICLE_ID) }, inverseJoinColumns = {
					@JoinColumn(name = EntityConstants.KEYWORD_ID) })
	private Set<Keyword> keywords;

	@Fetch(value = FetchMode.JOIN)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ArticleReviewer.class)
	@JoinColumn(name = EntityConstants.ARTICLE_ID, referencedColumnName = EntityConstants.ID, nullable = false)
	private Set<ArticleReviewer> articleReviewers;

}
