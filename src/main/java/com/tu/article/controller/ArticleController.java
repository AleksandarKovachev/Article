package com.tu.article.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.constant.Constant;
import com.tu.article.controller.constant.RequestAttribute;
import com.tu.article.controller.constant.ViewConstant;
import com.tu.article.entity.Article;
import com.tu.article.entity.ArticleCategory;
import com.tu.article.entity.ArticleFile;
import com.tu.article.entity.ArticleReviewer;
import com.tu.article.entity.Keyword;
import com.tu.article.entity.Parameter;
import com.tu.article.entity.User;
import com.tu.article.entity.constant.SystemParameter;
import com.tu.article.filter.ArticleFilter;
import com.tu.article.form.ArticleForm;
import com.tu.article.security.UserDetailsImpl;
import com.tu.article.service.ArticleCategoryService;
import com.tu.article.service.ArticleService;
import com.tu.article.service.DatabaseManagerService;
import com.tu.article.service.KeywordService;
import com.tu.article.service.ParameterService;
import com.tu.article.service.UserService;

/**
 * Controller for working with articles
 *
 * @author aleksandar.kovachev
 *
 */
@Controller
public class ArticleController {

	@Autowired
	private ArticleCategoryService articleCategoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private KeywordService keywordService;

	@Autowired
	private DatabaseManagerService databaseManagerService;

	@Autowired
	private ParameterService parameterService;

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "/article/{" + RequestAttribute.USERNAME + "}", method = RequestMethod.GET)
	public ModelAndView account(@PathVariable(value = RequestAttribute.USERNAME) String username,
			HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		List<Article> articles = articleService.getArticlesByAuthor(username);
		modelMap.addAttribute(RequestAttribute.ARTICLES, articles);

		Parameter apacheServerAddress = parameterService
				.getParameterByName(SystemParameter.APACHE_SERVER_ADDRESS.name());
		Parameter articlesPath = parameterService.getParameterByName(SystemParameter.APACHE_ARTICLES_PATH.name());
		modelMap.addAttribute(RequestAttribute.URL, apacheServerAddress.getValue() + articlesPath.getValue());
		Parameter reviewsPath = parameterService.getParameterByName(SystemParameter.ARTICLE_REVIEWS_PATH.name());
		modelMap.addAttribute(RequestAttribute.REVIEW_URL, apacheServerAddress.getValue() + reviewsPath.getValue());
		return new ModelAndView(ViewConstant.ACCOUNT_ARTICLES, modelMap);
	}

	@RequestMapping(value = "/article/add", method = RequestMethod.GET)
	public ModelAndView addArticle(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute(RequestAttribute.FORM) ArticleForm form) {
		ModelMap modelMap = new ModelMap();
		form.setCategoryId(Constant.INVALID_SELECTION);
		List<Long> authors = new ArrayList<>();
		authors.add(Constant.INVALID_SELECTION);
		form.setAuthors(authors);
		modelMap.addAttribute(RequestAttribute.FORM, form);
		modelMap.addAttribute(RequestAttribute.ARTICLE_CATEGORIES, articleCategoryService.getAllArticleCategories());
		modelMap.addAttribute(RequestAttribute.AUTHORS, userService.getAuthors());
		modelMap.addAttribute(RequestAttribute.IS_EDIT_ARTICLE, false);
		return new ModelAndView(ViewConstant.ADD_ARTICLE, modelMap);
	}

	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@RequestMapping(value = "/article/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editArticle(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String articleId, @ModelAttribute(RequestAttribute.FORM) ArticleForm form)
			throws IOException {
		ModelMap modelMap = new ModelMap();

		Article article = (Article) databaseManagerService.getObjectById(Article.class, Long.parseLong(articleId));
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		isEditableArticle(response, article, userDetails);

		List<Long> authors = new ArrayList<>();
		for (User author : article.getAuthors()) {
			authors.add(author.getId());
		}
		form.setAuthors(authors);
		form.setAbstractText(article.getAbstractColumn());
		form.setTitle(article.getTitle());
		form.setCategoryId(article.getArticleCategory().getId());
		List<String> keywords = new ArrayList<>();
		for (Keyword keyword : article.getKeywords()) {
			keywords.add(keyword.getName());
		}
		form.setKeywords(keywords);

		modelMap.addAttribute(RequestAttribute.FORM, form);
		modelMap.addAttribute(RequestAttribute.ARTICLE_CATEGORIES, articleCategoryService.getAllArticleCategories());
		modelMap.addAttribute(RequestAttribute.AUTHORS, userService.getAuthors());
		modelMap.addAttribute(RequestAttribute.IS_EDIT_ARTICLE, true);
		modelMap.addAttribute(RequestAttribute.ARTICLE_ID, article.getId());
		return new ModelAndView(ViewConstant.ADD_ARTICLE, modelMap);
	}

	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@RequestMapping(value = "/article/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editArticlePost(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String articleId, @ModelAttribute(RequestAttribute.FORM) ArticleForm form)
			throws IOException {
		ModelMap modelMap = new ModelMap();

		Article article = (Article) databaseManagerService.getObjectById(Article.class, Long.parseLong(articleId));
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		isEditableArticle(response, article, userDetails);

		form.getAuthors().add(userDetails.getId());
		List<String> messages = form.validate();
		if (!CollectionUtils.isEmpty(messages)) {
			modelMap.addAttribute(RequestAttribute.ERRORS, messages);
		} else {
			modelMap.addAttribute(RequestAttribute.MESSAGE, "Успешно редактирана статия");
			ArticleFile articleFile = createArticleFile(form, userDetails);
			setArticleData(form, articleFile, article);
			databaseManagerService.updateObject(article);
		}

		modelMap.addAttribute(RequestAttribute.FORM, form);
		modelMap.addAttribute(RequestAttribute.ARTICLE_CATEGORIES, articleCategoryService.getAllArticleCategories());
		modelMap.addAttribute(RequestAttribute.AUTHORS, userService.getAuthors());
		modelMap.addAttribute(RequestAttribute.IS_EDIT_ARTICLE, true);
		modelMap.addAttribute(RequestAttribute.ARTICLE_ID, article.getId());
		return new ModelAndView(ViewConstant.ADD_ARTICLE, modelMap);
	}

	@RequestMapping(value = "/article/add", method = RequestMethod.POST)
	public ModelAndView saveArticle(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute(RequestAttribute.FORM) ArticleForm form) throws IOException {
		ModelMap modelMap = new ModelMap();

		List<String> messages = form.validate();
		if (!CollectionUtils.isEmpty(messages)) {
			modelMap.addAttribute(RequestAttribute.ERRORS, messages);
		} else {
			modelMap.addAttribute(RequestAttribute.MESSAGE, "Успешно добавена статия");

			UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			ArticleFile articleFile = createArticleFile(form, userDetails);

			Article article = new Article();
			article.setCreateDate(new Date());
			article.setUserId(userDetails.getId());
			setArticleData(form, articleFile, article);

			databaseManagerService.addObject(article);
		}

		modelMap.addAttribute(RequestAttribute.FORM, form);
		modelMap.addAttribute(RequestAttribute.IS_EDIT_ARTICLE, false);
		modelMap.addAttribute(RequestAttribute.ARTICLE_CATEGORIES, articleCategoryService.getAllArticleCategories());
		modelMap.addAttribute(RequestAttribute.AUTHORS, userService.getAuthors());
		return new ModelAndView(ViewConstant.ADD_ARTICLE, modelMap);
	}

	@RequestMapping(value = "/article/preview/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView previewArticle(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id, @ModelAttribute(RequestAttribute.FILTER) ArticleFilter filter) {
		ModelMap modelMap = new ModelMap();
		Parameter apacheServerAddress = parameterService
				.getParameterByName(SystemParameter.APACHE_SERVER_ADDRESS.name());
		Parameter articlesPath = parameterService.getParameterByName(SystemParameter.APACHE_ARTICLES_PATH.name());
		Parameter reviewsPath = parameterService.getParameterByName(SystemParameter.ARTICLE_REVIEWS_PATH.name());
		modelMap.addAttribute(RequestAttribute.REVIEW_URL, apacheServerAddress.getValue() + reviewsPath.getValue());
		modelMap.addAttribute(RequestAttribute.URL, apacheServerAddress.getValue() + articlesPath.getValue());
		modelMap.addAttribute(RequestAttribute.ARTICLE, articleService.getArticleById(Long.parseLong(id)));
		modelMap.addAttribute(RequestAttribute.FILTER, filter);
		return new ModelAndView(ViewConstant.PREVIEW_ARTICLE, modelMap);
	}

	@RequestMapping(value = "/keywords", method = RequestMethod.GET)
	public @ResponseBody List<Keyword> getKeywords(HttpServletRequest request, HttpServletResponse response) {
		return keywordService.getAllKeywords();
	}

	private void uploadFile(MultipartFile multipartFile, String fileName) throws IOException {
		Parameter apacheFilesPath = parameterService.getParameterByName(SystemParameter.APACHE_FILES_PATH.name());
		Parameter apacheArticlesPath = parameterService.getParameterByName(SystemParameter.APACHE_ARTICLES_PATH.name());

		Path path;
		byte[] bytes = multipartFile.getBytes();
		path = Paths.get(apacheFilesPath.getValue() + apacheArticlesPath.getValue() + fileName);
		Files.write(path, bytes);
	}

	private void isEditableArticle(HttpServletResponse response, Article article, UserDetailsImpl userDetails)
			throws IOException {
		boolean isAuthor = false;
		for (User user : article.getAuthors()) {
			if (user.getId().equals(userDetails.getId())) {
				isAuthor = true;
			}
		}

		if (!isAuthor) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}

		boolean isEditable = false;
		if (!CollectionUtils.isEmpty(article.getArticleReviewers())) {
			for (ArticleReviewer articleReviewer : article.getArticleReviewers()) {
				if (articleReviewer.getReview() != null
						&& articleReviewer.getReview().getArticleStatus().getId().equals(3L)) {
					isEditable = true;
				}
			}
		} else {
			isEditable = true;
		}
		if (!isEditable) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	private ArticleFile createArticleFile(ArticleForm form, UserDetailsImpl userDetails) throws IOException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm");
		String fileName = userDetails.getUsername() + "_" + simpleDateFormat.format(new Date()) + "_"
				+ form.getArticleFile().getOriginalFilename();

		uploadFile(form.getArticleFile(), fileName);

		ArticleFile articleFile = new ArticleFile();
		articleFile.setName(fileName);
		articleFile.setStatus(databaseManagerService.getActiveStatus());
		articleFile.setId(databaseManagerService.addObject(articleFile));
		return articleFile;
	}

	private void setArticleData(ArticleForm form, ArticleFile articleFile, Article article) {
		article.setTitle(form.getTitle());
		article.setAbstractColumn(form.getAbstractText());
		article.setArticleCategory(
				(ArticleCategory) databaseManagerService.getObjectById(ArticleCategory.class, form.getCategoryId()));
		article.setArticleFile(articleFile);
		article.setStatus(databaseManagerService.getInactiveStatus());

		Set<Keyword> keywords = new LinkedHashSet<>();
		for (String name : form.getKeywords()) {
			Keyword keyword = keywordService.getKeywordByName(name);
			if (keyword == null) {
				keyword = new Keyword();
				keyword.setName(name);
				databaseManagerService.addObject(keyword);
			}
			keywords.add(keyword);
		}
		article.setKeywords(keywords);

		Set<User> authors = new LinkedHashSet<>();
		for (Long id : form.getAuthors()) {
			User user = (User) databaseManagerService.getObjectById(User.class, id);
			authors.add(user);
		}
		article.setAuthors(authors);
	}

}
