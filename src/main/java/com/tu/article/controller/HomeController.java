package com.tu.article.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.constant.Constant;
import com.tu.article.controller.constant.RequestAttribute;
import com.tu.article.controller.constant.ViewConstant;
import com.tu.article.entity.Article;
import com.tu.article.entity.constant.SystemParameter;
import com.tu.article.filter.ArticleFilter;
import com.tu.article.service.ArticleCategoryService;
import com.tu.article.service.ArticleService;
import com.tu.article.service.ParameterService;

/**
 * Home controller for working with the /index context
 *
 * @author aleksandar.kovachev
 *
 */
@Controller
public class HomeController {

	@Autowired
	private ParameterService parameterService;

	@Autowired
	private ArticleCategoryService articleCategoryService;

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		ArticleFilter filter = new ArticleFilter(10);
		filter.setCategoryId(Constant.INVALID_SELECTION);
		addModelMapAttributes(modelMap, filter);
		return new ModelAndView(ViewConstant.INDEX, modelMap);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView postHome(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute(RequestAttribute.FILTER) ArticleFilter filter) {
		ModelMap modelMap = new ModelMap();
		addModelMapAttributes(modelMap, filter);
		Integer totalCount = articleService.getCountArticlesByFilter(filter);
		filter.setTotalCount(totalCount);
		List<Article> articles = articleService.getArticlesByFilter(filter);
		modelMap.addAttribute(RequestAttribute.ARTICLES, articles);
		return new ModelAndView(ViewConstant.INDEX, modelMap);
	}

	@RequestMapping(value = "/articles/getData", method = RequestMethod.POST)
	public ModelAndView getData(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute(RequestAttribute.FILTER) ArticleFilter filter,
			@RequestParam(RequestAttribute.PAGE_NUMBER) Integer pageNumber,
			@RequestParam(RequestAttribute.PAGE_SIZE) Integer pageSize,
			@RequestParam(RequestAttribute.TOTAL_COUNT) Integer totalCount) {
		ModelMap modelMap = new ModelMap();
		filter.setPageSize(pageSize);
		filter.setTotalCount(totalCount);
		filter.setPageNumber(pageNumber);
		addModelMapAttributes(modelMap, filter);
		List<Article> articles = articleService.getArticlesByFilter(filter);
		modelMap.addAttribute(RequestAttribute.ARTICLES, articles);
		return new ModelAndView(ViewConstant.ARTICLES_LIST + " :: " + ViewConstant.ARTICLES, modelMap);
	}

	private void addModelMapAttributes(ModelMap modelMap, ArticleFilter filter) {
		if (filter.getCategoryId() == null) {
			filter.setCategoryId(Constant.INVALID_SELECTION);
		}
		modelMap.addAttribute(RequestAttribute.FILTER, filter);
		String url = parameterService.getParameterByName(SystemParameter.APACHE_SERVER_ADDRESS.name()).getValue()
				+ parameterService.getParameterByName(SystemParameter.APACHE_IMAGES_PATH.name()).getValue();
		modelMap.addAttribute(RequestAttribute.URL, url);
		modelMap.addAttribute(RequestAttribute.ARTICLE_CATEGORIES, articleCategoryService.getAllArticleCategories());
	}

}
