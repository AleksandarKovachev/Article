package com.tu.article.web.dto;

import com.tu.article.entity.Article;
import com.tu.article.entity.ArticleReviewer;

import lombok.Data;

/**
 * DTO for displaying review on article
 *
 * @author aleksandar.kovachev
 *
 */
@Data
public class ArticleReview {

	private Article article;

	private ArticleReviewer reviewer;

}
