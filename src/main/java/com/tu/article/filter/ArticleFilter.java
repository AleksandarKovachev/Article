package com.tu.article.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Filter for searching articles
 *
 * @author aleksandar.kovachev
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleFilter extends BasePageFilter {

	public ArticleFilter(int pageSize) {
		super(pageSize);
	}

	private String title;

	private String abstractText;

	private Long categoryId;

	private String authorName;

}
