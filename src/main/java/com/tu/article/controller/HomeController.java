package com.tu.article.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.controller.constant.RequestAttribute;
import com.tu.article.controller.constant.ViewConstant;
import com.tu.article.entity.constant.SystemParameter;
import com.tu.article.filter.ArticleFilter;
import com.tu.article.service.ArticleCategoryService;
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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		ArticleFilter filter = new ArticleFilter(10);
		addModelMapAttributes(modelMap, filter);
		return new ModelAndView(ViewConstant.INDEX, modelMap);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView postHome(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute ArticleFilter filter) {
		ModelMap modelMap = new ModelMap();
		addModelMapAttributes(modelMap, filter);
		return new ModelAndView(ViewConstant.INDEX, modelMap);
	}

	private void addModelMapAttributes(ModelMap modelMap, ArticleFilter filter) {
		modelMap.addAttribute(RequestAttribute.FILTER, filter);
		String url = parameterService.getParameterByName(SystemParameter.APACHE_SERVER_ADDRESS.name()).getValue()
				+ parameterService.getParameterByName(SystemParameter.APACHE_IMAGES_PATH.name()).getValue();
		modelMap.addAttribute(RequestAttribute.URL, url);
		modelMap.addAttribute(RequestAttribute.ARTICLE_CATEGORIES, articleCategoryService.getAllArticleCategories());
	}

}
