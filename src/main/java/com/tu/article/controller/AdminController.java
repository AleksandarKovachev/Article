package com.tu.article.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.controller.constant.RequestAttribute;
import com.tu.article.controller.constant.ViewConstant;
import com.tu.article.entity.Article;
import com.tu.article.entity.ArticleReviewer;
import com.tu.article.entity.Parameter;
import com.tu.article.entity.User;
import com.tu.article.entity.constant.SystemParameter;
import com.tu.article.filter.BasePageFilter;
import com.tu.article.service.ArticleService;
import com.tu.article.service.DatabaseManagerService;
import com.tu.article.service.ParameterService;
import com.tu.article.service.UserService;

/**
 * Controller for defining administrator functionalities
 *
 * @author aleksandar.kovachev
 *
 */
@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private DatabaseManagerService databaseManager;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ParameterService parameterService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView users(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		BasePageFilter filter = new BasePageFilter(10);
		filter.setTotalCount(userService.getUsersCount());
		setUserData(modelMap, filter);
		return new ModelAndView(ViewConstant.USERS, modelMap);
	}

	@RequestMapping(value = "/users/getData", method = RequestMethod.POST)
	public ModelAndView getData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(RequestAttribute.PAGE_NUMBER) Integer pageNumber,
			@RequestParam(RequestAttribute.PAGE_SIZE) Integer pageSize,
			@RequestParam(RequestAttribute.TOTAL_COUNT) Integer totalCount) {
		ModelMap modelMap = new ModelMap();
		BasePageFilter filter = new BasePageFilter(pageSize);
		filter.setTotalCount(totalCount);
		filter.setPageNumber(pageNumber);
		setUserData(modelMap, filter);
		return new ModelAndView(ViewConstant.USERS_LIST + " :: users", modelMap);
	}

	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
	public @ResponseBody Boolean updateUser(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id, @RequestParam("roleId") String role,
			@RequestParam("statusId") String status) {
		Long userId, roleId, statusId;
		try {
			userId = Long.parseLong(id);
			roleId = Long.parseLong(role);
			statusId = Long.parseLong(status);
		} catch (NumberFormatException e) {
			return false;
		}
		return userService.updateUserRoleAndStatus(userId, roleId, statusId);
	}

	@RequestMapping(value = "/articleReviewers", method = RequestMethod.GET)
	public ModelAndView articleReviewers(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		Parameter apacheServerAddress = parameterService
				.getParameterByName(SystemParameter.APACHE_SERVER_ADDRESS.name());
		Parameter articlesPath = parameterService.getParameterByName(SystemParameter.APACHE_ARTICLES_PATH.name());
		modelMap.addAttribute(RequestAttribute.URL, apacheServerAddress.getValue() + articlesPath.getValue());
		modelMap.addAttribute(RequestAttribute.ARTICLES, articleService.getArticlesWithoutReview());
		modelMap.addAttribute(RequestAttribute.REVIEWERS, userService.getReviewers());
		return new ModelAndView(ViewConstant.ARTICLE_REVIEWERS, modelMap);
	}

	@RequestMapping(value = "/setArticleReviewers/{id}", method = RequestMethod.POST)
	public @ResponseBody Boolean setArticleReviewers(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id, @RequestBody List<String> reviewers) {
		if (CollectionUtils.isEmpty(reviewers) || reviewers.size() != 2) {
			return false;
		}
		Long articleId = null;
		Set<ArticleReviewer> articleReviewers = new LinkedHashSet<>();
		for (String reviewer : reviewers) {
			Long reviewerId = null;
			try {
				reviewerId = Long.parseLong(reviewer);
				articleId = Long.parseLong(id);
			} catch (NumberFormatException e) {
				return false;
			}
			ArticleReviewer articleReviewer = new ArticleReviewer();
			User user = (User) databaseManager.getObjectById(User.class, reviewerId);
			articleReviewer.setUser(user);
			articleReviewer.setArticleId(articleId);
			articleReviewers.add(articleReviewer);
		}
		Article article = (Article) databaseManager.getObjectById(Article.class, articleId);
		article.setArticleReviewers(articleReviewers);
		databaseManager.updateObject(article);
		return true;
	}

	private void setUserData(ModelMap modelMap, BasePageFilter filter) {
		modelMap.addAttribute(RequestAttribute.FILTER, filter);
		modelMap.addAttribute(RequestAttribute.USERS, userService.getUsers(filter));
		modelMap.addAttribute(RequestAttribute.STATUSES, databaseManager.getAllStatuses());
		modelMap.addAttribute(RequestAttribute.ROLES, databaseManager.getAllRoles());
	}

}
