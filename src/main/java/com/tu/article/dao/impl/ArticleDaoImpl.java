package com.tu.article.dao.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.tu.article.constant.Constant;
import com.tu.article.dao.ArticleDao;
import com.tu.article.dao.BaseDao;
import com.tu.article.dao.constant.DaoConstants;
import com.tu.article.entity.Article;
import com.tu.article.entity.Status;
import com.tu.article.filter.ArticleFilter;

/**
 * DAO class for working with {@link Article} entity
 *
 * @author aleksandar.kovachev
 *
 */
@Repository
public class ArticleDaoImpl extends BaseDao implements ArticleDao {

	@Override
	public Article getArticleById(Long id) {
		return getSession().get(Article.class, id);
	}

	@Override
	public List<Article> getArticlesByAuthor(String username) {
		Query<Article> query = getSession().createQuery(
				"select article from Article as article join article.authors as user where user.username = :username",
				Article.class);
		query.setParameter(DaoConstants.USERNAME, username);
		return query.list();
	}

	@Override
	public List<Article> getArticlesWithoutReview() {
		Query<Article> query = getSession().createQuery(
				"select article from Article as article left join article.articleReviewers as reviewers where reviewers.id is null",
				Article.class);
		return query.list();
	}

	@Override
	public List<Article> getArticlesByReviewer(String username) {
		Query<Article> query = getSession().createQuery(
				"select article from Article as article join article.articleReviewers as reviewers where reviewers.user.username = :username",
				Article.class);
		query.setParameter(DaoConstants.USERNAME, username);
		return query.list();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Number getCountArticlesByFilter(ArticleFilter filter) {
		Query query = getArticles(filter, "count(distinct article)");
		return (Number) query.uniqueResult();
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Article> getArticlesByFilter(ArticleFilter filter) {
		Query query = getArticles(filter, "distinct article");

		query.setFirstResult(filter.getPageNumber() * filter.getPageSize());
		query.setMaxResults(filter.getPageSize());
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	private Query getArticles(ArticleFilter filter, String returnQuery) {
		StringBuilder hql = new StringBuilder("select " + returnQuery
				+ " from Article as article left join article.authors as author left join article.keywords as keyword where article.status.id = :status ");

		if (StringUtils.hasText(filter.getTitle())) {
			hql.append("and lower(article.title) like :title ");
		}

		if (StringUtils.hasText(filter.getAbstractText())) {
			hql.append("and lower(article.abstractColumn) like :abstract ");
		}

		if (filter.getCategoryId() != null && !filter.getCategoryId().equals(Constant.INVALID_SELECTION)) {
			hql.append("and article.articleCategory.id = :articleCategoryId ");
		}

		if (!CollectionUtils.isEmpty(filter.getKeywords())) {
			hql.append("and lower(keyword.name) in :keywords ");
		}

		if (StringUtils.hasText(filter.getAuthorName())) {
			hql.append("and lower(author.name) like :authorName ");
		}

		Query query = getSession().createQuery(hql.toString());
		query.setParameter(DaoConstants.STATUS, Status.ACTIVE_STATUS);
		if (StringUtils.hasText(filter.getTitle())) {
			query.setParameter(DaoConstants.TITLE, "%" + filter.getTitle().toLowerCase() + "%");
		}

		if (StringUtils.hasText(filter.getAbstractText())) {
			query.setParameter(DaoConstants.ABSTRACT, "%" + filter.getAbstractText().toLowerCase() + "%");
		}

		if (filter.getCategoryId() != null && !filter.getCategoryId().equals(Constant.INVALID_SELECTION)) {
			query.setParameter(DaoConstants.ARTICLE_CATEGORY_ID, filter.getCategoryId());
		}

		if (!CollectionUtils.isEmpty(filter.getKeywords())) {
			query.setParameter(DaoConstants.KEYWORDS, filter.getKeywords());
		}

		if (StringUtils.hasText(filter.getAuthorName())) {
			query.setParameter(DaoConstants.AUTHOR_NAME, "%" + filter.getAuthorName().toLowerCase() + "%");
		}
		return query;
	}

	@Override
	public List<Article> getInactiveArticles() {
		Query<Article> query = getSession().createQuery(
				"select distinct article from Article as article join article.articleReviewers as reviewers join reviewers.review where article.status.id = :status",
				Article.class);
		query.setParameter(DaoConstants.STATUS, Status.INACTIVE_STATUS);
		return query.list();
	}

}
