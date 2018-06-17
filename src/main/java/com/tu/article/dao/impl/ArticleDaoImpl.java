package com.tu.article.dao.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.tu.article.dao.ArticleDao;
import com.tu.article.dao.BaseDao;
import com.tu.article.dao.constant.DaoConstants;
import com.tu.article.entity.Article;

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

}
