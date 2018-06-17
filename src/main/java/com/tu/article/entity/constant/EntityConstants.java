package com.tu.article.entity.constant;

/**
 * Declarations for Entity constants
 *
 * @author aleksandar.kovachev
 *
 */
public class EntityConstants {

	// Common
	public static final String ID = "ID";
	public static final String NAME = "NAME";
	public static final String CODE = "CODE";
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final String CREATE_DATE = "CREATE_DATE";
	public static final String MODIFY_DATE = "MODIFY_DATE";
	public static final String STATUS_ID = "STATUS_ID";
	public static final String USER_ID = "USER_ID";
	public static final String ARTICLE_ID = "ARTICLE_ID";

	// Status
	public static final String STATUS_TABLE_NAME = "STATUS";

	// Role
	public static final String ROLE_TABLE_NAME = "ROLE";
	public static final String ROLE_ID_COLUMN_NAME = "ROLE_ID";

	// User
	public static final String USER_TABLE_NAME = "USER";
	public static final String USER_USERNAME_COLUMN = "USERNAME";
	public static final String USER_PASSWORD_COLUMN = "PASSWORD";
	public static final String USER_EMAIL_COLUMN = "EMAIL";
	public static final String USER_ORGANIZATION_COLUMN = "ORGANIZATION";
	public static final String USER_DEGREE_COLUMN = "DEGREE";
	public static final String USER_IS_CONFIRMED = "IS_CONFIRMED";

	// Degree
	public static final String DEGREE_TABLE_NAME = "DEGREE";
	public static final String DEGREE_ID = "DEGREE_ID";

	// Image
	public static final String IMAGE_TABLE_NAME = "IMAGE";

	// Image
	public static final String ARTICLE_FILE_TABLE_NAME = "ARTICLE_FILE";

	// Parameter
	public static final String PARAMETER_TABLE_NAME = "PARAMETER";
	public static final String PARAMETER_VALUE_COLUMN = "VALUE";

	// Article
	public static final String ARTICLE_TABLE_NAME = "ARTICLE";
	public static final String ARTICLE_TITLE_COLUMN = "TITLE";
	public static final String ARTICLE_ABSTRACT_COLUMN = "ABSTRACT";
	public static final String ARTICLE_FILE_ID_COLUMN = "ARTICLE_FILE_ID";
	public static final String ARTICLE_CATEGORY_ID_COLUMN = "ARTICLE_CATEGORY_ID";
	public static final String ARTICLE_AUTHOR_TABLE_NAME = "ARTICLE_AUTHOR";
	public static final String ARTICLE_KEYWORD_TABLE_NAME = "ARTICLE_KEYWORD";

	// ArticleStatus
	public static final String ARTICLE_STATUS_TABLE_NAME = "ARTICLE_STATUS";

	// ArticleCategory
	public static final String ARTICLE_CATEGORY_TABLE_NAME = "ARTICLE_CATEGORY";

	// Keyword
	public static final String KEYWORD_TABLE_NAME = "KEYWORD";
	public static final String KEYWORD_ID = "KEYWORD_ID";

	// ArticleReviewer
	public static final String ARTICLE_REVIEWER_TABLE_NAME = "ARTICLE_REVIEWER";
	public static final String REVIEW_ID = "REVIEW_ID";

	// Review
	public static final String REVIEW_TABLE_NAME = "REVIEW";
	public static final String REVIEW_FILE_ID_COLUMN = "REVIEW_FILE_ID";
	public static final String ARTICLE_STATUS_ID = "ARTICLE_STATUS_ID";

	// ReviewFile
	public static final String REVIEW_FILE_TABLE_NAME = "REVIEW_FILE";

}
